package com.example.ui.viewmodel

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.R
import com.example.data.local.RecentToolEntity
import com.example.data.local.ToolverseDatabase
import com.example.data.local.ToolverseLocalDao
import com.example.data.model.DocumentDraft
import com.example.data.model.FavoriteTool
import com.example.data.model.TaskHistoryItem
import com.example.data.model.UserProfile
import com.example.data.repository.ToolverseRepository
import com.example.domain.catalog.ToolCatalog
import com.example.domain.catalog.ToolCategory
import com.example.domain.catalog.ToolDefinition
import com.example.domain.processor.CvDraftData
import com.example.domain.processor.DocumentAndImageProcessor
import com.example.domain.processor.ImageToPdfOptions
import com.example.domain.processor.ToolExecutionResult
import com.example.domain.processor.UtilityProcessors
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import java.io.File
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToolverseViewModel(
    private val repository: ToolverseRepository,
    private val localDao: ToolverseLocalDao,
    private val currentUserId: String
) : ViewModel() {

    val userProfile: StateFlow<UserProfile?> = repository.observeUserProfile(currentUserId)
        .catch { e ->
            val fsError = generateSequence(e) { it.cause }.filterIsInstance<FirebaseFirestoreException>().firstOrNull()
            if (fsError == null && e is CancellationException) throw e
            emit(null)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), null)

    val favorites: StateFlow<List<FavoriteTool>> = repository.observeFavorites(currentUserId)
        .catch { e ->
            val fsError = generateSequence(e) { it.cause }.filterIsInstance<FirebaseFirestoreException>().firstOrNull()
            if (fsError == null && e is CancellationException) throw e
            emit(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    val historyItems: StateFlow<List<TaskHistoryItem>> = repository.observeHistory(currentUserId)
        .catch { e ->
            val fsError = generateSequence(e) { it.cause }.filterIsInstance<FirebaseFirestoreException>().firstOrNull()
            if (fsError == null && e is CancellationException) throw e
            emit(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    val cloudDrafts: StateFlow<List<DocumentDraft>> = repository.observeDrafts(currentUserId)
        .catch { e ->
            val fsError = generateSequence(e) { it.cause }.filterIsInstance<FirebaseFirestoreException>().firstOrNull()
            if (fsError == null && e is CancellationException) throw e
            emit(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    val recentTools: StateFlow<List<RecentToolEntity>> = localDao.observeRecentTools()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow<ToolCategory?>(null)
    val selectedCategoryFilter: StateFlow<ToolCategory?> = _selectedCategoryFilter.asStateFlow()

    // Processing & Execution State
    private val _isProcessing = MutableStateFlow(false)
    val isProcessing: StateFlow<Boolean> = _isProcessing.asStateFlow()

    private val _progressCurrent = MutableStateFlow(0)
    val progressCurrent: StateFlow<Int> = _progressCurrent.asStateFlow()

    private val _progressTotal = MutableStateFlow(0)
    val progressTotal: StateFlow<Int> = _progressTotal.asStateFlow()

    private val _executionResult = MutableStateFlow<ToolExecutionResult?>(null)
    val executionResult: StateFlow<ToolExecutionResult?> = _executionResult.asStateFlow()

    private val _statusBanner = MutableStateFlow<String?>(null)
    val statusBanner: StateFlow<String?> = _statusBanner.asStateFlow()

    // Image to PDF Workflow State
    private val _selectedImageUris = MutableStateFlow<List<Uri>>(emptyList())
    val selectedImageUris: StateFlow<List<Uri>> = _selectedImageUris.asStateFlow()

    private val _imageToPdfOptions = MutableStateFlow(ImageToPdfOptions())
    val imageToPdfOptions: StateFlow<ImageToPdfOptions> = _imageToPdfOptions.asStateFlow()

    // CV Maker Studio State
    private val _cvDraft = MutableStateFlow(CvDraftData())
    val cvDraft: StateFlow<CvDraftData> = _cvDraft.asStateFlow()

    private val _cvPhotoUri = MutableStateFlow<Uri?>(null)
    val cvPhotoUri: StateFlow<Uri?> = _cvPhotoUri.asStateFlow()

    private var activeJob: Job? = null

    fun initializeProfile(displayName: String, email: String, photoUrl: String?) {
        viewModelScope.launch {
            repository.ensureUserProfile(displayName, email, photoUrl)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategoryFilter(category: ToolCategory?) {
        _selectedCategoryFilter.value = category
    }

    fun clearExecutionResult() {
        _executionResult.value = null
        _statusBanner.value = null
    }

    fun recordToolOpened(tool: ToolDefinition) {
        viewModelScope.launch {
            localDao.upsertRecentTool(
                RecentToolEntity(
                    toolId = tool.id,
                    toolName = tool.name,
                    category = tool.category.id,
                    lastOpenedAt = System.currentTimeMillis()
                )
            )
        }
    }

    fun isFavorite(toolId: String): Boolean {
        return favorites.value.any { it.toolId == toolId }
    }

    fun toggleFavorite(tool: ToolDefinition) {
        viewModelScope.launch {
            if (isFavorite(tool.id)) {
                repository.removeFavorite(tool.id)
            } else {
                repository.addFavorite(tool.id, tool.name, tool.category.shortTitle)
            }
        }
    }

    fun updateThemeMode(mode: String) {
        viewModelScope.launch {
            repository.updatePreferences(themeMode = mode)
        }
    }

    fun updateFontChoice(font: String) {
        viewModelScope.launch {
            repository.updatePreferences(fontChoice = font)
        }
    }

    fun updateNotifications(enabled: Boolean) {
        viewModelScope.launch {
            repository.updatePreferences(notificationsEnabled = enabled)
        }
    }

    fun updateAutoCleanCache(enabled: Boolean) {
        viewModelScope.launch {
            repository.updatePreferences(autoCleanCache = enabled)
        }
    }

    fun updateCompactMode(enabled: Boolean) {
        viewModelScope.launch {
            repository.updatePreferences(compactMode = enabled)
        }
    }

    // Image to PDF Queue Management
    fun addImagesForPdf(uris: List<Uri>) {
        _selectedImageUris.value = (_selectedImageUris.value + uris).take(120)
    }

    fun appendCapturedCameraBitmap(context: Context, bitmap: Bitmap, addToPdfQueue: Boolean = true): Uri? {
        return try {
            val file = File(context.cacheDir, "Camera_Scan_${System.currentTimeMillis()}.jpg")
            file.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 94, out)
            }
            val uri = Uri.fromFile(file)
            if (addToPdfQueue) {
                addImagesForPdf(listOf(uri))
            }
            _statusBanner.value = "Captured high-resolution photo (${bitmap.width}×${bitmap.height} px)"
            uri
        } catch (e: Exception) {
            _statusBanner.value = "Could not save camera capture: ${e.localizedMessage}"
            null
        }
    }

    fun removeImageAt(index: Int) {
        val current = _selectedImageUris.value.toMutableList()
        if (index in current.indices) {
            current.removeAt(index)
            _selectedImageUris.value = current
        }
    }

    fun moveImage(fromIndex: Int, toIndex: Int) {
        val current = _selectedImageUris.value.toMutableList()
        if (fromIndex in current.indices && toIndex in current.indices) {
            val item = current.removeAt(fromIndex)
            current.add(toIndex, item)
            _selectedImageUris.value = current
        }
    }

    fun clearSelectedImages() {
        _selectedImageUris.value = emptyList()
    }

    fun updateImageToPdfOptions(options: ImageToPdfOptions) {
        _imageToPdfOptions.value = options
    }

    fun runImageToPdfConversion(context: Context, simulate50BatchIfEmpty: Boolean = false) {
        activeJob?.cancel()
        activeJob = viewModelScope.launch {
            _isProcessing.value = true
            _executionResult.value = null
            val startMs = System.currentTimeMillis()
            val uris = if (simulate50BatchIfEmpty && _selectedImageUris.value.isEmpty()) {
                // Generate 52 real sample image files in cacheDir to test 50+ batch memory-safe pipeline
                (1..52).map { idx ->
                    val f = File(context.cacheDir, "batch_page_$idx.png")
                    if (!f.exists()) {
                        val bmp = android.graphics.Bitmap.createBitmap(600, 800, android.graphics.Bitmap.Config.ARGB_8888)
                        val c = android.graphics.Canvas(bmp)
                        c.drawColor(android.graphics.Color.parseColor("#FAF8F3"))
                        val p = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                            color = android.graphics.Color.parseColor("#0F5338")
                            textSize = 36f
                        }
                        c.drawText("Batch Image #$idx / 52", 60f, 400f, p)
                        f.outputStream().use { bmp.compress(android.graphics.Bitmap.CompressFormat.PNG, 90, it) }
                        bmp.recycle()
                    }
                    Uri.fromFile(f)
                }
            } else {
                _selectedImageUris.value
            }

            val result = DocumentAndImageProcessor.convertImagesToPdfIncremental(
                context = context,
                imageUris = uris,
                options = _imageToPdfOptions.value,
                onProgress = { cur, tot ->
                    _progressCurrent.value = cur
                    _progressTotal.value = tot
                }
            )
            val durationMs = System.currentTimeMillis() - startMs
            _isProcessing.value = false
            _executionResult.value = result

            repository.recordTaskHistory(
                toolId = "pdf_image_to_pdf",
                toolName = "Image to PDF",
                category = "PDF Tools",
                status = if (result.success) "COMPLETED" else "FAILED",
                inputSummary = "${maxOf(1, uris.size)} image(s) • ${_imageToPdfOptions.value.pageSize} • ${_imageToPdfOptions.value.resolutionPreset}",
                outputName = result.outputFileName.ifBlank { "Images_Output.pdf" },
                outputUri = result.outputFilePath,
                sizeBytes = result.outputSizeBytes,
                durationMs = durationMs,
                errorMessage = if (!result.success) result.formattedOutput else null
            )
            if (result.success) {
                postTaskCompletionNotification(
                    context = context,
                    title = "Image to PDF Complete",
                    body = result.summaryTitle
                )
            }
        }
    }

    fun cancelActiveProcessing() {
        activeJob?.cancel()
        _isProcessing.value = false
        _statusBanner.value = "Operation cancelled safely. Temporary partial outputs cleaned."
    }

    // CV Studio Management
    fun updateCvDraft(updated: CvDraftData) {
        _cvDraft.value = updated
    }

    fun updateCvPhoto(uri: Uri?) {
        _cvPhotoUri.value = uri
    }

    fun loadCloudDraft(draft: DocumentDraft) {
        _cvDraft.value = CvDraftData.fromMap(draft.structuredData)
        _statusBanner.value = "Loaded draft '${draft.title}'"
    }

    fun saveCurrentCvDraftToCloud() {
        viewModelScope.launch {
            val cv = _cvDraft.value
            val res = repository.saveDraft(
                draftId = null,
                draftType = "CV",
                title = "${cv.fullName} — ${cv.roleTitle}".take(80),
                templateId = cv.templateId,
                structuredData = cv.toMap()
            )
            _statusBanner.value = if (res.isSuccess) {
                "Saved CV draft to Cloud Firestore"
            } else {
                "Could not save draft: ${res.exceptionOrNull()?.localizedMessage}"
            }
        }
    }

    fun deleteCloudDraft(draftId: String) {
        viewModelScope.launch {
            repository.deleteDraft(draftId)
        }
    }

    fun exportCvToPdf(context: Context) {
        viewModelScope.launch {
            _isProcessing.value = true
            val startMs = System.currentTimeMillis()
            val result = UtilityProcessors.generateCvPdf(context, _cvDraft.value, _cvPhotoUri.value)
            val durationMs = System.currentTimeMillis() - startMs
            _isProcessing.value = false
            _executionResult.value = result
            repository.recordTaskHistory(
                toolId = "cv_resume_builder",
                toolName = "CV & Resume Builder",
                category = "CV Maker",
                status = if (result.success) "COMPLETED" else "FAILED",
                inputSummary = "${_cvDraft.value.fullName} (${_cvDraft.value.templateId})",
                outputName = result.outputFileName,
                outputUri = result.outputFilePath,
                sizeBytes = result.outputSizeBytes,
                durationMs = durationMs
            )
        }
    }

    // Universal Tool Execution
    fun executeToolWorkflow(
        context: Context,
        tool: ToolDefinition,
        inputText: String,
        paramOption: String,
        selectedUris: List<Uri>
    ) {
        activeJob?.cancel()
        activeJob = viewModelScope.launch {
            _isProcessing.value = true
            _progressCurrent.value = 1
            _progressTotal.value = 1
            val startMs = System.currentTimeMillis()
            val result = UtilityProcessors.executeTool(
                context = context,
                tool = tool,
                inputText = inputText,
                paramOption = paramOption,
                selectedUris = selectedUris
            )
            val durationMs = System.currentTimeMillis() - startMs
            _isProcessing.value = false
            _executionResult.value = result

            repository.recordTaskHistory(
                toolId = tool.id,
                toolName = tool.name,
                category = tool.category.shortTitle,
                status = if (result.success) "COMPLETED" else "FAILED",
                inputSummary = if (selectedUris.isNotEmpty()) {
                    "${selectedUris.size} file(s) selected"
                } else {
                    inputText.ifBlank { tool.defaultInputSample }.take(60)
                },
                outputName = result.outputFileName.ifBlank { result.summaryTitle.take(80) },
                outputUri = result.outputFilePath,
                sizeBytes = result.outputSizeBytes,
                durationMs = durationMs,
                errorMessage = if (!result.success) result.formattedOutput else null
            )
            if (result.success) {
                postTaskCompletionNotification(
                    context = context,
                    title = "${tool.name} Ready",
                    body = result.summaryTitle
                )
            }
        }
    }

    fun cleanTemporaryCache(context: Context): String {
        val files = context.cacheDir.listFiles()?.toList().orEmpty()
        var freedBytes = 0L
        var removedCount = 0
        files.forEach { f ->
            if (f.isFile) {
                freedBytes += f.length()
                if (f.delete()) removedCount++
            }
        }
        val msg = "Cleaned $removedCount temporary file(s) (${DocumentAndImageProcessor.formatByteSize(freedBytes)} reclaimed)"
        _statusBanner.value = msg
        return msg
    }

    fun postTaskCompletionNotification(context: Context, title: String, body: String) {
        if (userProfile.value?.notificationsEnabled == false) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!granted) return
        }
        try {
            val channelId = "toolverse_tasks_channel"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                val channel = NotificationChannel(
                    channelId,
                    "Toolverse Task & Export Alerts",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Real-time notifications when PDF, Image, CV, and Archive tasks complete."
                }
                mgr?.createNotificationChannel(channel)
            }
            val notification = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(android.R.drawable.stat_sys_download_done)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()
            NotificationManagerCompat.from(context).notify((System.currentTimeMillis() % 10000).toInt(), notification)
        } catch (_: Exception) {}
    }

    fun deleteHistoryItem(taskId: String) {
        viewModelScope.launch {
            repository.deleteHistoryItem(taskId)
        }
    }

    fun clearAllHistory() {
        viewModelScope.launch {
            repository.clearAllHistory()
            localDao.clearRecentTools()
        }
    }

    fun exportAccountData(context: Context) {
        viewModelScope.launch {
            val res = repository.exportUserDataJson()
            res.onSuccess { json ->
                val out = File(context.cacheDir, "Toolverse_Account_Export_${System.currentTimeMillis()}.json")
                out.writeText(json)
                shareGeneratedFile(context, out.absolutePath, "application/json")
            }.onFailure { e ->
                _statusBanner.value = "Export failed: ${e.localizedMessage}"
            }
        }
    }

    fun deleteAccountAndCloudData(onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.deleteAllUserData()
            localDao.clearRecentTools()
            localDao.clearUserDrafts(currentUserId)
            onComplete()
        }
    }

    fun shareGeneratedFile(context: Context, filePath: String?, mimeType: String) {
        if (filePath.isNullOrBlank()) return
        val file = File(filePath)
        if (!file.exists()) {
            Toast.makeText(context, "Output file is no longer in local cache.", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = mimeType
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(intent, "Share ${file.name}"))
        } catch (e: Exception) {
            Toast.makeText(context, "Ready at: ${file.name}", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun provideFactory(currentUserId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = checkNotNull(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) {
                    "APPLICATION_KEY missing from CreationExtras"
                }
                val databaseId = app.getString(R.string.firestore_database_id)
                val db = FirebaseFirestore.getInstance(databaseId)
                val localDb = ToolverseDatabase.getInstance(app)
                ToolverseViewModel(
                    repository = ToolverseRepository(db),
                    localDao = localDb.localDao(),
                    currentUserId = currentUserId
                )
            }
        }
    }
}
