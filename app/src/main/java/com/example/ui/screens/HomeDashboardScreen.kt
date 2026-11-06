package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.RecentToolEntity
import com.example.data.model.FavoriteTool
import com.example.domain.catalog.ToolCatalog
import com.example.domain.catalog.ToolCategory
import com.example.domain.catalog.ToolDefinition
import com.example.ui.theme.LocalCompactMode

@Composable
fun HomeDashboardScreen(
    userName: String,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    recentTools: List<RecentToolEntity>,
    favorites: List<FavoriteTool>,
    onOpenCategory: (ToolCategory) -> Unit,
    onOpenAllTools: () -> Unit,
    onOpenTool: (ToolDefinition) -> Unit,
    onOpenCvMaker: () -> Unit
) {
    val isCompact = LocalCompactMode.current
    val totalToolCount = remember { ToolCatalog.allTools.size }
    val searchResults = remember(searchQuery) {
        if (searchQuery.isBlank()) emptyList() else ToolCatalog.searchTools(searchQuery)
    }
    var showNotificationsDialog by remember { mutableStateOf(false) }

    val popularCategories = remember {
        listOf(
            ToolCategory.PDF,
            ToolCategory.IMAGE,
            ToolCategory.VIDEO,
            ToolCategory.CV,
            ToolCategory.DATA,
            ToolCategory.TEXT,
            ToolCategory.FILE,
            ToolCategory.SECURITY,
            ToolCategory.CALC,
            ToolCategory.QR,
            ToolCategory.AUDIO,
            ToolCategory.DEV
        )
    }

    val quickAccessTools = remember {
        listOfNotNull(
            ToolCatalog.getToolById("pdf_image_to_pdf"),
            ToolCatalog.getToolById("video_trimmer_cutter"),
            ToolCatalog.getToolById("video_compressor_custom_size"),
            ToolCatalog.getToolById("calc_bill_splitter"),
            ToolCatalog.getToolById("calc_currency_converter_live"),
            ToolCatalog.getToolById("img_custom_resolution_export"),
            ToolCatalog.getToolById("pdf_to_word"),
            ToolCatalog.getToolById("cv_resume_builder"),
            ToolCatalog.getToolById("qr_generator"),
            ToolCatalog.getToolById("sec_totp_generator")
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_dashboard_list"),
        contentPadding = PaddingValues(
            horizontal = if (isCompact) 12.dp else 20.dp,
            vertical = if (isCompact) 8.dp else 14.dp
        ),
        verticalArrangement = Arrangement.spacedBy(if (isCompact) 10.dp else 18.dp)
    ) {
        // Top App Bar Row (Menu / All Tools + Verified Free Badge + Notification Bell)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onOpenAllTools,
                    modifier = Modifier
                        .size(48.dp)
                        .testTag("home_menu_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Browse Full Catalogue",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.clickable { onOpenAllTools() }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "$totalToolCount Free Tools",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                        }
                    }

                    IconButton(
                        onClick = { showNotificationsDialog = true },
                        modifier = Modifier
                            .size(48.dp)
                            .testTag("home_notifications_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.NotificationsNone,
                            contentDescription = "Workspace Status & Notifications",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }

        // Greeting & Headline (Screen 2 Mockup)
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                val firstName = userName.substringBefore(" ").ifBlank { "There" }
                Text(
                    text = "Hello, $firstName",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Explore $totalToolCount+ Tools",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "All your productivity needs in one place.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        // Global Search Bar across tool names, aliases & categories
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = {
                    Text(
                        text = "Search tools...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search tools",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_search_input")
            )
        }

        // Live Search Results (if user is typing)
        if (searchQuery.isNotBlank()) {
            item {
                Text(
                    text = "Search Results (${searchResults.size})",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            if (searchResults.isEmpty()) {
                item {
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "No tools matched \"$searchQuery\". Try searching for PDF, PNG, CSV, SHA-256, QR, or CV.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                items(searchResults, key = { it.id }) { tool ->
                    ToolRowItemCard(
                        tool = tool,
                        isFavorite = favorites.any { it.toolId == tool.id },
                        onClick = { onOpenTool(tool) },
                        onToggleFavorite = null
                    )
                }
            }
        } else {
            // Featured Tool Banner: Create Professional Resume & CV (Screen 2 Mockup)
            item {
                FeaturedCvBannerCard(onClick = onOpenCvMaker)
            }

            // Popular Categories Header + 3x3 Grid with Dynamic Counts from ToolCatalog
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Popular Categories",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onOpenAllTools() }
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                            .testTag("see_all_categories_button"),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "See All",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(if (isCompact) 8.dp else 12.dp))

                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    val columns = when {
                        maxWidth >= 680.dp -> if (isCompact) 6 else 5
                        maxWidth >= 480.dp -> if (isCompact) 5 else 4
                        maxWidth < 340.dp -> if (isCompact) 3 else 2
                        else -> if (isCompact) 4 else 3
                    }
                    val gridSpacing = if (isCompact) 6.dp else 10.dp
                    Column(verticalArrangement = Arrangement.spacedBy(gridSpacing)) {
                        for (rowCategories in popularCategories.chunked(columns)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(gridSpacing)
                            ) {
                                for (cat in rowCategories) {
                                    val dynamicCount = ToolCatalog.getCategoryCount(cat)
                                    CategoryTileCard(
                                        category = cat,
                                        toolCount = dynamicCount,
                                        onClick = {
                                            if (cat == ToolCategory.CV) {
                                                onOpenCvMaker()
                                            } else {
                                                onOpenCategory(cat)
                                            }
                                        },
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                repeat(columns - rowCategories.size) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }

            // Quick Access Row (Screen 2 Mockup)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quick Access",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Instant Launch",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(end = 4.dp)
                ) {
                    items(quickAccessTools, key = { it.id }) { tool ->
                        QuickAccessMiniCard(
                            tool = tool,
                            onClick = { onOpenTool(tool) }
                        )
                    }
                }
            }

            // Recently Used Tools (if any)
            if (recentTools.isNotEmpty()) {
                item {
                    Text(
                        text = "Recently Used",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                items(recentTools.take(4), key = { it.toolId }) { recent ->
                    val resolved = ToolCatalog.getToolById(recent.toolId)
                    if (resolved != null) {
                        ToolRowItemCard(
                            tool = resolved,
                            isFavorite = favorites.any { it.toolId == resolved.id },
                            onClick = { onOpenTool(resolved) },
                            onToggleFavorite = null
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (showNotificationsDialog) {
        AlertDialog(
            onDismissRequest = { showNotificationsDialog = false },
            title = { Text("Toolverse Engine Status", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("• All $totalToolCount utilities are active and verified for offline-first execution.")
                    Text("• Cloud Firestore sync is connected for your Favorites, Task History, and CV Drafts.")
                    Text("• Memory-safe batch Image-to-PDF engine supports 50+ high-res images.")
                }
            },
            confirmButton = {
                TextButton(onClick = { showNotificationsDialog = false }) {
                    Text("Done")
                }
            }
        )
    }
}

@Composable
private fun FeaturedCvBannerCard(onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .testTag("featured_cv_banner")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF0A3926),
                            Color(0xFF14593D),
                            Color(0xFF285F88)
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
                        text = "Create Professional\nResume & CV",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 25.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Start Now",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = Color(0xFFA7F3D0),
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color(0xFFA7F3D0),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Stylized Mini Resume Illustration Card on Right (matching Screen 2 mockup)
                Box(contentAlignment = Alignment.BottomEnd) {
                    Surface(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp),
                        shadowElevation = 6.dp,
                        modifier = Modifier.size(width = 86.dp, height = 98.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(18.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF3B82F6))
                                )
                                Box(
                                    modifier = Modifier
                                        .height(6.dp)
                                        .width(34.dp)
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(Color(0xFF1E293B))
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(5.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFF94A3B8))
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .height(5.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFFCBD5E1))
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(5.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFF3B82F6).copy(alpha = 0.5f))
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .height(5.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFFCBD5E1))
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF10B981)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryTileCard(
    category: ToolCategory,
    toolCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isCompact = LocalCompactMode.current
    val cornerRadius = if (isCompact) 13.dp else 18.dp
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(cornerRadius))
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(onClick = onClick)
            .testTag("category_tile_${category.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = if (isCompact) 8.dp else 14.dp,
                    horizontal = if (isCompact) 4.dp else 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(if (isCompact) 34.dp else 44.dp)
                    .clip(RoundedCornerShape(if (isCompact) 9.dp else 13.dp))
                    .background(category.containerColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = category.icon,
                    contentDescription = category.shortTitle,
                    tint = category.accentColor,
                    modifier = Modifier.size(if (isCompact) 18.dp else 24.dp)
                )
            }
            Spacer(modifier = Modifier.height(if (isCompact) 4.dp else 8.dp))
            Text(
                text = category.shortTitle,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (isCompact) 11.sp else 12.5.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Text(
                text = "$toolCount+",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    fontSize = if (isCompact) 10.sp else 11.sp
                )
            )
        }
    }
}

@Composable
private fun QuickAccessMiniCard(
    tool: ToolDefinition,
    onClick: () -> Unit
) {
    val isCompact = LocalCompactMode.current
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(if (isCompact) 12.dp else 16.dp),
        modifier = Modifier
            .width(if (isCompact) 76.dp else 88.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(if (isCompact) 12.dp else 16.dp))
            .clip(RoundedCornerShape(if (isCompact) 12.dp else 16.dp))
            .clickable(onClick = onClick)
            .padding(vertical = if (isCompact) 8.dp else 12.dp, horizontal = if (isCompact) 6.dp else 8.dp)
            .testTag("quick_access_${tool.id}")
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(if (isCompact) 32.dp else 38.dp)
                    .clip(RoundedCornerShape(if (isCompact) 9.dp else 11.dp))
                    .background(tool.category.containerColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = tool.icon,
                    contentDescription = tool.name,
                    tint = tool.category.accentColor,
                    modifier = Modifier.size(if (isCompact) 17.dp else 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(if (isCompact) 4.dp else 7.dp))
            Text(
                text = tool.name,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = if (isCompact) 11.5.sp else 13.sp,
                    fontSize = if (isCompact) 10.sp else 11.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ==================== SCREEN 3: CATEGORY LISTING / PDF TOOLS & FULL CATALOGUE ====================

@Composable
fun CategoryAndToolsScreen(
    initialCategory: ToolCategory?,
    favorites: List<FavoriteTool>,
    onBack: (() -> Unit)?,
    onOpenTool: (ToolDefinition) -> Unit,
    onToggleFavorite: (ToolDefinition) -> Unit
) {
    if (onBack != null) {
        BackHandler { onBack() }
    }

    var activeCategory by remember(initialCategory) { mutableStateOf(initialCategory) }
    var searchQuery by remember { mutableStateOf("") }
    var showSearchBar by remember { mutableStateOf(false) }
    var showFavoritesOnly by remember { mutableStateOf(false) }

    val toolsToShow = remember(activeCategory, searchQuery, showFavoritesOnly, favorites) {
        val base = ToolCatalog.searchTools(searchQuery, activeCategory)
        if (showFavoritesOnly) {
            base.filter { t -> favorites.any { it.toolId == t.id } }
        } else {
            base
        }
    }

    val heroCategory = activeCategory ?: ToolCategory.PDF
    val dynamicCategoryCount = remember(heroCategory) {
        if (activeCategory != null) {
            ToolCatalog.getCategoryCount(heroCategory)
        } else {
            ToolCatalog.allTools.size
        }
    }

    val isCompact = LocalCompactMode.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = if (isCompact) 12.dp else 20.dp)
    ) {
        // Top Header Bar (matching Screen 3 Mockup: Back arrow, Category Title, Search icon)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = if (isCompact) 6.dp else 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBack != null) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(48.dp)
                        .testTag("category_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }

            Text(
                text = activeCategory?.shortTitle ?: "All Tools (${ToolCatalog.allTools.size})",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            IconButton(
                onClick = { showSearchBar = !showSearchBar },
                modifier = Modifier
                    .size(48.dp)
                    .testTag("category_search_toggle")
            ) {
                Icon(
                    imageVector = if (showSearchBar) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = "Search category tools"
                )
            }
        }

        if (showSearchBar) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Filter tools by name or format…") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .testTag("category_search_input")
            )
        }

        // Category Filter Chips (when browsing full catalogue or switching categories)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 12.dp)
        ) {
            item {
                FilterChip(
                    selected = activeCategory == null && !showFavoritesOnly,
                    onClick = {
                        activeCategory = null
                        showFavoritesOnly = false
                    },
                    label = { Text("All (${ToolCatalog.allTools.size})") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
            item {
                FilterChip(
                    selected = showFavoritesOnly,
                    onClick = { showFavoritesOnly = !showFavoritesOnly },
                    label = { Text("Favorites (${favorites.size})") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
            items(ToolCategory.entries) { cat ->
                val count = ToolCatalog.getCategoryCount(cat)
                FilterChip(
                    selected = activeCategory == cat && !showFavoritesOnly,
                    onClick = {
                        activeCategory = cat
                        showFavoritesOnly = false
                    },
                    label = { Text("${cat.shortTitle} ($count)") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("category_tools_list"),
            verticalArrangement = Arrangement.spacedBy(if (isCompact) 6.dp else 10.dp),
            contentPadding = PaddingValues(bottom = if (isCompact) 16.dp else 24.dp)
        ) {
            // Hero Banner Card (matching Screen 3 Mockup: soft mint gradient card with red PDF icon & white arrow circle)
            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFD9F3E4),
                                        Color(0xFFB7E9CB)
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
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Surface(
                                    color = heroCategory.accentColor,
                                    shape = RoundedCornerShape(16.dp),
                                    shadowElevation = 4.dp,
                                    modifier = Modifier.size(58.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = heroCategory.icon,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                }
                                Column {
                                    Text(
                                        text = activeCategory?.shortTitle ?: "Toolverse Catalogue",
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF0E291E)
                                        )
                                    )
                                    Text(
                                        text = "$dynamicCategoryCount+ Powerful Utilities",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color(0xFF1F4D39),
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                            }

                            Surface(
                                color = Color.White,
                                shape = CircleShape,
                                shadowElevation = 2.dp,
                                modifier = Modifier.size(38.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        tint = Color(0xFF0F5338),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (toolsToShow.isEmpty()) {
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
                                text = if (showFavoritesOnly) "No Favorite Tools Yet" else "No Matching Tools",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (showFavoritesOnly) {
                                    "Bookmark any tool using the ribbon icon to pin it here for instant access."
                                } else {
                                    "Try clearing your search filter or choosing another category."
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                items(toolsToShow, key = { it.id }) { tool ->
                    ToolRowItemCard(
                        tool = tool,
                        isFavorite = favorites.any { it.toolId == tool.id },
                        onClick = { onOpenTool(tool) },
                        onToggleFavorite = { onToggleFavorite(tool) }
                    )
                }
            }
        }
    }
}

@Composable
fun ToolRowItemCard(
    tool: ToolDefinition,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onToggleFavorite: (() -> Unit)?
) {
    val isCompact = LocalCompactMode.current
    val cornerRadius = if (isCompact) 13.dp else 18.dp
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(cornerRadius),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(cornerRadius))
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(onClick = onClick)
            .testTag("tool_row_${tool.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = if (isCompact) 12.dp else 16.dp,
                    vertical = if (isCompact) 8.dp else 14.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(if (isCompact) 10.dp else 14.dp),
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(if (isCompact) 38.dp else 46.dp)
                        .clip(RoundedCornerShape(if (isCompact) 10.dp else 13.dp))
                        .background(tool.category.containerColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = tool.icon,
                        contentDescription = null,
                        tint = tool.category.accentColor,
                        modifier = Modifier.size(if (isCompact) 20.dp else 24.dp)
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = tool.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = if (isCompact) 14.sp else 16.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(if (isCompact) 1.dp else 2.dp))
                    Text(
                        text = tool.shortPurpose,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = if (isCompact) 11.5.sp else 12.5.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (onToggleFavorite != null) {
                    IconButton(
                        onClick = onToggleFavorite,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isFavorite) "Remove favorite" else "Add favorite",
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(if (isCompact) 18.dp else 20.dp)
                        )
                    }
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Open ${tool.name}",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
