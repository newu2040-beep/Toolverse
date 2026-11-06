package com.example.ui.screens

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.core.content.ContextCompat
import java.io.File
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DocumentDraft
import com.example.domain.catalog.ToolCategory
import com.example.domain.catalog.ToolDefinition
import com.example.domain.processor.CvDraftData
import com.example.domain.processor.DocumentAndImageProcessor
import com.example.domain.processor.ImageToPdfOptions
import com.example.domain.processor.ToolExecutionResult
import com.example.ui.theme.LocalCompactMode

// ==================== SCREEN 4A: IMAGE TO PDF (50+ IMAGES) BATCH WORKFLOW ====================

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageToPdfWorkflowScreen(
    selectedUris: List<Uri>,
    options: ImageToPdfOptions,
    isProcessing: Boolean,
    progressCurrent: Int,
    progressTotal: Int,
    executionResult: ToolExecutionResult?,
    onBack: () -> Unit,
    onAddImages: (List<Uri>) -> Unit,
    onRemoveImage: (Int) -> Unit,
    onMoveImage: (Int, Int) -> Unit,
    onClearImages: () -> Unit,
    onUpdateOptions: (ImageToPdfOptions) -> Unit,
    onConvert: (Boolean) -> Unit,
    onCancel: () -> Unit,
    onShareFile: (String?, String) -> Unit
) {
    BackHandler { onBack() }
    val context = LocalContext.current
    var cameraPermissionDenied by remember { mutableStateOf(false) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(60)
    ) { uris ->
        if (uris.isNotEmpty()) onAddImages(uris)
    }

    val safDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris ->
        if (uris.isNotEmpty()) onAddImages(uris)
    }

    val cameraPreviewLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            try {
                val file = File(context.cacheDir, "Camera_Page_${System.currentTimeMillis()}.jpg")
                file.outputStream().use { out -> bitmap.compress(Bitmap.CompressFormat.JPEG, 94, out) }
                onAddImages(listOf(Uri.fromFile(file)))
                Toast.makeText(context, "Added camera scan to PDF queue", Toast.LENGTH_SHORT).show()
            } catch (_: Exception) {}
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        cameraPermissionDenied = !granted
        if (granted) {
            cameraPreviewLauncher.launch(null)
        } else {
            Toast.makeText(context, "Camera permission required to scan pages directly.", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(48.dp)
                    .testTag("img_to_pdf_back")
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Image to PDF",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "${selectedUris.size} Selected",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 28.dp)
        ) {
            // Picker Controls
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Select Images (Supports 50+ Batch Queue)",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Uses Android Photo Picker & Storage Access Framework with incremental bounded-memory decoding.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                                modifier = Modifier.testTag("pick_photos_button")
                            ) {
                                Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Photo Picker")
                            }

                            OutlinedButton(
                                onClick = { safDocumentLauncher.launch(arrayOf("image/*")) },
                                modifier = Modifier.testTag("pick_saf_images_button")
                            ) {
                                Icon(Icons.Default.FolderOpen, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Browse Files")
                            }

                            OutlinedButton(
                                onClick = {
                                    val hasCam = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.CAMERA
                                    ) == PackageManager.PERMISSION_GRANTED
                                    if (hasCam) {
                                        cameraPreviewLauncher.launch(null)
                                    } else {
                                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                                    }
                                },
                                modifier = Modifier.testTag("capture_camera_page_button")
                            ) {
                                Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Scan Camera")
                            }
                        }

                        if (cameraPermissionDenied) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Camera access denied. Enable in system settings to scan documents.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.weight(1f)
                                )
                                OutlinedButton(
                                    onClick = {
                                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                            data = Uri.fromParts("package", context.packageName, null)
                                        }
                                        context.startActivity(intent)
                                    }
                                ) {
                                    Text("Settings")
                                }
                            }
                        }

                        if (selectedUris.isNotEmpty()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Reorder or remove queued pages below:",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "Clear All",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.error,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .clickable { onClearImages() }
                                        .padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Selected Image Queue with Reorder & Remove
            if (selectedUris.isNotEmpty()) {
                itemsIndexed(selectedUris) { index, uri ->
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(14.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Surface(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = CircleShape,
                                    modifier = Modifier.size(30.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = "${index + 1}",
                                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                        )
                                    }
                                }
                                Text(
                                    text = DocumentAndImageProcessor.resolveDisplayName(context, uri),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { onMoveImage(index, index - 1) },
                                    enabled = index > 0,
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(Icons.Default.ArrowUpward, contentDescription = "Move Up", modifier = Modifier.size(18.dp))
                                }
                                IconButton(
                                    onClick = { onMoveImage(index, index + 1) },
                                    enabled = index < selectedUris.lastIndex,
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(Icons.Default.ArrowDownward, contentDescription = "Move Down", modifier = Modifier.size(18.dp))
                                }
                                IconButton(
                                    onClick = { onRemoveImage(index) },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "Remove Image",
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Page Size, Orientation, Fit/Fill, Margin, Custom Resolution & Quality Options
            item {
                var customWidthText by remember(options.customWidthPx) { mutableStateOf(options.customWidthPx.toString()) }
                var customHeightText by remember(options.customHeightPx) { mutableStateOf(options.customHeightPx.toString()) }
                var customDpiText by remember(options.exportDpi) { mutableStateOf(options.exportDpi.toString()) }

                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = "PDF Layout & Custom Resolution Export",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )

                        Text("Page Size", style = MaterialTheme.typography.labelMedium)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            listOf("A4", "LETTER", "ORIGINAL").forEach { size ->
                                FilterChip(
                                    selected = options.pageSize == size,
                                    onClick = { onUpdateOptions(options.copy(pageSize = size)) },
                                    label = { Text(size) }
                                )
                            }
                        }

                        Text("Custom Resolution & DPI Preset", style = MaterialTheme.typography.labelMedium)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            listOf(
                                "STANDARD" to "Standard PDF",
                                "HD_720P" to "720p HD",
                                "FHD_1080P" to "1080p FHD",
                                "QHD_2K" to "2K QHD",
                                "UHD_4K" to "4K UHD",
                                "PRINT_300DPI" to "Print 300 DPI",
                                "CUSTOM" to "Custom W×H"
                            ).forEach { (presetKey, presetLabel) ->
                                FilterChip(
                                    selected = options.resolutionPreset == presetKey,
                                    onClick = { onUpdateOptions(options.copy(resolutionPreset = presetKey)) },
                                    label = { Text(presetLabel) }
                                )
                            }
                        }

                        if (options.resolutionPreset == "CUSTOM") {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedTextField(
                                    value = customWidthText,
                                    onValueChange = {
                                        customWidthText = it
                                        it.toIntOrNull()?.let { w -> onUpdateOptions(options.copy(customWidthPx = w)) }
                                    },
                                    label = { Text("Width (px)") },
                                    singleLine = true,
                                    modifier = Modifier.weight(1f)
                                )
                                OutlinedTextField(
                                    value = customHeightText,
                                    onValueChange = {
                                        customHeightText = it
                                        it.toIntOrNull()?.let { h -> onUpdateOptions(options.copy(customHeightPx = h)) }
                                    },
                                    label = { Text("Height (px)") },
                                    singleLine = true,
                                    modifier = Modifier.weight(1f)
                                )
                                OutlinedTextField(
                                    value = customDpiText,
                                    onValueChange = {
                                        customDpiText = it
                                        it.toIntOrNull()?.let { d -> onUpdateOptions(options.copy(exportDpi = d)) }
                                    },
                                    label = { Text("DPI") },
                                    singleLine = true,
                                    modifier = Modifier.weight(0.8f)
                                )
                            }
                        }

                        Text("Orientation & Fit Mode", style = MaterialTheme.typography.labelMedium)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            FilterChip(
                                selected = !options.isLandscape,
                                onClick = { onUpdateOptions(options.copy(isLandscape = false)) },
                                label = { Text("Portrait") }
                            )
                            FilterChip(
                                selected = options.isLandscape,
                                onClick = { onUpdateOptions(options.copy(isLandscape = true)) },
                                label = { Text("Landscape") }
                            )
                            FilterChip(
                                selected = options.fitMode == "FIT",
                                onClick = { onUpdateOptions(options.copy(fitMode = "FIT")) },
                                label = { Text("Fit Inside") }
                            )
                            FilterChip(
                                selected = options.fitMode == "FILL",
                                onClick = { onUpdateOptions(options.copy(fitMode = "FILL")) },
                                label = { Text("Fill Page") }
                            )
                        }

                        Text("Margins", style = MaterialTheme.typography.labelMedium)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            listOf(0 to "None", 16 to "Small", 24 to "Normal", 40 to "Wide").forEach { (pt, label) ->
                                FilterChip(
                                    selected = options.marginPt == pt,
                                    onClick = { onUpdateOptions(options.copy(marginPt = pt)) },
                                    label = { Text("$label (${pt}pt)") }
                                )
                            }
                        }

                        Text(
                            text = "Compression Quality: ${options.jpegQuality}%",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Slider(
                            value = options.jpegQuality.toFloat(),
                            onValueChange = { onUpdateOptions(options.copy(jpegQuality = it.toInt())) },
                            valueRange = 40f..100f
                        )
                    }
                }
            }

            // Convert Action & 52-Image Batch Benchmark Button
            item {
                if (isProcessing) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "Converting page $progressCurrent of $progressTotal…",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            LinearProgressIndicator(
                                progress = {
                                    if (progressTotal > 0) progressCurrent.toFloat() / progressTotal.toFloat() else 0f
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedButton(onClick = onCancel) {
                                Text("Cancel Safely")
                            }
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = { onConvert(false) },
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .testTag("convert_images_to_pdf_button")
                        ) {
                            Icon(Icons.Default.PictureAsPdf, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (selectedUris.isEmpty()) {
                                    "Generate Sample Multi-Page PDF Now"
                                } else {
                                    "Convert ${selectedUris.size} Image(s) to PDF"
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }

                        OutlinedButton(
                            onClick = { onConvert(true) },
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("batch_52_images_test_button")
                        ) {
                            Text("Run 52-Image Batch Stress Test (Bounded Memory)")
                        }
                    }
                }
            }

            // Output Preview & Share Card
            if (executionResult != null) {
                item {
                    ExecutionResultCard(
                        result = executionResult,
                        onShare = { onShareFile(executionResult.outputFilePath, executionResult.outputMimeType) }
                    )
                }
            }
        }
    }
}

// ==================== SCREEN 4B: CV MAKER & CAREER STUDIO (MATCHING MOCKUP SCREEN 4) ====================

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CvMakerStudioScreen(
    cvDraft: CvDraftData,
    cloudDrafts: List<DocumentDraft>,
    isProcessing: Boolean,
    executionResult: ToolExecutionResult?,
    statusBanner: String?,
    onBack: () -> Unit,
    onUpdateCv: (CvDraftData) -> Unit,
    onUpdatePhoto: (Uri?) -> Unit,
    onSaveDraftToCloud: () -> Unit,
    onLoadDraftFromCloud: (DocumentDraft) -> Unit,
    onDeleteCloudDraft: (String) -> Unit,
    onExportPdf: () -> Unit,
    onShareFile: (String?, String) -> Unit
) {
    BackHandler { onBack() }
    var activeTab by remember { mutableStateOf("TEMPLATES") } // TEMPLATES, MY_RESUMES, EDITOR

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) onUpdatePhoto(uri)
    }

    val templates = remember {
        listOf(
            Triple("ats_modern", "ATS Classic", Color(0xFF0F5338)),
            Triple("executive_green", "Executive Suite", Color(0xFF1E293B)),
            Triple("minimalist_tech", "Minimalist Tech", Color(0xFF2563EB)),
            Triple("creative_split", "Creative Split", Color(0xFF0D9488)),
            Triple("academic_cv", "Academic Vitae", Color(0xFF4F46E5)),
            Triple("compact_onepage", "Compact 1-Page", Color(0xFFD97706))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        // Top Bar (matching Screen 4 Mockup: Back arrow, "CV Maker", badge)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(48.dp)
                    .testTag("cv_maker_back")
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "CV Maker",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape,
                modifier = Modifier.size(38.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.PictureAsPdf,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 28.dp)
        ) {
            // Hero Banner (matching Screen 4 Mockup: "Create Your Professional CV — Stand out with a perfect resume")
            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFE5ECFF),
                                        Color(0xFFDCE7FF),
                                        Color(0xFFE6F4EE)
                                    )
                                )
                            )
                            .padding(horizontal = 20.dp, vertical = 18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Create Your\nProfessional CV →",
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF141B18),
                                        lineHeight = 24.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Stand out with a perfect\nresume →",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color(0xFF475569)
                                    )
                                )
                            }

                            // Mini CV Card Preview with Avatar Badge
                            Surface(
                                color = Color.White,
                                shape = RoundedCornerShape(14.dp),
                                shadowElevation = 4.dp,
                                modifier = Modifier.size(width = 82.dp, height = 96.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(10.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF2563EB))
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(5.dp)
                                            .background(Color(0xFFCBD5E1), RoundedCornerShape(3.dp))
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.75f)
                                            .height(5.dp)
                                            .background(Color(0xFFE2E8F0), RoundedCornerShape(3.dp))
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.9f)
                                            .height(5.dp)
                                            .background(Color(0xFF10B981).copy(alpha = 0.4f), RoundedCornerShape(3.dp))
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Segmented Pill Tabs: Templates | My Resumes | Form Editor (Mockup Screen 4)
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(50))
                        .padding(4.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        listOf(
                            "TEMPLATES" to "Templates",
                            "MY_RESUMES" to "My Resumes (${cloudDrafts.size})",
                            "EDITOR" to "Edit CV Form"
                        ).forEach { (key, label) ->
                            val selected = activeTab == key
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(50))
                                    .background(if (selected) Color(0xFF0F5338) else Color.Transparent)
                                    .clickable { activeTab = key }
                                    .padding(vertical = 10.dp)
                                    .testTag("cv_tab_$key"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                )
                            }
                        }
                    }
                }
            }

            if (statusBanner != null) {
                item {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = statusBanner,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }

            when (activeTab) {
                "TEMPLATES" -> {
                    // 3x2 Visual Template Cards Grid (matching Screen 4 Mockup)
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            for (row in templates.chunked(3)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    for ((id, title, color) in row) {
                                        val isSelected = cvDraft.templateId == id
                                        Surface(
                                            color = Color.White,
                                            shape = RoundedCornerShape(14.dp),
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(142.dp)
                                                .border(
                                                    width = if (isSelected) 2.dp else 1.dp,
                                                    color = if (isSelected) Color(0xFF0F5338) else MaterialTheme.colorScheme.outline,
                                                    shape = RoundedCornerShape(14.dp)
                                                )
                                                .clip(RoundedCornerShape(14.dp))
                                                .clickable { onUpdateCv(cvDraft.copy(templateId = id)) }
                                                .padding(10.dp)
                                        ) {
                                            Box(modifier = Modifier.fillMaxSize()) {
                                                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(22.dp)
                                                            .clip(RoundedCornerShape(4.dp))
                                                            .background(color)
                                                    )
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxWidth(0.75f)
                                                            .height(5.dp)
                                                            .background(Color(0xFF64748B), RoundedCornerShape(2.dp))
                                                    )
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxWidth(0.9f)
                                                            .height(4.dp)
                                                            .background(Color(0xFFCBD5E1), RoundedCornerShape(2.dp))
                                                    )
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxWidth(0.85f)
                                                            .height(4.dp)
                                                            .background(Color(0xFFCBD5E1), RoundedCornerShape(2.dp))
                                                    )
                                                    Spacer(modifier = Modifier.weight(1f))
                                                    Text(
                                                        text = title,
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color(0xFF1E293B),
                                                            fontSize = 10.sp
                                                        ),
                                                        maxLines = 1
                                                    )
                                                }

                                                if (isSelected) {
                                                    Icon(
                                                        imageVector = Icons.Default.CheckCircle,
                                                        contentDescription = "Selected Template",
                                                        tint = Color(0xFF10B981),
                                                        modifier = Modifier
                                                            .align(Alignment.BottomEnd)
                                                            .size(18.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Primary Action: Fill Form & Create CV -> (Mockup Screen 4)
                    item {
                        Button(
                            onClick = { activeTab = "EDITOR" },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0F5338),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .testTag("fill_form_create_cv_button")
                        ) {
                            Text(
                                text = "Fill Form & Create CV",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "Or",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = {
                                photoPicker.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("upload_cv_photo_button")
                        ) {
                            Icon(Icons.Default.CloudUpload, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Upload Profile Photo / Export Instant CV PDF")
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Export Print Resolution Preset:",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            listOf(
                                "STANDARD" to "Standard A4 (72 DPI)",
                                "FHD_1080P" to "Full HD (150 DPI)",
                                "PRINT_300DPI" to "Print Ready (300 DPI)",
                                "UHD_4K" to "Ultra HD 4K (600 DPI)"
                            ).forEach { (resKey, resLabel) ->
                                FilterChip(
                                    selected = cvDraft.exportResolution == resKey,
                                    onClick = { onUpdateCv(cvDraft.copy(exportResolution = resKey)) },
                                    label = { Text(resLabel) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = onExportPdf,
                            enabled = !isProcessing,
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("export_cv_pdf_direct_button")
                        ) {
                            Icon(Icons.Default.PictureAsPdf, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(if (isProcessing) "Generating PDF…" else "Export Current CV to PDF Now (${cvDraft.exportResolution})")
                        }
                    }
                }

                "MY_RESUMES" -> {
                    if (cloudDrafts.isEmpty()) {
                        item {
                            Surface(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(18.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(18.dp))
                                    .padding(24.dp)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "No Saved Cloud Resumes Yet",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "Open 'Edit CV Form' and tap 'Save Draft to Cloud' to sync your resumes across devices.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    } else {
                        items(cloudDrafts, key = { it.id }) { draft ->
                            Surface(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
                                    .clickable {
                                        onLoadDraftFromCloud(draft)
                                        activeTab = "EDITOR"
                                    }
                                    .padding(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = draft.title,
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                        )
                                        Text(
                                            text = "Template: ${draft.templateId}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    IconButton(onClick = { onDeleteCloudDraft(draft.id) }) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Delete Draft",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                "EDITOR" -> {
                    item {
                        Surface(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
                                .padding(16.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Text(
                                    text = "Structured CV Sections",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )

                                OutlinedTextField(
                                    value = cvDraft.fullName,
                                    onValueChange = { onUpdateCv(cvDraft.copy(fullName = it)) },
                                    label = { Text("Full Name") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    value = cvDraft.roleTitle,
                                    onValueChange = { onUpdateCv(cvDraft.copy(roleTitle = it)) },
                                    label = { Text("Professional Title / Target Role") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                    OutlinedTextField(
                                        value = cvDraft.email,
                                        onValueChange = { onUpdateCv(cvDraft.copy(email = it)) },
                                        label = { Text("Email") },
                                        modifier = Modifier.weight(1f)
                                    )
                                    OutlinedTextField(
                                        value = cvDraft.phone,
                                        onValueChange = { onUpdateCv(cvDraft.copy(phone = it)) },
                                        label = { Text("Phone") },
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                OutlinedTextField(
                                    value = cvDraft.location,
                                    onValueChange = { onUpdateCv(cvDraft.copy(location = it)) },
                                    label = { Text("Location & Portfolio Link") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    value = cvDraft.summary,
                                    onValueChange = { onUpdateCv(cvDraft.copy(summary = it)) },
                                    label = { Text("Executive Summary / Objective") },
                                    minLines = 3,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    value = cvDraft.experience,
                                    onValueChange = { onUpdateCv(cvDraft.copy(experience = it)) },
                                    label = { Text("Work Experience") },
                                    minLines = 4,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    value = cvDraft.education,
                                    onValueChange = { onUpdateCv(cvDraft.copy(education = it)) },
                                    label = { Text("Education") },
                                    minLines = 2,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    value = cvDraft.skills,
                                    onValueChange = { onUpdateCv(cvDraft.copy(skills = it)) },
                                    label = { Text("Skills & Technologies") },
                                    minLines = 2,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    value = cvDraft.projects,
                                    onValueChange = { onUpdateCv(cvDraft.copy(projects = it)) },
                                    label = { Text("Projects & Certifications") },
                                    minLines = 2,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    OutlinedButton(
                                        onClick = onSaveDraftToCloud,
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text("Save Draft to Cloud")
                                    }
                                    Button(
                                        onClick = onExportPdf,
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text("Export PDF")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (executionResult != null) {
                item {
                    ExecutionResultCard(
                        result = executionResult,
                        onShare = { onShareFile(executionResult.outputFilePath, executionResult.outputMimeType) }
                    )
                }
            }
        }
    }
}

// ==================== UNIVERSAL INTERACTIVE TOOL WORKSPACE (FOR ALL 177 UTILITIES) ====================

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InteractiveToolWorkspaceScreen(
    tool: ToolDefinition,
    isFavorite: Boolean,
    isProcessing: Boolean,
    executionResult: ToolExecutionResult?,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit,
    onExecute: (String, String, List<Uri>) -> Unit,
    onShareFile: (String?, String) -> Unit
) {
    BackHandler { onBack() }
    val context = LocalContext.current
    val isCompact = LocalCompactMode.current

    var inputText by remember(tool.id) { mutableStateOf(tool.defaultInputSample) }
    var paramOption by remember(tool.id) { mutableStateOf(tool.defaultParamOption) }
    var pickedUris by remember(tool.id) { mutableStateOf<List<Uri>>(emptyList()) }
    var permissionHint by remember { mutableStateOf<String?>(null) }

    val needsFilePicker = remember(tool.category) {
        tool.category in listOf(
            ToolCategory.PDF,
            ToolCategory.IMAGE,
            ToolCategory.VIDEO,
            ToolCategory.FILE,
            ToolCategory.AUDIO,
            ToolCategory.SECURITY
        )
    }

    val documentPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris ->
        if (uris.isNotEmpty()) pickedUris = uris
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) pickedUris = listOf(uri)
    }

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) pickedUris = listOf(uri)
    }

    val cameraPreviewLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bmp: Bitmap? ->
        if (bmp != null) {
            try {
                val f = File(context.cacheDir, "Tool_Camera_${System.currentTimeMillis()}.jpg")
                f.outputStream().use { bmp.compress(Bitmap.CompressFormat.JPEG, 94, it) }
                pickedUris = listOf(Uri.fromFile(f))
                permissionHint = "Captured camera photo (${bmp.width}×${bmp.height} px)"
            } catch (_: Exception) {}
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            cameraPreviewLauncher.launch(null)
        } else {
            permissionHint = "Camera permission denied. You can grant it in System App Settings."
        }
    }

    val micPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        permissionHint = if (granted) {
            "Microphone permission granted — live audio input active."
        } else {
            "Microphone permission denied. Grant access in System Settings if needed."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = if (isCompact) 12.dp else 20.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = if (isCompact) 6.dp else 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(48.dp)
                    .testTag("tool_workspace_back")
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = tool.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = onToggleFavorite,
                modifier = Modifier
                    .size(48.dp)
                    .testTag("tool_workspace_favorite")
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = "Toggle Favorite",
                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(if (isCompact) 9.dp else 14.dp),
            contentPadding = PaddingValues(bottom = if (isCompact) 18.dp else 28.dp)
        ) {
            // Tool Spec Card
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(18.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(tool.category.containerColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tool.icon,
                                contentDescription = null,
                                tint = tool.category.accentColor,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = tool.shortPurpose,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = tool.supportedFormats,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }
            }

            // Optional File Picker Section
            if (needsFilePicker) {
                item {
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(18.dp))
                            .padding(16.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "Source File Selection (Optional — Sample Ready if Omitted)",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedButton(
                                    onClick = { documentPickerLauncher.launch(arrayOf("*/*")) }
                                ) {
                                    Icon(Icons.Default.FolderOpen, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Select File(s)")
                                }
                                if (tool.category == ToolCategory.IMAGE || tool.category == ToolCategory.PDF) {
                                    OutlinedButton(
                                        onClick = {
                                            imagePickerLauncher.launch(
                                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                            )
                                        }
                                    ) {
                                        Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, modifier = Modifier.size(18.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("Pick Photo")
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            val hasCam = ContextCompat.checkSelfPermission(
                                                context,
                                                Manifest.permission.CAMERA
                                            ) == PackageManager.PERMISSION_GRANTED
                                            if (hasCam) cameraPreviewLauncher.launch(null)
                                            else cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                                        }
                                    ) {
                                        Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(18.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("Camera")
                                    }
                                }
                                if (tool.category == ToolCategory.VIDEO) {
                                    OutlinedButton(
                                        onClick = {
                                            val mediaType = if (tool.id == "gif_to_video") {
                                                ActivityResultContracts.PickVisualMedia.ImageAndVideo
                                            } else {
                                                ActivityResultContracts.PickVisualMedia.VideoOnly
                                            }
                                            videoPickerLauncher.launch(PickVisualMediaRequest(mediaType))
                                        },
                                        modifier = Modifier.testTag("pick_video_button")
                                    ) {
                                        Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(if (tool.id == "gif_to_video") "Pick GIF / Video" else "Pick Video")
                                    }
                                }
                                if (tool.category == ToolCategory.AUDIO) {
                                    OutlinedButton(
                                        onClick = {
                                            micPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                        }
                                    ) {
                                        Icon(Icons.Default.Mic, contentDescription = null, modifier = Modifier.size(18.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("Mic Access")
                                    }
                                }
                            }
                            if (permissionHint != null) {
                                Text(
                                    text = permissionHint!!,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            if (pickedUris.isNotEmpty()) {
                                Text(
                                    text = "Selected ${pickedUris.size} file(s): " +
                                        pickedUris.joinToString { DocumentAndImageProcessor.resolveDisplayName(context, it) },
                                    style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.primary)
                                )
                            }
                        }
                    }
                }
            }

            // Input & Parameter Editor Card
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(18.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = "Input / Configuration Parameters",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )

                        if (tool.id == "img_custom_resolution_export" || tool.id == "img_resize_pixels") {
                            Text(
                                text = "Quick Custom Resolution Presets:",
                                style = MaterialTheme.typography.labelMedium
                            )
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                listOf(
                                    "1280x720@150" to "720p HD",
                                    "1920x1080@300" to "1080p FHD @ 300 DPI",
                                    "2560x1440@300" to "2K QHD",
                                    "3840x2160@300" to "4K UHD",
                                    "1080x1080@300" to "Square 1:1 (300 DPI)",
                                    "2480x3508@300" to "A4 Print (300 DPI)"
                                ).forEach { (spec, label) ->
                                    FilterChip(
                                        selected = inputText == spec,
                                        onClick = { inputText = spec },
                                        label = { Text(label) }
                                    )
                                }
                            }
                        }

                        if (tool.category == ToolCategory.VIDEO) {
                            val videoPresets: List<Pair<String, String>> = when (tool.id) {
                                "video_speed_controller", "video_slow_motion", "video_fast_motion" -> listOf(
                                    "speed=0.25" to "0.25× Ultra Slow",
                                    "speed=0.5" to "0.5× Half Speed",
                                    "speed=1.5" to "1.5× Brisk",
                                    "speed=2.0" to "2.0× Double",
                                    "speed=4.0" to "4.0× Timelapse"
                                )
                                "video_resolution_converter" -> listOf(
                                    "640x360" to "360p SD",
                                    "854x480" to "480p FWVGA",
                                    "1280x720" to "720p HD",
                                    "1920x1080" to "1080p FHD"
                                )
                                "video_fps_converter" -> listOf(
                                    "fps=15" to "15 FPS",
                                    "fps=24" to "24 FPS Cinema",
                                    "fps=30" to "30 FPS Standard",
                                    "fps=60" to "60 FPS Smooth"
                                )
                                "video_format_converter" -> listOf(
                                    "format=MP4" to "MP4",
                                    "format=MOV" to "MOV",
                                    "format=MKV" to "MKV",
                                    "format=WEBM" to "WEBM",
                                    "format=AVI" to "AVI"
                                )
                                "video_compressor_custom_size" -> listOf(
                                    "target_mb=1.0" to "1.0 MB",
                                    "target_mb=2.5" to "2.5 MB",
                                    "target_mb=5.0" to "5.0 MB",
                                    "target_mb=10.0" to "10.0 MB"
                                )
                                "video_rotate_aspect_ratio" -> listOf(
                                    "rotate=0 aspect=16:9" to "16:9 Widescreen",
                                    "rotate=0 aspect=9:16" to "9:16 Vertical",
                                    "rotate=0 aspect=1:1" to "1:1 Square",
                                    "rotate=90 aspect=16:9" to "Rotate 90°",
                                    "rotate=180 aspect=16:9" to "Rotate 180°"
                                )
                                else -> emptyList()
                            }
                            if (videoPresets.isNotEmpty()) {
                                Text(
                                    text = "Quick Video Presets:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    videoPresets.forEach { (spec, label) ->
                                        FilterChip(
                                            selected = inputText == spec,
                                            onClick = { inputText = spec },
                                            label = { Text(label) }
                                        )
                                    }
                                }
                            }
                        }

                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            label = { Text("Primary Input / Payload / Parameters") },
                            minLines = 4,
                            maxLines = 10,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("tool_input_textfield")
                        )

                        if (tool.defaultParamOption.isNotEmpty() || tool.id in listOf("text_find_replace", "dev_regex_tester", "dev_json_path", "sec_text_aes_gcm", "sec_file_aes_gcm")) {
                            OutlinedTextField(
                                value = paramOption,
                                onValueChange = { paramOption = it },
                                label = { Text("Secondary Option / Pattern / Passphrase") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Button(
                            onClick = { onExecute(inputText, paramOption, pickedUris) },
                            enabled = !isProcessing,
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("run_tool_button")
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isProcessing) "Processing…" else "Run ${tool.name}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Result Preview & Export
            if (executionResult != null) {
                item {
                    ExecutionResultCard(
                        result = executionResult,
                        onShare = { onShareFile(executionResult.outputFilePath, executionResult.outputMimeType) }
                    )
                }
            }
        }
    }
}

@Composable
fun ExecutionResultCard(
    result: ToolExecutionResult,
    onShare: () -> Unit
) {
    val context = LocalContext.current
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                color = if (result.success) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
            .testTag("execution_result_card")
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = if (result.success) Icons.Default.CheckCircle else Icons.Default.Close,
                        contentDescription = null,
                        tint = if (result.success) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = result.summaryTitle,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            if (result.previewBitmap != null) {
                Surface(
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(14.dp))
                ) {
                    Image(
                        bitmap = result.previewBitmap.asImageBitmap(),
                        contentDescription = "Output Preview",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    )
                }
            }

            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = result.formattedOutput,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Monospace,
                        lineHeight = 18.sp
                    ),
                    modifier = Modifier.padding(12.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("Toolverse Output", result.formattedOutput))
                        Toast.makeText(context, "Copied output to clipboard", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Copy Output")
                }

                if (!result.outputFilePath.isNullOrBlank()) {
                    Button(
                        onClick = onShare,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("share_output_button")
                    ) {
                        Icon(Icons.Default.IosShare, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Share / Save")
                    }
                }
            }
        }
    }
}
