package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.VideoSettings
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.catalog.EngineType
import com.example.domain.catalog.ToolCatalog
import com.example.domain.catalog.ToolCategory
import com.example.domain.catalog.ToolDefinition
import com.example.ui.screens.CategoryAndToolsScreen
import com.example.ui.screens.CvMakerStudioScreen
import com.example.ui.screens.HistoryScreen
import com.example.ui.screens.HomeDashboardScreen
import com.example.ui.screens.ImageToPdfWorkflowScreen
import com.example.ui.screens.InteractiveToolWorkspaceScreen
import com.example.ui.screens.ProfileAndSettingsScreen
import com.example.ui.screens.WelcomeAuthScreen
import com.example.ui.screens.signOutUser
import com.example.ui.theme.LocalCompactMode
import com.example.ui.theme.ToolverseTheme
import com.example.ui.viewmodel.ToolverseViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToolverseAppRoot()
        }
    }
}

enum class MainNavTab {
    HOME,
    TOOLS,
    HISTORY,
    PROFILE
}

sealed interface ActiveSubScreen {
    data object None : ActiveSubScreen
    data class CategoryBrowse(val category: ToolCategory?) : ActiveSubScreen
    data object ImageToPdf : ActiveSubScreen
    data object CvMakerStudio : ActiveSubScreen
    data class ToolWorkspace(val toolId: String) : ActiveSubScreen
}

@Composable
fun ToolverseAppRoot() {
    var currentUser by remember { mutableStateOf(Firebase.auth.currentUser) }

    DisposableEffect(Unit) {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            currentUser = auth.currentUser
        }
        Firebase.auth.addAuthStateListener(listener)
        onDispose {
            Firebase.auth.removeAuthStateListener(listener)
        }
    }

    val user = currentUser
    if (user == null) {
        ToolverseTheme(themeMode = "LIGHT", fontChoice = "JAKARTA") {
            WelcomeAuthScreen(
                onAuthSuccess = {
                    currentUser = Firebase.auth.currentUser
                }
            )
        }
    } else {
        AuthenticatedToolverseShell(
            currentUserId = user.uid,
            userDisplayName = user.displayName ?: "Toolverse User",
            userEmail = user.email ?: "",
            userPhotoUrl = user.photoUrl?.toString(),
            onSignedOut = { currentUser = null }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticatedToolverseShell(
    currentUserId: String,
    userDisplayName: String,
    userEmail: String,
    userPhotoUrl: String?,
    onSignedOut: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val credentialManager = remember { CredentialManager.create(context) }

    val viewModel: ToolverseViewModel = viewModel(
        key = currentUserId,
        factory = ToolverseViewModel.provideFactory(currentUserId)
    )

    LaunchedEffect(currentUserId) {
        viewModel.initializeProfile(userDisplayName, userEmail, userPhotoUrl)
    }

    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    val historyItems by viewModel.historyItems.collectAsStateWithLifecycle()
    val cloudDrafts by viewModel.cloudDrafts.collectAsStateWithLifecycle()
    val recentTools by viewModel.recentTools.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val isProcessing by viewModel.isProcessing.collectAsStateWithLifecycle()
    val progressCurrent by viewModel.progressCurrent.collectAsStateWithLifecycle()
    val progressTotal by viewModel.progressTotal.collectAsStateWithLifecycle()
    val executionResult by viewModel.executionResult.collectAsStateWithLifecycle()
    val statusBanner by viewModel.statusBanner.collectAsStateWithLifecycle()
    val selectedImageUris by viewModel.selectedImageUris.collectAsStateWithLifecycle()
    val imageToPdfOptions by viewModel.imageToPdfOptions.collectAsStateWithLifecycle()
    val cvDraft by viewModel.cvDraft.collectAsStateWithLifecycle()

    var currentTab by rememberSaveable { mutableStateOf(MainNavTab.HOME) }
    var subScreen by remember { mutableStateOf<ActiveSubScreen>(ActiveSubScreen.None) }
    var showQuickActionSheet by remember { mutableStateOf(false) }

    fun navigateToTool(tool: ToolDefinition) {
        viewModel.clearExecutionResult()
        viewModel.recordToolOpened(tool)
        subScreen = when (tool.engineType) {
            EngineType.IMAGE_TO_PDF_WORKFLOW -> ActiveSubScreen.ImageToPdf
            EngineType.CV_STUDIO_WORKFLOW -> ActiveSubScreen.CvMakerStudio
            else -> ActiveSubScreen.ToolWorkspace(tool.id)
        }
    }

    ToolverseTheme(
        themeMode = userProfile?.themeMode ?: "SYSTEM",
        fontChoice = userProfile?.fontChoice ?: "JAKARTA",
        compactMode = userProfile?.compactMode ?: false
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isExpandedScreen = maxWidth >= 700.dp

            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                contentWindowInsets = WindowInsets.safeDrawing,
                bottomBar = {
                    if (!isExpandedScreen) {
                        ToolverseBottomBar(
                            currentTab = currentTab,
                            onSelectTab = { tab ->
                                subScreen = ActiveSubScreen.None
                                currentTab = tab
                            },
                            onQuickActionClick = { showQuickActionSheet = true }
                        )
                    }
                }
            ) { innerPadding ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    if (isExpandedScreen) {
                        NavigationRail(
                            containerColor = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            NavigationRailItem(
                                selected = currentTab == MainNavTab.HOME && subScreen == ActiveSubScreen.None,
                                onClick = {
                                    subScreen = ActiveSubScreen.None
                                    currentTab = MainNavTab.HOME
                                },
                                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                label = { Text("Home") }
                            )
                            NavigationRailItem(
                                selected = currentTab == MainNavTab.TOOLS || subScreen is ActiveSubScreen.CategoryBrowse,
                                onClick = {
                                    subScreen = ActiveSubScreen.None
                                    currentTab = MainNavTab.TOOLS
                                },
                                icon = { Icon(Icons.Default.GridView, contentDescription = "Tools") },
                                label = { Text("Tools") }
                            )
                            NavigationRailItem(
                                selected = false,
                                onClick = { showQuickActionSheet = true },
                                icon = {
                                    Box(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.primary),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(Icons.Default.Bolt, contentDescription = "Quick Action", tint = Color.White)
                                    }
                                },
                                label = { Text("Create") }
                            )
                            NavigationRailItem(
                                selected = currentTab == MainNavTab.HISTORY,
                                onClick = {
                                    subScreen = ActiveSubScreen.None
                                    currentTab = MainNavTab.HISTORY
                                },
                                icon = { Icon(Icons.Default.History, contentDescription = "History") },
                                label = { Text("History") }
                            )
                            NavigationRailItem(
                                selected = currentTab == MainNavTab.PROFILE,
                                onClick = {
                                    subScreen = ActiveSubScreen.None
                                    currentTab = MainNavTab.PROFILE
                                },
                                icon = { Icon(Icons.Default.PersonOutline, contentDescription = "Profile") },
                                label = { Text("Profile") }
                            )
                        }
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        when (val active = subScreen) {
                            is ActiveSubScreen.CategoryBrowse -> {
                                CategoryAndToolsScreen(
                                    initialCategory = active.category,
                                    favorites = favorites,
                                    onBack = { subScreen = ActiveSubScreen.None },
                                    onOpenTool = { navigateToTool(it) },
                                    onToggleFavorite = { viewModel.toggleFavorite(it) }
                                )
                            }

                            is ActiveSubScreen.ImageToPdf -> {
                                ImageToPdfWorkflowScreen(
                                    selectedUris = selectedImageUris,
                                    options = imageToPdfOptions,
                                    isProcessing = isProcessing,
                                    progressCurrent = progressCurrent,
                                    progressTotal = progressTotal,
                                    executionResult = executionResult,
                                    onBack = { subScreen = ActiveSubScreen.None },
                                    onAddImages = { viewModel.addImagesForPdf(it) },
                                    onRemoveImage = { viewModel.removeImageAt(it) },
                                    onMoveImage = { from, to -> viewModel.moveImage(from, to) },
                                    onClearImages = { viewModel.clearSelectedImages() },
                                    onUpdateOptions = { viewModel.updateImageToPdfOptions(it) },
                                    onConvert = { batch50 -> viewModel.runImageToPdfConversion(context, batch50) },
                                    onCancel = { viewModel.cancelActiveProcessing() },
                                    onShareFile = { path, mime -> viewModel.shareGeneratedFile(context, path, mime) }
                                )
                            }

                            is ActiveSubScreen.CvMakerStudio -> {
                                CvMakerStudioScreen(
                                    cvDraft = cvDraft,
                                    cloudDrafts = cloudDrafts,
                                    isProcessing = isProcessing,
                                    executionResult = executionResult,
                                    statusBanner = statusBanner,
                                    onBack = { subScreen = ActiveSubScreen.None },
                                    onUpdateCv = { viewModel.updateCvDraft(it) },
                                    onUpdatePhoto = { viewModel.updateCvPhoto(it) },
                                    onSaveDraftToCloud = { viewModel.saveCurrentCvDraftToCloud() },
                                    onLoadDraftFromCloud = { viewModel.loadCloudDraft(it) },
                                    onDeleteCloudDraft = { viewModel.deleteCloudDraft(it) },
                                    onExportPdf = { viewModel.exportCvToPdf(context) },
                                    onShareFile = { path, mime -> viewModel.shareGeneratedFile(context, path, mime) }
                                )
                            }

                            is ActiveSubScreen.ToolWorkspace -> {
                                val tool = ToolCatalog.getToolById(active.toolId) ?: ToolCatalog.allTools.first()
                                InteractiveToolWorkspaceScreen(
                                    tool = tool,
                                    isFavorite = favorites.any { it.toolId == tool.id },
                                    isProcessing = isProcessing,
                                    executionResult = executionResult,
                                    onBack = { subScreen = ActiveSubScreen.None },
                                    onToggleFavorite = { viewModel.toggleFavorite(tool) },
                                    onExecute = { input, param, uris ->
                                        viewModel.executeToolWorkflow(context, tool, input, param, uris)
                                    },
                                    onShareFile = { path, mime -> viewModel.shareGeneratedFile(context, path, mime) }
                                )
                            }

                            ActiveSubScreen.None -> {
                                when (currentTab) {
                                    MainNavTab.HOME -> {
                                        HomeDashboardScreen(
                                            userName = userProfile?.displayName ?: userDisplayName,
                                            searchQuery = searchQuery,
                                            onSearchQueryChange = { viewModel.updateSearchQuery(it) },
                                            recentTools = recentTools,
                                            favorites = favorites,
                                            onOpenCategory = { cat ->
                                                subScreen = ActiveSubScreen.CategoryBrowse(cat)
                                            },
                                            onOpenAllTools = {
                                                currentTab = MainNavTab.TOOLS
                                            },
                                            onOpenTool = { navigateToTool(it) },
                                            onOpenCvMaker = {
                                                viewModel.clearExecutionResult()
                                                subScreen = ActiveSubScreen.CvMakerStudio
                                            }
                                        )
                                    }

                                    MainNavTab.TOOLS -> {
                                        CategoryAndToolsScreen(
                                            initialCategory = null,
                                            favorites = favorites,
                                            onBack = null,
                                            onOpenTool = { navigateToTool(it) },
                                            onToggleFavorite = { viewModel.toggleFavorite(it) }
                                        )
                                    }

                                    MainNavTab.HISTORY -> {
                                        HistoryScreen(
                                            historyItems = historyItems,
                                            onOpenToolById = { id ->
                                                ToolCatalog.getToolById(id)?.let { navigateToTool(it) }
                                            },
                                            onShareHistoryOutput = { item ->
                                                viewModel.shareGeneratedFile(context, item.outputUri, "*/*")
                                            },
                                            onDeleteHistoryItem = { viewModel.deleteHistoryItem(it) },
                                            onClearAllHistory = { viewModel.clearAllHistory() },
                                            onBrowseTools = { currentTab = MainNavTab.TOOLS }
                                        )
                                    }

                                    MainNavTab.PROFILE -> {
                                        ProfileAndSettingsScreen(
                                            userEmail = userEmail,
                                            userDisplayName = userProfile?.displayName ?: userDisplayName,
                                            userProfile = userProfile,
                                            favoritesCount = favorites.size,
                                            historyCount = historyItems.size,
                                            draftsCount = cloudDrafts.size,
                                            onUpdateTheme = { viewModel.updateThemeMode(it) },
                                            onUpdateFont = { viewModel.updateFontChoice(it) },
                                            onUpdateNotifications = { viewModel.updateNotifications(it) },
                                            onUpdateAutoCleanCache = { viewModel.updateAutoCleanCache(it) },
                                            onUpdateCompactMode = { viewModel.updateCompactMode(it) },
                                            onExportAccountData = { viewModel.exportAccountData(context) },
                                            onDeleteAccountAndData = {
                                                viewModel.deleteAccountAndCloudData {
                                                    signOutUser(context, credentialManager, onSignedOut, coroutineScope)
                                                }
                                            },
                                            onSignOut = {
                                                signOutUser(context, credentialManager, onSignedOut, coroutineScope)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Central Action Button Modal Launcher Sheet
        if (showQuickActionSheet) {
            ModalBottomSheet(
                onDismissRequest = { showQuickActionSheet = false },
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Quick Create & Convert",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Jump straight into a high-frequency workflow:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    val shortcuts = listOf(
                        Triple("pdf_image_to_pdf", "Image to PDF (50+ Batch & Custom DPI)", Icons.Default.PhotoLibrary),
                        Triple("video_trimmer_cutter", "Video Trimmer, Cutter & Studio (20 Tools)", Icons.Default.VideoSettings),
                        Triple("img_custom_resolution_export", "Custom Resolution & DPI Exporter (720p–4K)", Icons.Default.Image),
                        Triple("cv_resume_builder", "Create Professional CV & Resume", Icons.Default.Badge),
                        Triple("pdf_merge", "Merge PDF Documents", Icons.Default.PictureAsPdf),
                        Triple("qr_generator", "Generate High-Contrast QR Code", Icons.Default.QrCode2),
                        Triple("sec_password_generator", "Generate Cryptographic Password", Icons.Default.Lock)
                    )

                    shortcuts.forEach { (toolId, label, icon) ->
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    showQuickActionSheet = false
                                    ToolCatalog.getToolById(toolId)?.let { navigateToTool(it) }
                                }
                                .padding(horizontal = 16.dp, vertical = 14.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                Text(label, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun ToolverseBottomBar(
    currentTab: MainNavTab,
    onSelectTab: (MainNavTab) -> Unit,
    onQuickActionClick: () -> Unit
) {
    val isCompact = LocalCompactMode.current
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = if (isCompact) 6.dp else 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 0.8.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(horizontal = if (isCompact) 8.dp else 12.dp, vertical = if (isCompact) 4.dp else 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                label = "Home",
                selected = currentTab == MainNavTab.HOME,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                onClick = { onSelectTab(MainNavTab.HOME) },
                testTag = "nav_tab_home"
            )

            BottomBarItem(
                label = "Tools",
                selected = currentTab == MainNavTab.TOOLS,
                selectedIcon = Icons.Filled.GridView,
                unselectedIcon = Icons.Outlined.GridView,
                onClick = { onSelectTab(MainNavTab.TOOLS) },
                testTag = "nav_tab_tools"
            )

            // Central Green Lightning Action Button (matching 5-screen Mockup)
            Box(
                modifier = Modifier
                    .size(if (isCompact) 48.dp else 52.dp)
                    .shadow(6.dp, CircleShape, spotColor = Color(0xFF0F5338))
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable(onClick = onQuickActionClick)
                    .testTag("nav_central_action_button"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = "Quick Action Launcher",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(if (isCompact) 22.dp else 26.dp)
                )
            }

            BottomBarItem(
                label = "History",
                selected = currentTab == MainNavTab.HISTORY,
                selectedIcon = Icons.Filled.History,
                unselectedIcon = Icons.Outlined.History,
                onClick = { onSelectTab(MainNavTab.HISTORY) },
                testTag = "nav_tab_history"
            )

            BottomBarItem(
                label = "Profile",
                selected = currentTab == MainNavTab.PROFILE,
                selectedIcon = Icons.Filled.PersonOutline,
                unselectedIcon = Icons.Outlined.PersonOutline,
                onClick = { onSelectTab(MainNavTab.PROFILE) },
                testTag = "nav_tab_profile"
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    label: String,
    selected: Boolean,
    selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    testTag: String
) {
    val tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
    Column(
        modifier = Modifier
            .size(width = 64.dp, height = 48.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (selected) selectedIcon else unselectedIcon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = tint,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 11.sp
            )
        )
    }
}
