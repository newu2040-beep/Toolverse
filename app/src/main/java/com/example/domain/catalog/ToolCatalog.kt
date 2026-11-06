package com.example.domain.catalog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.CallSplit
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.automirrored.filled.MergeType
import androidx.compose.material.icons.automirrored.filled.NoteAdd
import androidx.compose.material.icons.automirrored.filled.RotateRight
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.automirrored.filled.TextSnippet
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material.icons.filled.EnhancedEncryption
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.FilterFrames
import androidx.compose.material.icons.filled.FindReplace
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.FolderZip
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Html
import androidx.compose.material.icons.filled.Http
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.PhotoSizeSelectLarge
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.QueryBuilder
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Unarchive
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.ViewColumn
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ui.theme.CatAudioAccent
import com.example.ui.theme.CatAudioBg
import com.example.ui.theme.CatCalcAccent
import com.example.ui.theme.CatCalcBg
import com.example.ui.theme.CatCvAccent
import com.example.ui.theme.CatCvBg
import com.example.ui.theme.CatDataAccent
import com.example.ui.theme.CatDataBg
import com.example.ui.theme.CatDevAccent
import com.example.ui.theme.CatDevBg
import com.example.ui.theme.CatFileAccent
import com.example.ui.theme.CatFileBg
import com.example.ui.theme.CatImageAccent
import com.example.ui.theme.CatImageBg
import com.example.ui.theme.CatPdfAccent
import com.example.ui.theme.CatPdfBg
import com.example.ui.theme.CatQrAccent
import com.example.ui.theme.CatQrBg
import com.example.ui.theme.CatSecurityAccent
import com.example.ui.theme.CatSecurityBg
import com.example.ui.theme.CatTextAccent
import com.example.ui.theme.CatTextBg
import com.example.ui.theme.CatVideoAccent
import com.example.ui.theme.CatVideoBg

enum class ToolCategory(
    val id: String,
    val title: String,
    val shortTitle: String,
    val description: String,
    val accentColor: Color,
    val containerColor: Color,
    val icon: ImageVector
) {
    PDF(
        id = "PDF",
        title = "PDF & Document Tools",
        shortTitle = "PDF Tools",
        description = "Convert, merge, split, compress, watermark, inspect, and secure PDF documents on-device.",
        accentColor = CatPdfAccent,
        containerColor = CatPdfBg,
        icon = Icons.Default.PictureAsPdf
    ),
    IMAGE(
        id = "IMAGE",
        title = "Image & Graphics Tools",
        shortTitle = "Image Tools",
        description = "Convert between PNG, JPEG, WEBP, BMP, TIFF, resize, crop, inspect quality, and scrub EXIF metadata.",
        accentColor = CatImageAccent,
        containerColor = CatImageBg,
        icon = Icons.Default.Image
    ),
    CV(
        id = "CV",
        title = "CV, Forms & Career Studio",
        shortTitle = "CV Maker",
        description = "Build ATS-ready resumes, career letters, experience certificates, and fillable PDF forms.",
        accentColor = CatCvAccent,
        containerColor = CatCvBg,
        icon = Icons.Default.Badge
    ),
    DATA(
        id = "DATA",
        title = "Data & Spreadsheet Tools",
        shortTitle = "Data Tools",
        description = "Transform, validate, and clean CSV, JSON, XML, YAML, and tabular spreadsheet datasets.",
        accentColor = CatDataAccent,
        containerColor = CatDataBg,
        icon = Icons.Default.Analytics
    ),
    TEXT(
        id = "TEXT",
        title = "Text & Writing Utilities",
        shortTitle = "Text Tools",
        description = "Analyze words, clean whitespace, convert cases, compare diffs, sort lines, and preview Markdown.",
        accentColor = CatTextAccent,
        containerColor = CatTextBg,
        icon = Icons.AutoMirrored.Filled.TextSnippet
    ),
    FILE(
        id = "FILE",
        title = "File & Archive Tools",
        shortTitle = "File Tools",
        description = "Create and extract ZIP archives, batch rename files, inspect MIME headers, and verify checksums.",
        accentColor = CatFileAccent,
        containerColor = CatFileBg,
        icon = Icons.Default.FolderZip
    ),
    SECURITY(
        id = "SECURITY",
        title = "Security & Cryptography",
        shortTitle = "Security",
        description = "Generate high-entropy passwords, compute SHA-256/SHA-1/MD5 digests, and encrypt with AES-256-GCM.",
        accentColor = CatSecurityAccent,
        containerColor = CatSecurityBg,
        icon = Icons.Default.Shield
    ),
    CALC(
        id = "CALC",
        title = "Calculators & Converters",
        shortTitle = "Calculator",
        description = "Scientific expressions, tax/GST, discounts, dates, age, and precision unit converters.",
        accentColor = CatCalcAccent,
        containerColor = CatCalcBg,
        icon = Icons.Default.Calculate
    ),
    QR(
        id = "QR",
        title = "QR & Barcode Tools",
        shortTitle = "QR Tools",
        description = "Generate high-resolution QR codes, Wi-Fi QR, vCard QR, Code-128 barcodes, and decode images.",
        accentColor = CatQrAccent,
        containerColor = CatQrBg,
        icon = Icons.Default.QrCode2
    ),
    AUDIO(
        id = "AUDIO",
        title = "Audio & Waveform Tools",
        shortTitle = "Audio Tools",
        description = "Inspect audio bitrates, edit metadata tags, trim/merge WAV streams, and synthesize test tones.",
        accentColor = CatAudioAccent,
        containerColor = CatAudioBg,
        icon = Icons.Default.AudioFile
    ),
    DEV(
        id = "DEV",
        title = "Developer Workflows",
        shortTitle = "Dev Tools",
        description = "Base64, URL encode, UUID v4 generator, Unix timestamps, Regex tester, JWT decoder, and JSON path.",
        accentColor = CatDevAccent,
        containerColor = CatDevBg,
        icon = Icons.Default.Code
    ),
    VIDEO(
        id = "VIDEO",
        title = "Video Editing & Processing Studio",
        shortTitle = "Video Tools",
        description = "Trim, split, merge, encode H.264 MP4, adjust speed/FPS/bitrate, convert GIFs, stabilize, and enhance videos.",
        accentColor = CatVideoAccent,
        containerColor = CatVideoBg,
        icon = Icons.Default.Videocam
    )
}

enum class EngineType {
    IMAGE_TO_PDF_WORKFLOW,
    CV_STUDIO_WORKFLOW,
    PDF_DOCUMENT_ENGINE,
    CAREER_LETTER_ENGINE,
    IMAGE_GRAPHICS_ENGINE,
    DATA_SPREADSHEET_ENGINE,
    TEXT_PROCESSING_ENGINE,
    FILE_ARCHIVE_ENGINE,
    SECURITY_CRYPTO_ENGINE,
    CALCULATOR_CONVERTER_ENGINE,
    QR_BARCODE_ENGINE,
    AUDIO_MEDIA_ENGINE,
    DEVELOPER_UTILITY_ENGINE,
    VIDEO_PROCESSING_ENGINE
}

data class ToolDefinition(
    val id: String,
    val name: String,
    val shortPurpose: String,
    val category: ToolCategory,
    val icon: ImageVector,
    val supportedFormats: String,
    val engineType: EngineType,
    val searchAliases: List<String> = emptyList(),
    val defaultInputSample: String = "",
    val defaultParamOption: String = ""
)

object ToolCatalog {

    val allTools: List<ToolDefinition> = listOf(
        // ==================== 1. PDF & DOCUMENT TOOLS (22 Tools) ====================
        ToolDefinition(
            id = "pdf_image_to_pdf",
            name = "Image to PDF",
            shortPurpose = "Convert 50+ images to a single multi-page PDF",
            category = ToolCategory.PDF,
            icon = Icons.Default.PhotoLibrary,
            supportedFormats = "Input: JPG, PNG, WEBP, BMP • Output: PDF (A4/Letter/Original)",
            engineType = EngineType.IMAGE_TO_PDF_WORKFLOW,
            searchAliases = listOf("jpg to pdf", "png to pdf", "photos to pdf", "batch image pdf")
        ),
        ToolDefinition(
            id = "pdf_to_word",
            name = "PDF to Word",
            shortPurpose = "Convert PDF text & pages to editable DOCX",
            category = ToolCategory.PDF,
            icon = Icons.Default.Description,
            supportedFormats = "Input: Unencrypted PDF • Output: OOXML .docx (Best-Effort Text Layout)",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("pdf to docx", "word converter", "extract doc"),
            defaultParamOption = "DOCX_EXPORT"
        ),
        ToolDefinition(
            id = "pdf_merge",
            name = "Merge PDF",
            shortPurpose = "Combine multiple PDFs into one document",
            category = ToolCategory.PDF,
            icon = Icons.AutoMirrored.Filled.MergeType,
            supportedFormats = "Input: 2+ PDF files • Output: Combined PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("join pdf", "combine pdf", "binder"),
            defaultParamOption = "MERGE"
        ),
        ToolDefinition(
            id = "pdf_split_range",
            name = "Split PDF",
            shortPurpose = "Split PDF by custom page ranges",
            category = ToolCategory.PDF,
            icon = Icons.AutoMirrored.Filled.CallSplit,
            supportedFormats = "Input: Multi-page PDF • Output: Range-extracted PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("separate pdf", "page range", "divide pdf"),
            defaultInputSample = "1-2"
        ),
        ToolDefinition(
            id = "pdf_extract_pages",
            name = "Extract Selected PDF Pages",
            shortPurpose = "Extract specific pages (e.g., 1, 3, 5) into a new PDF",
            category = ToolCategory.PDF,
            icon = Icons.Default.FilterFrames,
            supportedFormats = "Input: PDF • Output: Selected pages PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "1, 2"
        ),
        ToolDefinition(
            id = "pdf_compress",
            name = "Compress PDF",
            shortPurpose = "Reduce PDF file size with configurable DPI & quality",
            category = ToolCategory.PDF,
            icon = Icons.Default.Compress,
            supportedFormats = "Input: PDF • Output: Optimized downsampled PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("shrink pdf", "optimize pdf", "smaller pdf"),
            defaultInputSample = "70"
        ),
        ToolDefinition(
            id = "pdf_encrypt",
            name = "Encrypt PDF",
            shortPurpose = "Add AES-256-GCM authenticated password protection",
            category = ToolCategory.PDF,
            icon = Icons.Default.Lock,
            supportedFormats = "Input: PDF + Password • Output: Encrypted .tvpdf / Protected PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("lock pdf", "password protect pdf"),
            defaultInputSample = "StrongPass#2026"
        ),
        ToolDefinition(
            id = "pdf_unlock",
            name = "Unlock PDF",
            shortPurpose = "Decrypt protected PDF package with valid password",
            category = ToolCategory.PDF,
            icon = Icons.Default.LockOpen,
            supportedFormats = "Input: Protected PDF + Valid Password • Output: Unlocked PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("decrypt pdf", "remove password"),
            defaultInputSample = "StrongPass#2026"
        ),
        ToolDefinition(
            id = "pdf_to_png",
            name = "PDF to Images (PNG)",
            shortPurpose = "Render PDF pages into crisp lossless PNG images",
            category = ToolCategory.PDF,
            icon = Icons.Default.Image,
            supportedFormats = "Input: PDF • Output: Lossless PNG pages",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("pdf to image", "pdf to png", "rasterize pdf")
        ),
        ToolDefinition(
            id = "pdf_to_jpeg",
            name = "PDF to JPEG",
            shortPurpose = "Convert PDF pages to compact JPEG images",
            category = ToolCategory.PDF,
            icon = Icons.Default.PhotoLibrary,
            supportedFormats = "Input: PDF • Output: JPEG pages (90% quality)",
            engineType = EngineType.PDF_DOCUMENT_ENGINE
        ),
        ToolDefinition(
            id = "pdf_viewer",
            name = "PDF Viewer & Inspector",
            shortPurpose = "View pages, inspect dimensions, and verify integrity",
            category = ToolCategory.PDF,
            icon = Icons.Default.RemoveRedEye,
            supportedFormats = "Input: Standard PDF 1.4–1.7",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("open pdf", "read pdf", "preview pdf")
        ),
        ToolDefinition(
            id = "pdf_rotate",
            name = "Rotate PDF Pages",
            shortPurpose = "Rotate all or selected PDF pages by 90°, 180°, or 270°",
            category = ToolCategory.PDF,
            icon = Icons.AutoMirrored.Filled.RotateRight,
            supportedFormats = "Input: PDF • Output: Rotated PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "90"
        ),
        ToolDefinition(
            id = "pdf_reorder",
            name = "Reorder PDF Pages",
            shortPurpose = "Rearrange page sequence using custom page order",
            category = ToolCategory.PDF,
            icon = Icons.Default.Reorder,
            supportedFormats = "Input: PDF • Output: Reordered PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "2, 1"
        ),
        ToolDefinition(
            id = "pdf_delete_pages",
            name = "Delete PDF Pages",
            shortPurpose = "Remove unwanted pages from a PDF document",
            category = ToolCategory.PDF,
            icon = Icons.Default.DeleteSweep,
            supportedFormats = "Input: PDF • Output: Trimmed PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "2"
        ),
        ToolDefinition(
            id = "pdf_duplicate_pages",
            name = "Duplicate PDF Pages",
            shortPurpose = "Clone selected pages inside a PDF file",
            category = ToolCategory.PDF,
            icon = Icons.Default.ContentCopy,
            supportedFormats = "Input: PDF • Output: Multi-copy PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "1"
        ),
        ToolDefinition(
            id = "pdf_text_extract",
            name = "PDF Text & Metadata Extractor",
            shortPurpose = "Extract embedded text streams and document metadata",
            category = ToolCategory.PDF,
            icon = Icons.AutoMirrored.Filled.TextSnippet,
            supportedFormats = "Input: PDF • Output: Extracted UTF-8 TXT",
            engineType = EngineType.PDF_DOCUMENT_ENGINE
        ),
        ToolDefinition(
            id = "pdf_docx_to_pdf",
            name = "DOCX to PDF",
            shortPurpose = "Convert Word DOCX files into paginated PDFs",
            category = ToolCategory.PDF,
            icon = Icons.Default.Transform,
            supportedFormats = "Input: .docx XML package • Output: Paginated A4 PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "Project Proposal Document\n\n1. Executive Summary\nDelivering 100+ native Android utilities with zero paywalls."
        ),
        ToolDefinition(
            id = "pdf_txt_to_pdf",
            name = "TXT to PDF",
            shortPurpose = "Format plain text notes into clean A4 PDF pages",
            category = ToolCategory.PDF,
            icon = Icons.Default.Description,
            supportedFormats = "Input: UTF-8 .txt • Output: Paginated A4 PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "Meeting Notes — Architecture Sync\n\n• On-device incremental processing\n• Zero-trust Firestore security rules\n• Adaptive Material 3 layouts"
        ),
        ToolDefinition(
            id = "pdf_html_to_pdf",
            name = "HTML to PDF",
            shortPurpose = "Render structured HTML markup into a printable PDF",
            category = ToolCategory.PDF,
            icon = Icons.Default.Html,
            supportedFormats = "Input: HTML5 snippet/file • Output: A4 PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "<h1>Toolverse Invoice #1042</h1><p>Client: Acme Corp</p><p>Status: Paid in Full</p>"
        ),
        ToolDefinition(
            id = "pdf_add_watermark",
            name = "Add PDF Watermark",
            shortPurpose = "Stamp diagonal semi-transparent watermark text across pages",
            category = ToolCategory.PDF,
            icon = Icons.Default.WaterDrop,
            supportedFormats = "Input: PDF + Watermark Text • Output: Watermarked PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "CONFIDENTIAL • TOOLVERSE"
        ),
        ToolDefinition(
            id = "pdf_add_page_numbers",
            name = "Add Page Numbers",
            shortPurpose = "Stamp 'Page X of Y' footers onto every PDF page",
            category = ToolCategory.PDF,
            icon = Icons.Default.FormatListNumbered,
            supportedFormats = "Input: PDF • Output: Numbered PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "Bottom Right"
        ),
        ToolDefinition(
            id = "pdf_sign_and_fill",
            name = "Sign & Fill PDF Form",
            shortPurpose = "Stamp digital signature block and form annotations onto PDF",
            category = ToolCategory.PDF,
            icon = Icons.Default.Draw,
            supportedFormats = "Input: PDF + Signature/Fields • Output: Flattened Signed PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "Signed by: Alex Morgan | Date: 2026-11-05"
        ),

        // ==================== 2. CV, FORMS & CAREER DOCUMENTS (16 Tools) ====================
        ToolDefinition(
            id = "cv_resume_builder",
            name = "CV & Resume Builder",
            shortPurpose = "Build ATS-friendly and executive multi-section CVs",
            category = ToolCategory.CV,
            icon = Icons.Default.Badge,
            supportedFormats = "Output: Print-ready A4 PDF + Cloud Draft Sync",
            engineType = EngineType.CV_STUDIO_WORKFLOW,
            searchAliases = listOf("resume maker", "cv maker", "curriculum vitae", "job resume")
        ),
        ToolDefinition(
            id = "cv_templates_gallery",
            name = "Multiple CV Templates Studio",
            shortPurpose = "Switch between ATS Classic, Executive, Tech & Minimal layouts",
            category = ToolCategory.CV,
            icon = Icons.Default.GridOn,
            supportedFormats = "4 Production Templates • Multi-page A4 PDF",
            engineType = EngineType.CV_STUDIO_WORKFLOW
        ),
        ToolDefinition(
            id = "cv_photo_crop_studio",
            name = "CV Profile Photo Crop & Position",
            shortPurpose = "Prepare circular/rounded portrait photo for CV headers",
            category = ToolCategory.CV,
            icon = Icons.Default.AccountBox,
            supportedFormats = "Input: JPG/PNG Portrait • Output: 400x400 Avatar PNG & CV PDF",
            engineType = EngineType.CV_STUDIO_WORKFLOW
        ),
        ToolDefinition(
            id = "cv_pdf_exporter",
            name = "CV Direct Export to PDF",
            shortPurpose = "Export saved resume draft directly to high-DPI PDF",
            category = ToolCategory.CV,
            icon = Icons.Default.PictureAsPdf,
            supportedFormats = "Output: Paginated A4 PDF",
            engineType = EngineType.CV_STUDIO_WORKFLOW
        ),
        ToolDefinition(
            id = "cv_resume_import_parser",
            name = "Resume Import & Field Extractor",
            shortPurpose = "Parse pasted resume text into structured CV sections",
            category = ToolCategory.CV,
            icon = Icons.Default.AutoFixHigh,
            supportedFormats = "Input: Resume Text / TXT • Output: Structured CV Draft + PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Alex Rivera\nalex.rivera@email.com | +1 555-0192\nSummary: Senior Kotlin & Android Engineer with 7 years experience.\nSkills: Kotlin, Jetpack Compose, Coroutines, Firebase, Room"
        ),
        ToolDefinition(
            id = "cv_cover_letter",
            name = "Cover Letter Maker",
            shortPurpose = "Generate tailored cover letter from your role & achievements",
            category = ToolCategory.CV,
            icon = Icons.Default.MailOutline,
            supportedFormats = "Input: User Facts • Output: Formatted Cover Letter PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Applicant: Jordan Lee\nTarget Role: Senior Product Engineer\nCompany: Linear Systems\nKey Highlight: Led mobile architecture migration improving startup time by 42%."
        ),
        ToolDefinition(
            id = "cv_job_application_letter",
            name = "Job Application Letter Maker",
            shortPurpose = "Create formal job application letters for HR submissions",
            category = ToolCategory.CV,
            icon = Icons.Default.WorkOutline,
            supportedFormats = "Input: User Facts • Output: Formal Letter PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Applicant: Jordan Lee\nPosition: Android Platform Lead\nHiring Manager: Taylor Vance\nAvailability: Immediate / 2 Weeks Notice"
        ),
        ToolDefinition(
            id = "cv_offer_letter",
            name = "Offer Letter Template Maker",
            shortPurpose = "Draft structured employment offer letters with user-supplied terms",
            category = ToolCategory.CV,
            icon = Icons.Default.VerifiedUser,
            supportedFormats = "Input: Employer & Candidate Details • Output: Offer Letter PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Candidate: Samira Patel\nRole: Staff Designer\nStart Date: December 1, 2026\nCompensation: As agreed in schedule A"
        ),
        ToolDefinition(
            id = "cv_resignation_letter",
            name = "Resignation Letter Maker",
            shortPurpose = "Create professional notice-of-resignation & handover documents",
            category = ToolCategory.CV,
            icon = Icons.AutoMirrored.Filled.NoteAdd,
            supportedFormats = "Input: Notice Period & Last Working Day • Output: PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Employee: Chris Morgan\nManager: Dana White\nLast Working Day: November 30, 2026\nNote: Thankful for the opportunity and committed to a smooth transition."
        ),
        ToolDefinition(
            id = "cv_leave_application",
            name = "Leave Application Maker",
            shortPurpose = "Format medical, annual, or personal leave requests",
            category = ToolCategory.CV,
            icon = Icons.Default.CalendarMonth,
            supportedFormats = "Input: Leave Dates & Reason • Output: Leave Request PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Employee: Priya Nair\nLeave Type: Annual Leave\nDates: Nov 18, 2026 to Nov 22, 2026\nBackup Contact: Dev Team Lead"
        ),
        ToolDefinition(
            id = "cv_experience_certificate",
            name = "Experience Certificate Template Maker",
            shortPurpose = "Generate HR work experience & service verification letters",
            category = ToolCategory.CV,
            icon = Icons.Default.MenuBook,
            supportedFormats = "Input: Organization, Tenure & Role • Output: Certificate PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Organization: NovaTech Solutions\nEmployee: Liam O'Connor\nDesignation: Lead QA Engineer\nTenure: Jan 2022 – Oct 2026"
        ),
        ToolDefinition(
            id = "cv_recommendation_letter",
            name = "Recommendation & Reference Letter",
            shortPurpose = "Draft academic or professional endorsement letters",
            category = ToolCategory.CV,
            icon = Icons.Default.Badge,
            supportedFormats = "Input: Recommender & Candidate Facts • Output: Letter PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Recommender: Dr. Elena Rostova, VP Engineering\nCandidate: Maya Lin\nRelationship: Direct Supervisor (3 Years)\nEndorsement: Exceptional analytical rigor and leadership."
        ),
        ToolDefinition(
            id = "cv_internship_letter",
            name = "Internship Application Letter Maker",
            shortPurpose = "Create student & graduate internship cover letters",
            category = ToolCategory.CV,
            icon = Icons.Default.Description,
            supportedFormats = "Input: University, Major & Skills • Output: PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Student: Noah Kim\nUniversity: State Tech University (B.S. Computer Science)\nTarget Track: Summer Mobile Engineering Intern\nProjects: Built offline campus transit app in Kotlin."
        ),
        ToolDefinition(
            id = "cv_professional_bio",
            name = "Professional Bio Maker",
            shortPurpose = "Craft speaker bios, executive summaries, and portfolio intros",
            category = ToolCategory.CV,
            icon = Icons.Default.AccountBox,
            supportedFormats = "Output: Short, Medium & Executive Bio + PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Name: Evelyn Vance\nTitle: Principal Security Architect\nFocus: Zero-trust mobile systems, applied cryptography\nAchievements: Author of 12 open-source tooling libraries."
        ),
        ToolDefinition(
            id = "cv_fillable_form_builder",
            name = "Fillable Form Builder",
            shortPurpose = "Create custom forms with text, date, checkbox, and dropdown fields",
            category = ToolCategory.CV,
            icon = Icons.AutoMirrored.Filled.Assignment,
            supportedFormats = "Output: Printable Structured Form PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Form Title: Client Onboarding Checklist\n[Text] Full Legal Name\n[Date] Effective Start Date\n[Checkbox] NDA Signed & Verified\n[Dropdown] Tier: Standard | Enterprise"
        ),
        ToolDefinition(
            id = "cv_form_filler_signature",
            name = "Form Filler with Signature Attachment",
            shortPurpose = "Complete form fields and embed digital signature block into PDF",
            category = ToolCategory.CV,
            icon = Icons.Default.Draw,
            supportedFormats = "Output: Completed & Signed Form PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Form: Equipment Handover Receipt\nRecipient: Marcus Vance\nAsset Tag: TV-MBP-4091\nSignature: Confirmed & Received"
        ),

        // ==================== 3. IMAGE & GRAPHICS TOOLS (16 Tools) ====================
        ToolDefinition(
            id = "img_jpg_to_png",
            name = "JPG to PNG",
            shortPurpose = "Convert JPEG photos into lossless 32-bit PNG format",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Image,
            supportedFormats = "Input: JPG/JPEG • Output: Lossless PNG",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "PNG"
        ),
        ToolDefinition(
            id = "img_png_to_jpg",
            name = "PNG to JPG/JPEG",
            shortPurpose = "Convert PNG to JPEG (composite alpha onto clean white background)",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.PhotoLibrary,
            supportedFormats = "Input: PNG • Output: JPEG (Note: Alpha flattened to white)",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "JPG"
        ),
        ToolDefinition(
            id = "img_webp_to_png",
            name = "WEBP to PNG",
            shortPurpose = "Convert WEBP graphics into universal PNG format with alpha",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Transform,
            supportedFormats = "Input: WEBP • Output: Lossless PNG",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "PNG"
        ),
        ToolDefinition(
            id = "img_png_to_webp",
            name = "PNG to WEBP",
            shortPurpose = "Compress PNG images into modern high-efficiency WEBP",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Compress,
            supportedFormats = "Input: PNG/JPG • Output: WEBP",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "WEBP"
        ),
        ToolDefinition(
            id = "img_bmp_to_png",
            name = "BMP to PNG",
            shortPurpose = "Convert uncompressed BMP bitmaps to compact PNG files",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Image,
            supportedFormats = "Input: BMP • Output: Lossless PNG",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "PNG"
        ),
        ToolDefinition(
            id = "img_heic_to_jpg",
            name = "HEIC to JPG",
            shortPurpose = "Convert HEIC/HEIF photos to widely compatible JPEG",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.SwapHoriz,
            supportedFormats = "Input: HEIC/HEIF (Android 9+ hardware decoder) • Output: JPEG",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "JPG"
        ),
        ToolDefinition(
            id = "img_png_to_tiff",
            name = "PNG to TIFF",
            shortPurpose = "Write uncompressed Baseline RGB TIFF 6.0 archival image",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.FilterFrames,
            supportedFormats = "Input: PNG • Output: Single-page Uncompressed Baseline TIFF (.tif)",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "TIFF"
        ),
        ToolDefinition(
            id = "img_jpeg_to_tiff",
            name = "JPEG to TIFF",
            shortPurpose = "Convert JPEG photo into Baseline RGB TIFF 6.0 container",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.FilterFrames,
            supportedFormats = "Input: JPEG • Output: Single-page Uncompressed Baseline TIFF (.tif)",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "TIFF"
        ),
        ToolDefinition(
            id = "img_tiff_to_png",
            name = "TIFF to PNG",
            shortPurpose = "Decode Baseline uncompressed RGB TIFF to lossless PNG",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Image,
            supportedFormats = "Input: Baseline Uncompressed RGB TIFF • Output: PNG",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "PNG"
        ),
        ToolDefinition(
            id = "img_tiff_to_jpeg",
            name = "TIFF to JPEG",
            shortPurpose = "Decode Baseline uncompressed RGB TIFF to compact JPEG",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.PhotoLibrary,
            supportedFormats = "Input: Baseline Uncompressed RGB TIFF • Output: JPEG",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "JPG"
        ),
        ToolDefinition(
            id = "img_gif_frame_extract",
            name = "GIF Frame Extraction",
            shortPurpose = "Extract primary/key frames from animated GIF into PNGs",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.PhotoLibrary,
            supportedFormats = "Input: Animated/Static GIF • Output: Extracted PNG frame(s)",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "PNG"
        ),
        ToolDefinition(
            id = "img_resize_pixels",
            name = "Resize Image by Pixels",
            shortPurpose = "Scale image to exact width × height pixel dimensions",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.PhotoSizeSelectLarge,
            supportedFormats = "Input: PNG/JPG/WEBP + WxH • Output: Resized Image",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultInputSample = "1080x1080"
        ),
        ToolDefinition(
            id = "img_resize_percentage",
            name = "Resize Image by Percentage",
            shortPurpose = "Scale image dimensions by 25%, 50%, 75%, or custom %",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Percent,
            supportedFormats = "Input: Image + Scale % • Output: Scaled Image",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultInputSample = "50"
        ),
        ToolDefinition(
            id = "img_crop_and_rotate",
            name = "Crop and Rotate Image",
            shortPurpose = "Center-crop aspect ratio (1:1, 4:3, 16:9) and rotate",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Crop,
            supportedFormats = "Input: Image • Output: Cropped & Rotated PNG/JPEG",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultInputSample = "90"
        ),
        ToolDefinition(
            id = "img_quality_inspector",
            name = "Image Quality & Format Inspector",
            shortPurpose = "Analyze resolution, aspect ratio, color depth, alpha, and byte size",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Info,
            supportedFormats = "Input: PNG, JPEG, WEBP, GIF, BMP, TIFF",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "INSPECT"
        ),
        ToolDefinition(
            id = "img_metadata_scrubber",
            name = "Image Metadata Viewer & Remover",
            shortPurpose = "Inspect EXIF tags and scrub GPS/camera privacy metadata",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.PrivacyTip,
            supportedFormats = "Input: JPEG/PNG • Output: Clean Privacy-Scrubbed Image",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultParamOption = "SCRUB_EXIF"
        ),

        // ==================== 4. DATA & SPREADSHEET TOOLS (12 Tools) ====================
        ToolDefinition(
            id = "data_csv_editor",
            name = "CSV Maker & Table Editor",
            shortPurpose = "Create, inspect, and clean comma-separated datasets",
            category = ToolCategory.DATA,
            icon = Icons.Default.TableChart,
            supportedFormats = "Input/Output: RFC 4180 .csv",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "id,name,department,score\n101,Alice Chen,Engineering,98\n102,Bob Smith,Design,94\n103,Carlos Diaz,Product,91"
        ),
        ToolDefinition(
            id = "data_csv_to_json",
            name = "CSV to JSON Converter",
            shortPurpose = "Transform CSV rows with headers into a structured JSON array",
            category = ToolCategory.DATA,
            icon = Icons.Default.DataObject,
            supportedFormats = "Input: CSV • Output: Pretty-printed JSON Array",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "id,name,role,active\n1,Aria,Architect,true\n2,Devon,Security,true"
        ),
        ToolDefinition(
            id = "data_json_to_csv",
            name = "JSON to CSV Converter",
            shortPurpose = "Flatten a JSON array of objects into a spreadsheet CSV file",
            category = ToolCategory.DATA,
            icon = Icons.Default.TableRows,
            supportedFormats = "Input: JSON Array • Output: RFC 4180 CSV",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "[{\"id\":1,\"tool\":\"Image to PDF\",\"category\":\"PDF\"},{\"id\":2,\"tool\":\"SHA-256\",\"category\":\"Security\"}]"
        ),
        ToolDefinition(
            id = "data_xlsx_to_csv",
            name = "XLSX to CSV",
            shortPurpose = "Extract worksheet rows from OpenXML .xlsx spreadsheet to CSV",
            category = ToolCategory.DATA,
            icon = Icons.Default.GridOn,
            supportedFormats = "Input: OOXML .xlsx / Tabular • Output: CSV",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "Quarter\tRevenue\tGrowth\nQ1 2026\t145000\t12%\nQ2 2026\t168000\t16%\nQ3 2026\t192000\t14%"
        ),
        ToolDefinition(
            id = "data_csv_to_xlsx",
            name = "CSV to XLSX Package",
            shortPurpose = "Package CSV data into an Excel-compatible OpenXML workbook",
            category = ToolCategory.DATA,
            icon = Icons.Default.TableChart,
            supportedFormats = "Input: CSV • Output: OpenXML .xlsx / Spreadsheet XML",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "Item,Quantity,UnitCost\nWorkstation,12,1850\nMonitor 4K,24,420"
        ),
        ToolDefinition(
            id = "data_json_formatter",
            name = "JSON Formatter & Validator",
            shortPurpose = "Pretty-print, minify, and validate strict JSON syntax",
            category = ToolCategory.DATA,
            icon = Icons.Default.DataObject,
            supportedFormats = "Input/Output: RFC 8259 JSON",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "{\"app\":\"Toolverse\",\"toolsCount\":125,\"freeForever\":true,\"categories\":[\"PDF\",\"CV\",\"Image\",\"Security\"]}"
        ),
        ToolDefinition(
            id = "data_xml_formatter",
            name = "XML Formatter & Validator",
            shortPurpose = "Validate well-formed XML tags and indent hierarchy",
            category = ToolCategory.DATA,
            icon = Icons.Default.Code,
            supportedFormats = "Input/Output: XML 1.0",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "<catalog><tool id=\"pdf_1\"><name>Image to PDF</name><offline>true</offline></tool></catalog>"
        ),
        ToolDefinition(
            id = "data_yaml_formatter",
            name = "YAML Formatter & Validator",
            shortPurpose = "Validate YAML key-value structure and convert to/from JSON",
            category = ToolCategory.DATA,
            icon = Icons.Default.Schema,
            supportedFormats = "Input: YAML • Output: Normalized YAML & JSON",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "service: toolverse-android\nversion: 1.0.0\nruntime: kotlin-compose\nfeatures:\n  - pdf_engine\n  - cv_builder\n  - zero_trust_firestore"
        ),
        ToolDefinition(
            id = "data_duplicate_row_remover",
            name = "Duplicate-Row Remover",
            shortPurpose = "Deduplicate identical rows in CSV or tabular datasets",
            category = ToolCategory.DATA,
            icon = Icons.Default.CleaningServices,
            supportedFormats = "Input: CSV/TSV • Output: Deduplicated CSV + Stats",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "email,plan\nalice@acme.io,free\nbob@acme.io,free\nalice@acme.io,free\nclaire@acme.io,free"
        ),
        ToolDefinition(
            id = "data_column_cleaner",
            name = "Column & Field Cleaner",
            shortPurpose = "Trim whitespace, strip empty columns, and normalize headers",
            category = ToolCategory.DATA,
            icon = Icons.Default.ViewColumn,
            supportedFormats = "Input: CSV • Output: Normalized CSV",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = " First Name , Last Name , Department \n  Maya ,  Lin ,  R&D  \n  Liam ,  Vance ,  Operations "
        ),
        ToolDefinition(
            id = "data_delimiter_converter",
            name = "Delimited-Text Converter",
            shortPurpose = "Convert between comma (CSV), tab (TSV), pipe (|), and semicolon (;)",
            category = ToolCategory.DATA,
            icon = Icons.Default.SwapHoriz,
            supportedFormats = "Input: CSV/TSV/PSV • Output: Custom Delimiter",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "sku,title,stock\nTV-01,Adapter,42\nTV-02,Dock,19",
            defaultParamOption = "|"
        ),
        ToolDefinition(
            id = "data_table_to_pdf",
            name = "Table Data to PDF",
            shortPurpose = "Render CSV or spreadsheet rows into a bordered A4 PDF table",
            category = ToolCategory.DATA,
            icon = Icons.Default.PictureAsPdf,
            supportedFormats = "Input: CSV/TSV • Output: Paginated Table PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "Metric,Target,Actual,Status\nCold Start,< 800ms,490ms,PASS\nBatch 50 Images,No OOM,Bounded,PASS\nFirestore Rules,100% Tested,100% Tested,PASS"
        ),

        // ==================== 5. TEXT TOOLS (12 Tools) ====================
        ToolDefinition(
            id = "text_editor_maker",
            name = "TXT Maker & Clean Editor",
            shortPurpose = "Draft, edit, and export UTF-8 plain text files",
            category = ToolCategory.TEXT,
            icon = Icons.AutoMirrored.Filled.TextSnippet,
            supportedFormats = "Output: UTF-8 .txt",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "Toolverse Quick Scratchpad\nWrite notes, snippets, or documentation and export directly as UTF-8 .txt."
        ),
        ToolDefinition(
            id = "text_to_pdf_direct",
            name = "Text to PDF Publisher",
            shortPurpose = "Typeset articles or notes into clean A4 PDF documents",
            category = ToolCategory.TEXT,
            icon = Icons.Default.PictureAsPdf,
            supportedFormats = "Input: Text • Output: A4 PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "Technical Specification Summary\n\nAll tools run locally on-device by default for maximum speed and privacy."
        ),
        ToolDefinition(
            id = "text_word_counter",
            name = "Word & Reading Time Counter",
            shortPurpose = "Count words, sentences, paragraphs, and speaking/reading time",
            category = ToolCategory.TEXT,
            icon = Icons.Default.Numbers,
            supportedFormats = "Real-time lexical & readability analysis",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "Toolverse combines over one hundred focused productivity utilities behind one calm, responsive Android interface."
        ),
        ToolDefinition(
            id = "text_char_line_counter",
            name = "Character, Byte & Line Counter",
            shortPurpose = "Count total characters, non-space chars, UTF-8 bytes, and lines",
            category = ToolCategory.TEXT,
            icon = Icons.AutoMirrored.Filled.FormatAlignLeft,
            supportedFormats = "Unicode graphemes & UTF-8 byte length",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "First line of sample text.\nSecond line with UTF-8 symbols: ✓ ★ →\nThird line."
        ),
        ToolDefinition(
            id = "text_case_converter",
            name = "Case Converter",
            shortPurpose = "Convert to UPPERCASE, lowercase, Title Case, camelCase, snake_case",
            category = ToolCategory.TEXT,
            icon = Icons.Default.TextFields,
            supportedFormats = "8 naming and prose case styles",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "toolverse full stack android productivity toolkit"
        ),
        ToolDefinition(
            id = "text_whitespace_cleaner",
            name = "Whitespace & Linebreak Cleaner",
            shortPurpose = "Strip extra spaces, tabs, trailing whitespace, and blank lines",
            category = ToolCategory.TEXT,
            icon = Icons.Default.CleaningServices,
            supportedFormats = "Input/Output: Normalized Text",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "Too    many     spaces   here.\n\n\n   Leading and trailing spaces   \n\nClean me up!"
        ),
        ToolDefinition(
            id = "text_find_replace",
            name = "Find and Replace",
            shortPurpose = "Batch search and replace substrings or patterns across text",
            category = ToolCategory.TEXT,
            icon = Icons.Default.FindReplace,
            supportedFormats = "Case-sensitive & case-insensitive replacement",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "legacy_api_v1.connect(legacy_api_v1.host)",
            defaultParamOption = "legacy_api_v1->toolverse_v2"
        ),
        ToolDefinition(
            id = "text_diff_checker",
            name = "Text Comparison & Diff",
            shortPurpose = "Compare two text blocks line-by-line (added, removed, unchanged)",
            category = ToolCategory.TEXT,
            icon = Icons.AutoMirrored.Filled.CompareArrows,
            supportedFormats = "Input: Block A & Block B separated by ---",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "version = 1.0\nstatus = draft\nowner = alice\n---\nversion = 2.0\nstatus = released\nowner = alice"
        ),
        ToolDefinition(
            id = "text_sort_lines",
            name = "Sort Lines (A–Z, Z–A, Length, Numeric)",
            shortPurpose = "Sort multi-line lists alphabetically, numerically, or by length",
            category = ToolCategory.TEXT,
            icon = Icons.AutoMirrored.Filled.Sort,
            supportedFormats = "Ascending, Descending & Natural Sort",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "Zeta\nAlpha\nOmega\nBeta\nDelta"
        ),
        ToolDefinition(
            id = "text_remove_duplicate_lines",
            name = "Remove Duplicate Lines",
            shortPurpose = "Keep only unique lines while preserving original order",
            category = ToolCategory.TEXT,
            icon = Icons.Default.DeleteSweep,
            supportedFormats = "Order-preserving line deduplication",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "kotlin-stdlib\ncompose-material3\nkotlin-stdlib\nfirebase-firestore\ncompose-material3"
        ),
        ToolDefinition(
            id = "text_markdown_preview",
            name = "Markdown Editor & HTML Preview",
            shortPurpose = "Write Markdown and convert to structured HTML & printable preview",
            category = ToolCategory.TEXT,
            icon = Icons.Default.Code,
            supportedFormats = "CommonMark Markdown -> HTML5",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "# Toolverse Release 1.0\n\n## Core Highlights\n- **120+ Utilities** in one coherent app\n- **On-device privacy** by default\n- `AES-256-GCM` authenticated encryption"
        ),
        ToolDefinition(
            id = "text_encoding_converter",
            name = "Text Encoding & Hex/Binary Inspector",
            shortPurpose = "Convert text between UTF-8, ASCII, ISO-8859-1, Hex, and Binary",
            category = ToolCategory.TEXT,
            icon = Icons.Default.Translate,
            supportedFormats = "UTF-8 • US-ASCII • Hexadecimal • 8-bit Binary",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "Toolverse 2026"
        ),

        // ==================== 6. FILE & ARCHIVE TOOLS (10 Tools) ====================
        ToolDefinition(
            id = "file_zip_creator",
            name = "ZIP Archive Creator",
            shortPurpose = "Compress selected files or text entries into a standard .zip archive",
            category = ToolCategory.FILE,
            icon = Icons.Default.FolderZip,
            supportedFormats = "Input: Multiple Files • Output: Deflate .zip Archive",
            engineType = EngineType.FILE_ARCHIVE_ENGINE,
            defaultInputSample = "README.txt:Generated by Toolverse ZIP Creator\nmanifest.json:{\"version\":1,\"status\":\"ready\"}"
        ),
        ToolDefinition(
            id = "file_zip_extractor",
            name = "ZIP Extractor & Inspector",
            shortPurpose = "Inspect and extract entries from .zip archives with zip-slip protection",
            category = ToolCategory.FILE,
            icon = Icons.Default.Unarchive,
            supportedFormats = "Input: Standard Deflate .zip Archive",
            engineType = EngineType.FILE_ARCHIVE_ENGINE
        ),
        ToolDefinition(
            id = "file_7z_inspector",
            name = "7Z Archive Header Inspector",
            shortPurpose = "Inspect 7-Zip (.7z) signature headers, version, and archive metadata",
            category = ToolCategory.FILE,
            icon = Icons.Default.Archive,
            supportedFormats = "Input: .7z / .zip / .tar (Magic header verification)",
            engineType = EngineType.FILE_ARCHIVE_ENGINE
        ),
        ToolDefinition(
            id = "file_batch_renamer",
            name = "Single & Batch File Renamer",
            shortPurpose = "Preview and apply prefix, suffix, sequential numbering, and slug rules",
            category = ToolCategory.FILE,
            icon = Icons.Default.DriveFileRenameOutline,
            supportedFormats = "Pattern rules: {index}, {date}, prefix, replace",
            engineType = EngineType.FILE_ARCHIVE_ENGINE,
            defaultInputSample = "IMG_0041.JPG\nIMG_0042.JPG\nFinal Scan (1).pdf",
            defaultParamOption = "toolverse_{index}"
        ),
        ToolDefinition(
            id = "file_mime_inspector",
            name = "File Type & Magic-Byte Inspector",
            shortPurpose = "Detect real file format via magic header bytes vs. extension",
            category = ToolCategory.FILE,
            icon = Icons.Default.Info,
            supportedFormats = "Detects PDF, PNG, JPEG, GIF, WEBP, ZIP, DOCX, ELF, MP3, WAV",
            engineType = EngineType.FILE_ARCHIVE_ENGINE
        ),
        ToolDefinition(
            id = "file_size_analyzer",
            name = "File Size & Storage Analyzer",
            shortPurpose = "Analyze file sizes, compression ratios, and workspace cache breakdown",
            category = ToolCategory.FILE,
            icon = Icons.Default.Storage,
            supportedFormats = "Bytes, KiB, MiB, Decimal KB/MB breakdown",
            engineType = EngineType.FILE_ARCHIVE_ENGINE
        ),
        ToolDefinition(
            id = "file_organizer_planner",
            name = "File Organizer by Type & Date",
            shortPurpose = "Group selected files into structured category & YYYY-MM folders",
            category = ToolCategory.FILE,
            icon = Icons.Default.Reorder,
            supportedFormats = "Outputs organized ZIP bundle or manifest",
            engineType = EngineType.FILE_ARCHIVE_ENGINE,
            defaultInputSample = "invoice_nov.pdf\navatar_2026.png\nbackup.zip\nnotes.txt\ncontract.pdf"
        ),
        ToolDefinition(
            id = "file_duplicate_finder",
            name = "Duplicate Finder Using SHA-256 Hashes",
            shortPurpose = "Compare multiple files byte-for-byte via SHA-256 to spot exact duplicates",
            category = ToolCategory.FILE,
            icon = Icons.Default.ContentCopy,
            supportedFormats = "Input: 2+ Picked Files • Content-addressed SHA-256 comparison",
            engineType = EngineType.FILE_ARCHIVE_ENGINE
        ),
        ToolDefinition(
            id = "file_checksum_verifier",
            name = "File Checksum Verifier",
            shortPurpose = "Compute file SHA-256/SHA-1/MD5 and compare against expected hash",
            category = ToolCategory.FILE,
            icon = Icons.Default.VerifiedUser,
            supportedFormats = "SHA-256, SHA-1, MD5, CRC32 verification",
            engineType = EngineType.FILE_ARCHIVE_ENGINE
        ),
        ToolDefinition(
            id = "file_share_package",
            name = "Share & Export Package Creator",
            shortPurpose = "Bundle multiple files with a SHA-256 integrity manifest into a shareable ZIP",
            category = ToolCategory.FILE,
            icon = Icons.Default.IosShare,
            supportedFormats = "Output: .zip package with MANIFEST.sha256",
            engineType = EngineType.FILE_ARCHIVE_ENGINE
        ),

        // ==================== 7. SECURITY TOOLS (8 Tools) ====================
        ToolDefinition(
            id = "sec_password_generator",
            name = "Secure Password Generator",
            shortPurpose = "Generate cryptographic passwords & passphrases with entropy bits",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Password,
            supportedFormats = "CSPRNG SecureRandom • Configurable length & character sets",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "20"
        ),
        ToolDefinition(
            id = "sec_sha256",
            name = "SHA-256 Calculator",
            shortPurpose = "Compute FIPS 180-4 SHA-256 cryptographic digest for text or files",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Fingerprint,
            supportedFormats = "256-bit (64 hex chars) cryptographic hash",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "Toolverse Zero-Trust Security Verification"
        ),
        ToolDefinition(
            id = "sec_sha1_legacy",
            name = "SHA-1 Calculator (Legacy Verification)",
            shortPurpose = "Compute 160-bit SHA-1 digest with collision security advisory",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Key,
            supportedFormats = "160-bit SHA-1 (Warning: Use SHA-256 for collision resistance)",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "Legacy git commit or artifact verification"
        ),
        ToolDefinition(
            id = "sec_md5_checksum",
            name = "MD5 Checksum (Integrity-Only)",
            shortPurpose = "Compute 128-bit MD5 checksum for non-cryptographic file integrity",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Tag,
            supportedFormats = "128-bit MD5 (Advisory: Integrity check only, not collision-safe)",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "Verify download integrity checksum"
        ),
        ToolDefinition(
            id = "sec_file_aes_gcm",
            name = "File Encryption & Decryption (AES-256-GCM)",
            shortPurpose = "Encrypt or decrypt files using PBKDF2-HMAC-SHA256 + AES-256-GCM",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.EnhancedEncryption,
            supportedFormats = "Authenticated Encryption with 128-bit GCM Auth Tag",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultParamOption = "CorrectHorseBatteryStaple!99"
        ),
        ToolDefinition(
            id = "sec_text_aes_gcm",
            name = "Text Encryption & Decryption (AES-256-GCM)",
            shortPurpose = "Encrypt sensitive text to Base64 ciphertext or decrypt with passphrase",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.VpnKey,
            supportedFormats = "PBKDF2 (65,536 iterations) + AES-256-GCM + 96-bit IV",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "Confidential API Recovery Token: tv_live_98410294810",
            defaultParamOption = "VaultPass#2026"
        ),
        ToolDefinition(
            id = "sec_pdf_password",
            name = "PDF Password Protection",
            shortPurpose = "Wrap PDF document in an AES-256-GCM password-protected envelope",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Lock,
            supportedFormats = "AES-256-GCM Authenticated PDF Envelope",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "DocumentVault#2026"
        ),
        ToolDefinition(
            id = "sec_cache_cleaner",
            name = "Local Cache Cleaner & Secure Deletion",
            shortPurpose = "Wipe temporary exported files and inspect app storage footprint",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Security,
            supportedFormats = "Clears app cacheDir & temporary processing outputs",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE
        ),

        // ==================== 8. CALCULATORS & CONVERTERS (10 Tools) ====================
        ToolDefinition(
            id = "calc_scientific",
            name = "Scientific Calculator",
            shortPurpose = "Evaluate trig, log, ln, sqrt, powers, factorials, and constants",
            category = ToolCategory.CALC,
            icon = Icons.Default.Science,
            supportedFormats = "Supports sin, cos, tan, sqrt, log, ln, ^, pi, e",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "sqrt(144) + 2^5 - sin(0)"
        ),
        ToolDefinition(
            id = "calc_percentage",
            name = "Percentage Calculator",
            shortPurpose = "Calculate X% of Y, percentage change, and margin ratios",
            category = ToolCategory.CALC,
            icon = Icons.Default.Percent,
            supportedFormats = "Value & Percentage breakdown",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "18.5, 2400"
        ),
        ToolDefinition(
            id = "calc_discount",
            name = "Discount & Sale Price Calculator",
            shortPurpose = "Compute final price, total savings, and stacked discount %",
            category = ToolCategory.CALC,
            icon = Icons.Default.MonetizationOn,
            supportedFormats = "Input: Original Price, Discount %, Optional Extra %",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "249.99, 25, 10"
        ),
        ToolDefinition(
            id = "calc_tax_gst",
            name = "Tax / GST / VAT Calculator",
            shortPurpose = "Compute net-to-gross (add tax) and gross-to-net (extract tax)",
            category = ToolCategory.CALC,
            icon = Icons.Default.ReceiptLong,
            supportedFormats = "Configurable Tax/GST Rate % (Inclusive & Exclusive)",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "1500, 18"
        ),
        ToolDefinition(
            id = "calc_date_diff",
            name = "Date Difference Calculator",
            shortPurpose = "Calculate exact years, months, weeks, days, and workdays between dates",
            category = ToolCategory.CALC,
            icon = Icons.Default.DateRange,
            supportedFormats = "Input: YYYY-MM-DD to YYYY-MM-DD",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "2024-01-15 to 2026-11-05"
        ),
        ToolDefinition(
            id = "calc_age",
            name = "Exact Age Calculator",
            shortPurpose = "Calculate age in years, months, days, total hours, and next birthday",
            category = ToolCategory.CALC,
            icon = Icons.Default.CalendarMonth,
            supportedFormats = "Input: Birth Date (YYYY-MM-DD)",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "1996-04-21"
        ),
        ToolDefinition(
            id = "calc_time_duration",
            name = "Time Duration Calculator",
            shortPurpose = "Add, subtract, and convert HH:MM:SS durations and timecode",
            category = ToolCategory.CALC,
            icon = Icons.Default.Timer,
            supportedFormats = "Input: HH:MM:SS + HH:MM:SS",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "02:45:30 + 01:35:45"
        ),
        ToolDefinition(
            id = "calc_length_area_volume",
            name = "Length, Area & Volume Converter",
            shortPurpose = "Convert meters, km, miles, feet, inches, sq.m, acres, liters, gallons",
            category = ToolCategory.CALC,
            icon = Icons.Default.Straighten,
            supportedFormats = "SI Metric & Imperial Spatial Units",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "25.4"
        ),
        ToolDefinition(
            id = "calc_weight_temp",
            name = "Weight & Temperature Converter",
            shortPurpose = "Convert kg, lbs, oz, grams and Celsius, Fahrenheit, Kelvin",
            category = ToolCategory.CALC,
            icon = Icons.Default.Thermostat,
            supportedFormats = "Mass (kg/lb/oz/g) & Temperature (°C/°F/K)",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "37"
        ),
        ToolDefinition(
            id = "calc_digital_storage",
            name = "Digital Storage & Bandwidth Converter",
            shortPurpose = "Convert Bytes, KB/KiB, MB/MiB, GB/GiB, TB/TiB, and Mbps",
            category = ToolCategory.CALC,
            icon = Icons.Default.Storage,
            supportedFormats = "Decimal (SI 1000) & Binary (IEC 1024) Units",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "512"
        ),

        // ==================== 9. QR & BARCODE TOOLS (6 Tools) ====================
        ToolDefinition(
            id = "qr_generator",
            name = "QR Code Generator",
            shortPurpose = "Create high-contrast QR codes for URLs, text, or deep links",
            category = ToolCategory.QR,
            icon = Icons.Default.QrCode2,
            supportedFormats = "Output: High-DPI PNG QR Code",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "https://toolverse.app/toolkit"
        ),
        ToolDefinition(
            id = "qr_scanner",
            name = "QR Code Scanner & Image Decoder",
            shortPurpose = "Scan QR payload from image or inspect structured QR URI schemes",
            category = ToolCategory.QR,
            icon = Icons.Default.QrCodeScanner,
            supportedFormats = "Input: QR Image or Raw QR Payload",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "WIFI:S:Toolverse_5G;T:WPA;P:SuperSecret2026;;"
        ),
        ToolDefinition(
            id = "qr_barcode_generator",
            name = "Barcode Generator (Code-128)",
            shortPurpose = "Generate scannable 1D Code-128 retail & inventory barcodes",
            category = ToolCategory.QR,
            icon = Icons.Default.QrCode,
            supportedFormats = "Output: 1D Code-128 Barcode PNG",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "TV-2026-884920"
        ),
        ToolDefinition(
            id = "qr_barcode_reader",
            name = "Barcode Reader & GS1/EAN Inspector",
            shortPurpose = "Validate EAN-13, UPC-A, and Code-128 check digits and country prefixes",
            category = ToolCategory.QR,
            icon = Icons.Default.Rule,
            supportedFormats = "EAN-13, UPC-A, ISBN-13, Code-128 Checksum Validation",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "4006381333931"
        ),
        ToolDefinition(
            id = "qr_wifi_generator",
            name = "Wi-Fi QR Generator",
            shortPurpose = "Generate instant-connect Wi-Fi QR codes (WPA/WPA2/WPA3/WEP)",
            category = ToolCategory.QR,
            icon = Icons.Default.Wifi,
            supportedFormats = "Standard WIFI:S:<SSID>;T:<WPA>;P:<PASS>;; QR PNG",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "SSID:Toolverse_Studio|SECURITY:WPA|PASS:FastFiber2026"
        ),
        ToolDefinition(
            id = "qr_vcard_generator",
            name = "Contact / vCard 3.0 QR Generator",
            shortPurpose = "Create digital business card QR codes for instant contact import",
            category = ToolCategory.QR,
            icon = Icons.Default.AccountBox,
            supportedFormats = "vCard 3.0 Standard QR Code PNG",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "NAME:Alex Rivera|ORG:Toolverse Labs|TEL:+1555019283|EMAIL:alex@toolverse.app"
        ),

        // ==================== 10. AUDIO TOOLS (5 Tools) ====================
        ToolDefinition(
            id = "audio_wav_converter",
            name = "Audio Format & PCM WAV Generator",
            shortPurpose = "Convert or synthesize 16-bit PCM WAV audio files with selectable sample rate",
            category = ToolCategory.AUDIO,
            icon = Icons.Default.AudioFile,
            supportedFormats = "RIFF 16-bit Little-Endian PCM .wav (44.1 kHz / 48 kHz)",
            engineType = EngineType.AUDIO_MEDIA_ENGINE,
            defaultInputSample = "440, 2.5, 44100"
        ),
        ToolDefinition(
            id = "audio_trimmer",
            name = "Audio Trimmer (Lossless WAV Cutter)",
            shortPurpose = "Trim start and end timestamps of WAV audio with sample accuracy",
            category = ToolCategory.AUDIO,
            icon = Icons.Default.ContentCut,
            supportedFormats = "Input: PCM .wav + Start/End sec • Output: Trimmed .wav",
            engineType = EngineType.AUDIO_MEDIA_ENGINE,
            defaultInputSample = "0.2 to 1.8"
        ),
        ToolDefinition(
            id = "audio_merger",
            name = "Audio Merger (Concatenate WAV)",
            shortPurpose = "Join multiple PCM WAV audio clips into a single continuous track",
            category = ToolCategory.AUDIO,
            icon = Icons.AutoMirrored.Filled.MergeType,
            supportedFormats = "Input: 2+ PCM .wav files • Output: Merged .wav",
            engineType = EngineType.AUDIO_MEDIA_ENGINE
        ),
        ToolDefinition(
            id = "audio_metadata_inspector",
            name = "Audio Metadata Viewer & RIFF/ID3 Tag Editor",
            shortPurpose = "Inspect duration, sample rate, channels, and write RIFF INFO metadata",
            category = ToolCategory.AUDIO,
            icon = Icons.Default.MusicNote,
            supportedFormats = "MP3, WAV, M4A, OGG, FLAC (MediaMetadataRetriever + RIFF)",
            engineType = EngineType.AUDIO_MEDIA_ENGINE,
            defaultInputSample = "Title:Toolverse Intro|Artist:Studio Ensemble|Year:2026"
        ),
        ToolDefinition(
            id = "audio_bitrate_inspector",
            name = "Audio Bitrate & File-Size Calculator",
            shortPurpose = "Inspect real file bitrate or calculate uncompressed/compressed audio size",
            category = ToolCategory.AUDIO,
            icon = Icons.Default.GraphicEq,
            supportedFormats = "PCM, FLAC, MP3 (128–320 kbps), AAC size & bitrate analyzer",
            engineType = EngineType.AUDIO_MEDIA_ENGINE,
            defaultInputSample = "180, 44100, 16, 2"
        ),

        // ==================== 11. DEVELOPER TOOLS (8 Tools) ====================
        ToolDefinition(
            id = "dev_base64",
            name = "Base64 Encoder & Decoder",
            shortPurpose = "Encode and decode standard RFC 4648 & URL-safe Base64 payloads",
            category = ToolCategory.DEV,
            icon = Icons.Default.Code,
            supportedFormats = "UTF-8 <-> RFC 4648 Base64 & URL-Safe Base64",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "Toolverse::NativeAndroid::2026"
        ),
        ToolDefinition(
            id = "dev_url_encoder",
            name = "URL Encoder & Query String Parser",
            shortPurpose = "Percent-encode/decode URI components and inspect query parameters",
            category = ToolCategory.DEV,
            icon = Icons.Default.Link,
            supportedFormats = "RFC 3986 Percent-Encoding + Query Key/Value Map",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "https://api.toolverse.app/v1/search?q=pdf+tools&category=PDF%20%26%20Docs&limit=25"
        ),
        ToolDefinition(
            id = "dev_uuid_generator",
            name = "UUID v4 & ULID Generator",
            shortPurpose = "Generate bulk RFC 4122 v4 UUIDs (hyphenated, compact, uppercase)",
            category = ToolCategory.DEV,
            icon = Icons.Default.Key,
            supportedFormats = "Cryptographic UUID v4 (1–50 per batch)",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "10"
        ),
        ToolDefinition(
            id = "dev_unix_timestamp",
            name = "Unix Timestamp & ISO-8601 Converter",
            shortPurpose = "Convert epoch seconds/milliseconds to UTC, Local, and relative time",
            category = ToolCategory.DEV,
            icon = Icons.Default.Schedule,
            supportedFormats = "Epoch Seconds <-> Milliseconds <-> ISO-8601 UTC",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "1793889880"
        ),
        ToolDefinition(
            id = "dev_regex_tester",
            name = "Regex Tester & Group Extractor",
            shortPurpose = "Test regular expressions with live match offsets and capture groups",
            category = ToolCategory.DEV,
            icon = Icons.Default.FindReplace,
            supportedFormats = "JVM Pattern Engine • Capture Group Breakdown",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "Contact support@toolverse.app or security@toolverse.io for inquiries.",
            defaultParamOption = "([a-zA-Z0-9._-]+)@([a-zA-Z0-9._-]+\\.[a-zA-Z]{2,})"
        ),
        ToolDefinition(
            id = "dev_html_entities",
            name = "HTML Entity Encoder & Decoder",
            shortPurpose = "Escape and unescape HTML5 named and numeric character entities",
            category = ToolCategory.DEV,
            icon = Icons.Default.Html,
            supportedFormats = "&amp; &lt; &gt; &quot; &#39; &copy; numeric hex entities",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "<button class=\"primary\">Save & Export \"PDF\"</button>"
        ),
        ToolDefinition(
            id = "dev_jwt_viewer",
            name = "JWT Viewer & Claims Inspector (Decode-Only)",
            shortPurpose = "Decode JWT header & payload claims and check exp/iat timestamps",
            category = ToolCategory.DEV,
            icon = Icons.Default.VpnKey,
            supportedFormats = "RFC 7519 JWT Header & Payload Decoder (Note: Decode-only; does not verify secret signature)",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyXzEyMyIsIm5hbWUiOiJBbGljZSBFbmdpbmVlciIsInJvbGUiOiJtZW1iZXIiLCJpYXQiOjE3OTM4ODAwMDAsImV4cCI6MTg5Mzg4MDAwMH0.signature_placeholder"
        ),
        ToolDefinition(
            id = "dev_json_path",
            name = "JSON Path Explorer",
            shortPurpose = "Query nested JSON objects and arrays using dot and bracket selectors",
            category = ToolCategory.DEV,
            icon = Icons.Default.DataArray,
            supportedFormats = "JSON + Path selector (e.g. store.tools[0].name)",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "{\"workspace\":{\"name\":\"Toolverse\",\"tools\":[{\"id\":\"pdf_1\",\"name\":\"Image to PDF\"},{\"id\":\"sec_1\",\"name\":\"SHA-256\"}]}}",
            defaultParamOption = "workspace.tools[0].name"
        ),

        // ==================== +52 NEW UNIQUE USEFUL TOOLS (TOTAL 177 TOOLS) ====================
        // --- PDF & Document Tools (+5) ---
        ToolDefinition(
            id = "pdf_grayscale",
            name = "PDF to Grayscale (B&W Print)",
            shortPurpose = "Convert color PDF pages to high-contrast grayscale for ink-saving printing",
            category = ToolCategory.PDF,
            icon = Icons.Default.Tune,
            supportedFormats = "Input: Color PDF • Output: Luminance Grayscale PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            searchAliases = listOf("black and white pdf", "grayscale pdf", "monochrome pdf")
        ),
        ToolDefinition(
            id = "pdf_page_size_normalizer",
            name = "PDF Page Size Normalizer",
            shortPurpose = "Standardize mixed-dimension PDF pages into uniform A4 or Letter sheets",
            category = ToolCategory.PDF,
            icon = Icons.Default.FilterFrames,
            supportedFormats = "Input: Mixed PDF • Output: Uniform A4 / Letter PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "A4"
        ),
        ToolDefinition(
            id = "pdf_margin_cropper",
            name = "PDF Margin & Whitespace Trimmer",
            shortPurpose = "Crop outer page margins and scale content for easier mobile reading",
            category = ToolCategory.PDF,
            icon = Icons.Default.Crop,
            supportedFormats = "Input: PDF + Margin Trim % • Output: Cropped PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "8"
        ),
        ToolDefinition(
            id = "pdf_header_footer",
            name = "PDF Header & Bates Number Stamper",
            shortPurpose = "Stamp legal Bates numbers and custom running headers onto PDF pages",
            category = ToolCategory.PDF,
            icon = Icons.Default.FormatListNumbered,
            supportedFormats = "Input: PDF + Prefix • Output: Bates-Indexed PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE,
            defaultInputSample = "EXHIBIT-A-0001"
        ),
        ToolDefinition(
            id = "pdf_poster_grid",
            name = "PDF 2-Up Handout Composer",
            shortPurpose = "Arrange sequential PDF pages into compact 2-up presentation sheets",
            category = ToolCategory.PDF,
            icon = Icons.Default.GridOn,
            supportedFormats = "Input: Multi-page PDF • Output: 2-Up Handout PDF",
            engineType = EngineType.PDF_DOCUMENT_ENGINE
        ),

        // --- CV, Forms & Career Studio (+4) ---
        ToolDefinition(
            id = "cv_ats_keyword_matcher",
            name = "ATS Resume vs. Job Description Matcher",
            shortPurpose = "Compute real keyword match %, missing skills, and ATS compatibility score",
            category = ToolCategory.CV,
            icon = Icons.Default.Analytics,
            supportedFormats = "Input: Resume Text | Job Description • Output: Match Report + PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Resume: Senior Android Engineer skilled in Kotlin, Jetpack Compose, Coroutines, Room, Firebase, CI/CD, JUnit.\n---\nJob Description: Looking for an Android Developer experienced in Kotlin, Jetpack Compose, Coroutines, GraphQL, Hilt, Firebase, and Agile."
        ),
        ToolDefinition(
            id = "cv_salary_tax_estimator",
            name = "Salary, Hourly & Take-Home Pay Breakdown",
            shortPurpose = "Convert annual salary to monthly, biweekly, daily, hourly, and net after tax",
            category = ToolCategory.CV,
            icon = Icons.Default.MonetizationOn,
            supportedFormats = "Input: Gross Annual Salary, Tax %, Hours/Week • Output: Compensation Table + PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "135000, 24, 40"
        ),
        ToolDefinition(
            id = "cv_interview_star_builder",
            name = "STAR Interview Response Builder",
            shortPurpose = "Structure behavioral interview stories (Situation, Task, Action, Result) into PDF",
            category = ToolCategory.CV,
            icon = Icons.Default.MenuBook,
            supportedFormats = "Output: Structured STAR Sheet & Printable PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Situation: App cold start exceeded 2.4s on mid-range devices.\nTask: Reduce startup latency under 800ms before Q4 launch.\nAction: Deferred non-critical initializers and added Baseline Profiles.\nResult: Cut cold start by 68% to 740ms and improved Play rating to 4.8★."
        ),
        ToolDefinition(
            id = "cv_invoice_generator",
            name = "Freelance Invoice & Receipt PDF Maker",
            shortPurpose = "Generate itemized client invoices with automatic tax and total calculation",
            category = ToolCategory.CV,
            icon = Icons.Default.ReceiptLong,
            supportedFormats = "Input: Line Items & Tax % • Output: Printable A4 Invoice PDF",
            engineType = EngineType.CAREER_LETTER_ENGINE,
            defaultInputSample = "Invoice #: INV-2026-108\nFrom: Rahul Shah Studio\nBill To: Horizon Ventures\nItem 1: Android Architecture Audit | 1200\nItem 2: UI/UX Performance Polish | 950\nTax%: 10"
        ),

        // --- Image & Graphics Tools (+6) ---
        ToolDefinition(
            id = "img_custom_resolution_export",
            name = "Custom Resolution & DPI Image Exporter",
            shortPurpose = "Export images at SD, 1080p FHD, 2K QHD, 4K UHD, or exact custom W×H & DPI",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.PhotoSizeSelectLarge,
            supportedFormats = "Presets: 720p, 1080p, 2K, 4K, 300 DPI Print, or Custom WxH@DPI",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            searchAliases = listOf("4k export", "1080p resize", "custom dpi", "uhd image"),
            defaultInputSample = "1920x1080@300",
            defaultParamOption = "PNG"
        ),
        ToolDefinition(
            id = "img_color_palette_extractor",
            name = "Dominant Color Palette & HEX Extractor",
            shortPurpose = "Sample image pixels to extract dominant HEX, RGB, and Compose color tokens",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.Tune,
            supportedFormats = "Input: Image • Output: Swatch Sheet PNG + HEX/RGB Codes",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE
        ),
        ToolDefinition(
            id = "img_grayscale_sepia_filter",
            name = "Studio Filter: Grayscale, Sepia & Invert",
            shortPurpose = "Apply hardware ColorMatrix filters (Grayscale, Sepia, Invert, High Contrast)",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.AutoFixHigh,
            supportedFormats = "Modes: GRAYSCALE, SEPIA, INVERT, CONTRAST",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultInputSample = "SEPIA"
        ),
        ToolDefinition(
            id = "img_watermark_stamper",
            name = "Photo Copyright & Watermark Stamper",
            shortPurpose = "Stamp diagonal or corner copyright watermarks directly onto images",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.WaterDrop,
            supportedFormats = "Input: Image + Watermark Text • Output: Watermarked PNG",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultInputSample = "© Rahul Shah • Toolverse Studio"
        ),
        ToolDefinition(
            id = "img_rounded_badge_maker",
            name = "Circle Avatar & Rounded Corner Maker",
            shortPurpose = "Mask rectangular photos into anti-aliased circular avatars or rounded cards",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.AccountBox,
            supportedFormats = "Output: 32-bit Transparent Alpha PNG (CIRCLE or ROUNDED)",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultInputSample = "CIRCLE"
        ),
        ToolDefinition(
            id = "img_grid_splitter",
            name = "3×3 Grid & Panorama Tile Splitter",
            shortPurpose = "Slice an image into a 3×3 social grid or 3×1 panorama contact sheet",
            category = ToolCategory.IMAGE,
            icon = Icons.Default.GridOn,
            supportedFormats = "Input: Image • Output: Sliced Grid Preview PNG & ZIP",
            engineType = EngineType.IMAGE_GRAPHICS_ENGINE,
            defaultInputSample = "3x3"
        ),

        // --- Data & Spreadsheet Tools (+5) ---
        ToolDefinition(
            id = "data_sql_insert_generator",
            name = "CSV to SQL CREATE & INSERT Generator",
            shortPurpose = "Convert CSV headers and rows into SQL CREATE TABLE and batch INSERT statements",
            category = ToolCategory.DATA,
            icon = Icons.Default.Storage,
            supportedFormats = "PostgreSQL / SQLite / MySQL compatible DDL & DML",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "id,username,role,points\n1,rahul,architect,980\n2,maya,engineer,920",
            defaultParamOption = "users_table"
        ),
        ToolDefinition(
            id = "data_markdown_table_generator",
            name = "CSV to Markdown & HTML Table Generator",
            shortPurpose = "Format CSV or TSV rows into aligned GitHub Markdown and HTML5 tables",
            category = ToolCategory.DATA,
            icon = Icons.Default.TableChart,
            supportedFormats = "Output: GitHub Flavored Markdown Table + HTML <table>",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "Feature,Status,Latency\nPDF Engine,Active,12ms\nFirestore Sync,Verified,38ms"
        ),
        ToolDefinition(
            id = "data_statistical_profiler",
            name = "Dataset Statistical Profiler & Outliers",
            shortPurpose = "Compute Mean, Median, StdDev, Variance, Min/Max, Quartiles, and Z-score outliers",
            category = ToolCategory.DATA,
            icon = Icons.Default.Analytics,
            supportedFormats = "Input: Numeric list or CSV column",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "12.5, 15.0, 14.2, 18.9, 16.4, 15.8, 42.0, 13.9, 15.2"
        ),
        ToolDefinition(
            id = "data_json_schema_generator",
            name = "JSON to Schema & Kotlin Data Class",
            shortPurpose = "Infer JSON Schema (Draft-07) and Kotlin @Serializable data classes from JSON",
            category = ToolCategory.DATA,
            icon = Icons.Default.Schema,
            supportedFormats = "Input: JSON Object • Output: JSON Schema + Kotlin Data Class",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "{\"id\":101,\"title\":\"Toolverse Pro\",\"rating\":4.95,\"isOfflineReady\":true}"
        ),
        ToolDefinition(
            id = "data_pivot_summarizer",
            name = "CSV Group-By & Pivot Aggregator",
            shortPurpose = "Group CSV rows by category column and compute Count, Sum, and Average",
            category = ToolCategory.DATA,
            icon = Icons.Default.TableRows,
            supportedFormats = "Input: CSV (Category, NumericValue) • Output: Aggregated Pivot Table",
            engineType = EngineType.DATA_SPREADSHEET_ENGINE,
            defaultInputSample = "Department,Budget\nEngineering,45000\nDesign,22000\nEngineering,38000\nSecurity,29000\nDesign,18000"
        ),

        // --- Text & Writing Utilities (+6) ---
        ToolDefinition(
            id = "text_readability_analyzer",
            name = "Flesch-Kincaid Readability & Grade Analyzer",
            shortPurpose = "Compute Flesch Reading Ease, Grade Level, syllable count, and sentence complexity",
            category = ToolCategory.TEXT,
            icon = Icons.Default.MenuBook,
            supportedFormats = "Flesch Reading Ease (0–100) + US Grade Level",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "Clear writing makes software documentation effortless to understand. Short sentences reduce cognitive load for readers."
        ),
        ToolDefinition(
            id = "text_slug_permalink",
            name = "SEO URL Slug & Safe Filename Generator",
            shortPurpose = "Convert titles into clean URL slugs, git branch names, and cross-OS filenames",
            category = ToolCategory.TEXT,
            icon = Icons.Default.Link,
            supportedFormats = "kebab-slug, snake_slug, git-branch, safe_filename",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "Toolverse 2.0: 175+ Native Android Utilities & Pastel Themes!"
        ),
        ToolDefinition(
            id = "text_morse_braille_cipher",
            name = "Morse Code, NATO Phonetic & ROT13 Converter",
            shortPurpose = "Translate text simultaneously into ITU Morse code, NATO phonetic, and ROT13",
            category = ToolCategory.TEXT,
            icon = Icons.Default.Translate,
            supportedFormats = "ITU Morse • ICAO NATO Alphabet • ROT13 / Caesar Cipher",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "TOOLVERSE 2026"
        ),
        ToolDefinition(
            id = "text_number_to_words",
            name = "Number to Words & Check Amount Writer",
            shortPurpose = "Spell out numbers into English cardinal words and formal check-writing format",
            category = ToolCategory.TEXT,
            icon = Icons.Default.Numbers,
            supportedFormats = "Supports integers and decimals up to trillions",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "148250.75"
        ),
        ToolDefinition(
            id = "text_line_affix_wrapper",
            name = "Batch Line Prefix, Suffix & SQL/HTML Wrapper",
            shortPurpose = "Wrap every line with custom prefix/suffix, SQL string quotes, or HTML <li> tags",
            category = ToolCategory.TEXT,
            icon = Icons.Default.TextFields,
            supportedFormats = "Custom prefix|suffix, SQL ('...'), JSON, HTML list",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "alpha\nbeta\ngamma\ndelta",
            defaultParamOption = "'|',"
        ),
        ToolDefinition(
            id = "text_ngram_frequency",
            name = "Keyword Density & N-Gram Frequency Counter",
            shortPurpose = "Extract top 1-word, 2-word, and 3-word phrases with exact density percentages",
            category = ToolCategory.TEXT,
            icon = Icons.Default.Analytics,
            supportedFormats = "Unigram, Bigram & Trigram lexical frequency table",
            engineType = EngineType.TEXT_PROCESSING_ENGINE,
            defaultInputSample = "fast pdf tools and fast image tools make mobile productivity fast and reliable"
        ),

        // --- File & Archive Tools (+4) ---
        ToolDefinition(
            id = "file_tar_inspector",
            name = "POSIX TAR Archive Builder & Inspector",
            shortPurpose = "Package text/files into an uncompressed POSIX UStar .tar archive or inspect headers",
            category = ToolCategory.FILE,
            icon = Icons.Default.Archive,
            supportedFormats = "POSIX.1-1988 UStar 512-byte block .tar container",
            engineType = EngineType.FILE_ARCHIVE_ENGINE,
            defaultInputSample = "notes.txt:Hello from Toolverse POSIX TAR Builder\nconfig.ini:theme=pastel_mint"
        ),
        ToolDefinition(
            id = "file_hex_dump_viewer",
            name = "Binary Hex Dump & ASCII Offset Viewer",
            shortPurpose = "Inspect raw file or text bytes in canonical 16-byte hexadecimal + ASCII columns",
            category = ToolCategory.FILE,
            icon = Icons.Default.Code,
            supportedFormats = "Canonical xxd / hexdump -C format (Offset | Hex | ASCII)",
            engineType = EngineType.FILE_ARCHIVE_ENGINE,
            defaultInputSample = "%PDF-1.7\nToolverse Binary Stream Inspector 2026"
        ),
        ToolDefinition(
            id = "file_base64_file_codec",
            name = "File to Base64 Data-URI & Binary Encoder",
            shortPurpose = "Encode any picked file into a self-contained RFC 2397 data: URI or raw Base64",
            category = ToolCategory.FILE,
            icon = Icons.Default.SwapHoriz,
            supportedFormats = "Output: data:<mime>;base64,... & downloadable .b64.txt",
            engineType = EngineType.FILE_ARCHIVE_ENGINE,
            defaultInputSample = "SVG/Text payload for inline data URI embedding"
        ),
        ToolDefinition(
            id = "file_line_ending_normalizer",
            name = "CRLF / LF Line-Ending & UTF-8 BOM Normalizer",
            shortPurpose = "Detect Windows CRLF vs. Unix LF line endings, strip UTF-8 BOM, and normalize",
            category = ToolCategory.FILE,
            icon = Icons.Default.CleaningServices,
            supportedFormats = "Converts CRLF <-> LF and removes 0xEFBBBF BOM",
            engineType = EngineType.FILE_ARCHIVE_ENGINE,
            defaultInputSample = "Line 1\r\nLine 2\r\nLine 3"
        ),

        // --- Security & Cryptography (+5) ---
        ToolDefinition(
            id = "sec_hmac_signature",
            name = "HMAC-SHA256 & HMAC-SHA512 Signer",
            shortPurpose = "Compute keyed RFC 2104 HMAC signatures for webhooks and API authentication",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Key,
            supportedFormats = "HmacSHA256 & HmacSHA512 (Hex + Base64 outputs)",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "{\"event\":\"order.completed\",\"amount\":4900}",
            defaultParamOption = "whsec_live_secret_key_2026"
        ),
        ToolDefinition(
            id = "sec_totp_generator",
            name = "2FA TOTP Code Generator (RFC 6238)",
            shortPurpose = "Generate live 6-digit 30-second 2FA TOTP verification codes from Base32 secret",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Pin,
            supportedFormats = "RFC 6238 HMAC-SHA1 6-Digit TOTP + Remaining Window",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "JBSWY3DPEHPK3PXP"
        ),
        ToolDefinition(
            id = "sec_password_strength_auditor",
            name = "Password Entropy & Crack-Time Auditor",
            shortPurpose = "Analyze character pool, Shannon entropy bits, and offline brute-force resistance",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Shield,
            supportedFormats = "Entropy bits, character classes & crack-time estimation",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "Tr0ub4dor&3# PastelMint2026"
        ),
        ToolDefinition(
            id = "sec_pii_redactor",
            name = "Log & Document PII Privacy Redactor",
            shortPurpose = "Automatically mask emails, phone numbers, IPv4 addresses, cards, and bearer tokens",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.PrivacyTip,
            supportedFormats = "Redacts Email, Phone, IPv4, Credit Card, and API Keys",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "User rahul@example.com logged in from 192.168.1.42 with phone +1-555-839-2019 and card 4532-0151-1283-0366."
        ),
        ToolDefinition(
            id = "sec_pbkdf2_key_deriver",
            name = "PBKDF2 Key Derivation Inspector",
            shortPurpose = "Derive deterministic 256-bit cryptographic keys using PBKDF2-HMAC-SHA256",
            category = ToolCategory.SECURITY,
            icon = Icons.Default.Fingerprint,
            supportedFormats = "PBKDF2WithHmacSHA256 • 65,536 Iterations • 256-bit Key",
            engineType = EngineType.SECURITY_CRYPTO_ENGINE,
            defaultInputSample = "MasterPassphrase#2026",
            defaultParamOption = "toolverse_salt_v1"
        ),

        // --- Calculators & Converters (+6) ---
        ToolDefinition(
            id = "calc_emi_loan",
            name = "Loan, Mortgage & EMI Amortization Calculator",
            shortPurpose = "Compute monthly EMI, total interest payable, and principal payoff breakdown",
            category = ToolCategory.CALC,
            icon = Icons.Default.MonetizationOn,
            supportedFormats = "Input: Principal, Annual Rate %, Tenure Months",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "250000, 6.5, 240"
        ),
        ToolDefinition(
            id = "calc_compound_interest",
            name = "Compound Interest & SIP Wealth Calculator",
            shortPurpose = "Project lump-sum and monthly SIP compound growth over time",
            category = ToolCategory.CALC,
            icon = Icons.Default.Analytics,
            supportedFormats = "Input: Principal, Monthly Contribution, Annual Rate %, Years",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "10000, 500, 9.5, 10"
        ),
        ToolDefinition(
            id = "calc_bmi_bmr_macros",
            name = "BMI, BMR & Daily Calorie/Macro Calculator",
            shortPurpose = "Calculate BMI category, Mifflin-St Jeor BMR, TDEE, and daily macro targets",
            category = ToolCategory.CALC,
            icon = Icons.Default.Scale,
            supportedFormats = "Input: Weight(kg), Height(cm), Age(yrs)",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "72, 178, 28"
        ),
        ToolDefinition(
            id = "calc_number_base_radix",
            name = "Programmer Base & Bitwise Calculator",
            shortPurpose = "Convert between Binary (2), Octal (8), Decimal (10), Hex (16) and bitwise ops",
            category = ToolCategory.CALC,
            icon = Icons.Default.Calculate,
            supportedFormats = "DEC <-> HEX <-> BIN <-> OCT + Bitwise shifts",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "4096"
        ),
        ToolDefinition(
            id = "calc_aspect_ratio_dpi",
            name = "Screen Aspect Ratio, PPI & Resolution Calculator",
            shortPurpose = "Compute display PPI/DPI, aspect ratio, megapixels, and scaling multipliers",
            category = ToolCategory.CALC,
            icon = Icons.Default.PhotoSizeSelectLarge,
            supportedFormats = "Input: Width px, Height px, Diagonal inches",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "2560, 1440, 27"
        ),
        ToolDefinition(
            id = "calc_speed_fuel_energy",
            name = "Speed, Pressure, Power & Fuel Converter",
            shortPurpose = "Convert km/h, mph, knots, m/s, bar, PSI, kW, HP, and L/100km to MPG",
            category = ToolCategory.CALC,
            icon = Icons.Default.Straighten,
            supportedFormats = "Automotive, Physics & Engineering Units",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            defaultInputSample = "100"
        ),

        // --- QR & Barcode Tools (+4) ---
        ToolDefinition(
            id = "qr_calendar_event",
            name = "Calendar Event (iCal VEVENT) QR Generator",
            shortPurpose = "Create scannable calendar event QR codes for instant schedule import",
            category = ToolCategory.QR,
            icon = Icons.Default.CalendarMonth,
            supportedFormats = "iCalendar VEVENT QR PNG",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "SUMMARY:Toolverse Launch Sync|START:20261115T100000Z|LOCATION:Studio Room A"
        ),
        ToolDefinition(
            id = "qr_geo_location",
            name = "Geo-Location & Maps Coordinate QR Generator",
            shortPurpose = "Generate scannable geo:latitude,longitude QR codes for navigation apps",
            category = ToolCategory.QR,
            icon = Icons.Default.QrCode2,
            supportedFormats = "RFC 5870 geo:lat,lng QR Code PNG",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "37.7749,-122.4194"
        ),
        ToolDefinition(
            id = "qr_upi_payment_link",
            name = "Payment & UPI Deep-Link QR Generator",
            shortPurpose = "Generate standardized payment link and UPI merchant QR codes",
            category = ToolCategory.QR,
            icon = Icons.Default.MonetizationOn,
            supportedFormats = "UPI / Payment URI High-Contrast QR PNG",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "upi://pay?pa=rahulshah@upi&pn=Rahul%20Shah&am=499.00&cu=INR"
        ),
        ToolDefinition(
            id = "qr_batch_sheet_pdf",
            name = "Printable QR Label Sheet (3×4 A4 PDF)",
            shortPurpose = "Generate a printable A4 sheet of 12 scannable QR asset labels",
            category = ToolCategory.QR,
            icon = Icons.Default.PictureAsPdf,
            supportedFormats = "Input: Base Payload/Prefix • Output: 12-Label A4 PDF",
            engineType = EngineType.QR_BARCODE_ENGINE,
            defaultInputSample = "ASSET-TV-2026"
        ),

        // --- Audio & Waveform Tools (+3) ---
        ToolDefinition(
            id = "audio_dtmf_dial_synthesizer",
            name = "DTMF Touch-Tone Keypad WAV Synthesizer",
            shortPurpose = "Synthesize dual-tone multi-frequency (0–9, *, #, A–D) dial sequences to WAV",
            category = ToolCategory.AUDIO,
            icon = Icons.Default.AudioFile,
            supportedFormats = "ITU-T Q.23 Dual-Tone 16-bit PCM .wav",
            engineType = EngineType.AUDIO_MEDIA_ENGINE,
            defaultInputSample = "18005550199#"
        ),
        ToolDefinition(
            id = "audio_chord_progression_wav",
            name = "Musical Chord & Metronome WAV Generator",
            shortPurpose = "Synthesize harmonic triad chords and precision BPM metronome clicks to WAV",
            category = ToolCategory.AUDIO,
            icon = Icons.Default.Equalizer,
            supportedFormats = "Polyphonic 16-bit PCM .wav (44.1 kHz)",
            engineType = EngineType.AUDIO_MEDIA_ENGINE,
            defaultInputSample = "120"
        ),
        ToolDefinition(
            id = "audio_waveform_visualizer",
            name = "Audio Peak, RMS dBFS & Waveform Renderer",
            shortPurpose = "Analyze audio crest factor, RMS dBFS, and export a high-res waveform PNG",
            category = ToolCategory.AUDIO,
            icon = Icons.Default.GraphicEq,
            supportedFormats = "Input: Audio/WAV • Output: Waveform Plot PNG + dBFS Metrics",
            engineType = EngineType.AUDIO_MEDIA_ENGINE,
            defaultInputSample = "440, 2.0"
        ),

        // --- Developer Workflows (+4) ---
        ToolDefinition(
            id = "dev_cron_expression_parser",
            name = "Cron Expression Explainer & Schedule Tester",
            shortPurpose = "Translate 5-field cron expressions into human-readable schedules",
            category = ToolCategory.DEV,
            icon = Icons.Default.Schedule,
            supportedFormats = "Standard 5-field POSIX Cron (min hour dom mon dow)",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "30 9 * * 1-5"
        ),
        ToolDefinition(
            id = "dev_color_converter",
            name = "Color Space & WCAG Contrast Calculator",
            shortPurpose = "Convert HEX to RGB, HSL, CMYK, Compose Color(), and check WCAG AA/AAA contrast",
            category = ToolCategory.DEV,
            icon = Icons.Default.Tune,
            supportedFormats = "HEX • RGB • HSL • CMYK • Jetpack Compose Color • WCAG 2.1",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "#0F5338",
            defaultParamOption = "#FAF8F3"
        ),
        ToolDefinition(
            id = "dev_cidr_subnet_calc",
            name = "IPv4 CIDR Subnet & Host Range Calculator",
            shortPurpose = "Calculate netmask, wildcard mask, network/broadcast IPs, and usable host count",
            category = ToolCategory.DEV,
            icon = Icons.Default.DeveloperMode,
            supportedFormats = "IPv4 Address/Prefix (e.g. 192.168.10.0/24)",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "192.168.10.42/24"
        ),
        ToolDefinition(
            id = "dev_http_curl_inspect",
            name = "cURL to Kotlin OkHttp & Request Inspector",
            shortPurpose = "Convert cURL commands into ready-to-compile Kotlin OkHttp client code",
            category = ToolCategory.DEV,
            icon = Icons.Default.Http,
            supportedFormats = "Input: cURL command • Output: Kotlin OkHttp Request snippet",
            engineType = EngineType.DEVELOPER_UTILITY_ENGINE,
            defaultInputSample = "curl -X POST https://api.toolverse.app/v1/convert -H \"Authorization: Bearer tok_123\" -H \"Content-Type: application/json\" -d '{\"format\":\"pdf\"}'"
        ),

        // ==================== 12. VIDEO EDITING & PROCESSING STUDIO (20 Tools) ====================
        ToolDefinition(
            id = "video_trimmer_cutter",
            name = "Video Trimmer & Cutter",
            shortPurpose = "Cut and trim video start/end timestamps losslessly via MediaMuxer or frame re-encode",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.ContentCut,
            supportedFormats = "Input: MP4 / MOV / WEBM + Start–End sec • Output: Trimmed .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("cut video", "trim mp4", "video clipper"),
            defaultInputSample = "0.5 to 3.5",
            defaultParamOption = "MP4"
        ),
        ToolDefinition(
            id = "video_splitter",
            name = "Video Splitter (Multi-Segment)",
            shortPurpose = "Split a video into equal duration parts or custom timestamp chapters (.zip / .mp4)",
            category = ToolCategory.VIDEO,
            icon = Icons.AutoMirrored.Filled.CallSplit,
            supportedFormats = "Input: Video + Segment Count or Duration • Output: Segmented .mp4 & .zip",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("split video", "divide mp4", "video chapters"),
            defaultInputSample = "3",
            defaultParamOption = "EQUAL_PARTS"
        ),
        ToolDefinition(
            id = "video_merger",
            name = "Video Merger & Joiner",
            shortPurpose = "Combine and stitch multiple video clips sequentially into a unified H.264 MP4 stream",
            category = ToolCategory.VIDEO,
            icon = Icons.AutoMirrored.Filled.MergeType,
            supportedFormats = "Input: 2+ Video Clips • Output: Merged H.264 .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("merge video", "join mp4", "combine clips"),
            defaultInputSample = "Clip 1 + Clip 2 Seamless Concatenation",
            defaultParamOption = "1280x720"
        ),
        ToolDefinition(
            id = "video_slow_motion",
            name = "Slow-Motion Video Maker",
            shortPurpose = "Create smooth 0.25x, 0.5x, or 0.75x slow-motion videos by scaling PTS presentation timestamps",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.SlowMotionVideo,
            supportedFormats = "Speed Multipliers: 0.25x, 0.50x, 0.75x • Output: Slow-Mo .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("slowmo", "slow motion", "half speed video"),
            defaultInputSample = "0.5",
            defaultParamOption = "0.5x"
        ),
        ToolDefinition(
            id = "video_fast_motion",
            name = "Fast-Motion & Timelapse Video Maker",
            shortPurpose = "Accelerate video playback to 1.5x, 2x, 4x, or 8x hyperlapse speed",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.FastForward,
            supportedFormats = "Speed Multipliers: 1.5x, 2.0x, 4.0x, 8.0x • Output: Fast-Mo .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("timelapse", "fast forward video", "2x speed"),
            defaultInputSample = "2.0",
            defaultParamOption = "2.0x"
        ),
        ToolDefinition(
            id = "video_speed_controller",
            name = "Video Speed Controller (Custom Speed)",
            shortPurpose = "Fine-tune playback speed from 0.10x to 10.0x with precision PTS remapping",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.Speed,
            supportedFormats = "Custom Speed Factor (0.10x – 10.0x) • Output: .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("custom video speed", "tempo video", "frame cadence"),
            defaultInputSample = "1.25",
            defaultParamOption = "KEEP_PITCH"
        ),
        ToolDefinition(
            id = "video_hq_encoder",
            name = "High-Quality Video Encoder (H.264/AVC)",
            shortPurpose = "Encode video streams using hardware MediaCodec H.264 AVC with selectable CRF/Bitrate",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.HighQuality,
            supportedFormats = "Hardware MediaCodec video/avc • Baseline / High Profile .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("h264 encoder", "avc mp4", "master quality video"),
            defaultInputSample = "1920x1080@30fps",
            defaultParamOption = "8000kbps"
        ),
        ToolDefinition(
            id = "video_resolution_converter",
            name = "Video Resolution Converter",
            shortPurpose = "Scale video dimensions to 480p SD, 720p HD, 1080p FHD, 2K QHD, or 4K UHD",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.PhotoSizeSelectLarge,
            supportedFormats = "Presets: 854×480, 1280×720, 1920×1080, 2560×1440, 3840×2160",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("resize video", "1080p video", "4k video scaler"),
            defaultInputSample = "1920x1080",
            defaultParamOption = "1080p_FHD"
        ),
        ToolDefinition(
            id = "video_fps_converter",
            name = "Video FPS Frame-Rate Converter",
            shortPurpose = "Convert video frame rate between 24fps Cinema, 30fps Standard, and 60fps Smooth",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.Movie,
            supportedFormats = "Target Frame Rates: 15, 24, 25, 30, 50, 60 FPS • Output: .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("60fps converter", "24fps cinema", "frame rate changer"),
            defaultInputSample = "60",
            defaultParamOption = "60_FPS"
        ),
        ToolDefinition(
            id = "video_bitrate_controller",
            name = "Video Bitrate Controller (CBR / VBR)",
            shortPurpose = "Set exact target video bitrate in kbps/Mbps and inspect Bits-Per-Pixel (BPP) density",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.Tune,
            supportedFormats = "Bitrate Range: 500 kbps – 25,000 kbps • Output: Re-encoded .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("video bitrate", "cbr vbr", "kbps video"),
            defaultInputSample = "4500",
            defaultParamOption = "VBR"
        ),
        ToolDefinition(
            id = "video_format_converter",
            name = "Video Format Converter (MP4, MOV, MKV, WEBM, AVI)",
            shortPurpose = "Transcode and remux video streams across MP4, MOV, MKV, WEBM, and AVI containers",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.Transform,
            supportedFormats = "Target Containers: MP4, MOV, MKV, WEBM, AVI",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("mp4 to mov", "mkv to mp4", "webm converter", "avi to mp4"),
            defaultInputSample = "MP4",
            defaultParamOption = "MP4"
        ),
        ToolDefinition(
            id = "video_compressor_custom_size",
            name = "Video Compressor (Custom Target MB Size)",
            shortPurpose = "Compress video to fit an exact target file size (e.g., 8 MB, 16 MB, 25 MB for messaging/email)",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.Compress,
            supportedFormats = "Target Size (MB) -> Auto Bitrate & Resolution Optimization .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("shrink video", "compress mp4", "discord 10mb video"),
            defaultInputSample = "10",
            defaultParamOption = "10MB"
        ),
        ToolDefinition(
            id = "video_quality_enhancer",
            name = "Video Quality & Dynamic Range Enhancer",
            shortPurpose = "Enhance shadow detail, vibrance, and perceptual dynamic range across video frames",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.AutoFixHigh,
            supportedFormats = "ColorMatrix Luminance & Saturation Boost • Output: Enhanced .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("upscale video", "hdr enhance", "clear video"),
            defaultInputSample = "1.20",
            defaultParamOption = "HDR_TONE"
        ),
        ToolDefinition(
            id = "video_sharpness_enhancer",
            name = "Video Sharpness & Detail Enhancer",
            shortPurpose = "Apply spatial unsharp-mask edge enhancement to crisp up soft or blurry footage",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.FilterFrames,
            supportedFormats = "3×3 High-Pass Sharpening Kernel • Output: Sharpened .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("sharpen video", "unblur video", "detail boost"),
            defaultInputSample = "1.35",
            defaultParamOption = "CRISP"
        ),
        ToolDefinition(
            id = "video_brightness_contrast",
            name = "Video Brightness & Contrast Adjuster",
            shortPurpose = "Calibrate video exposure brightness (-100 to +100) and contrast multiplier (0.5x to 2.0x)",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.Tune,
            supportedFormats = "Input: BrightnessOffset, ContrastScale • Output: Color-Graded .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("brighten dark video", "video exposure", "color grade"),
            defaultInputSample = "18, 1.22",
            defaultParamOption = "WARM_GRADE"
        ),
        ToolDefinition(
            id = "video_stabilizer",
            name = "Video Stabilizer & Motion Deshaker",
            shortPurpose = "Smooth handheld camera jitter using safe-margin motion vector crop stabilization",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.Videocam,
            supportedFormats = "Motion Smoothing Crop Margin (5%–15%) • Output: Stabilized .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("stabilize shaky video", "deshake mp4", "gimbal smooth"),
            defaultInputSample = "8",
            defaultParamOption = "SMOOTH_8PCT"
        ),
        ToolDefinition(
            id = "video_mute_audio_extractor",
            name = "Video Mute & Audio Track Extractor",
            shortPurpose = "Strip audio track to create a silent MP4 OR extract the audio stream to a lossless WAV file",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.VolumeOff,
            supportedFormats = "Modes: MUTE_VIDEO (.mp4) or EXTRACT_AUDIO (.wav)",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("remove audio from video", "mp4 to wav", "silent video"),
            defaultInputSample = "MUTE_VIDEO",
            defaultParamOption = "MUTE_VIDEO"
        ),
        ToolDefinition(
            id = "video_to_gif",
            name = "Video-to-GIF Animated Converter",
            shortPurpose = "Extract video frames and encode a loopable animated GIF89a image with custom FPS",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.GifBox,
            supportedFormats = "Input: Video • Output: Multi-Frame Animated .gif",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("mp4 to gif", "animated gif maker", "video clip to gif"),
            defaultInputSample = "10",
            defaultParamOption = "480p"
        ),
        ToolDefinition(
            id = "gif_to_video",
            name = "GIF-to-Video (MP4) Converter",
            shortPurpose = "Convert animated GIF images into high-efficiency H.264 MP4 video files for social sharing",
            category = ToolCategory.VIDEO,
            icon = Icons.Default.Movie,
            supportedFormats = "Input: Animated .gif • Output: Loopable H.264 .mp4",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("gif to mp4", "convert gif to video", "instagram gif mp4"),
            defaultInputSample = "3",
            defaultParamOption = "720p"
        ),
        ToolDefinition(
            id = "video_rotate_aspect_ratio",
            name = "Video Rotation & Aspect-Ratio Converter",
            shortPurpose = "Rotate video 90°/180°/270° and reframe to 16:9, 9:16 Reels/Shorts, 1:1 Square, or 4:5",
            category = ToolCategory.VIDEO,
            icon = Icons.AutoMirrored.Filled.RotateRight,
            supportedFormats = "Orientations: 0°, 90°, 180°, 270° • Ratios: 16:9, 9:16, 1:1, 4:5",
            engineType = EngineType.VIDEO_PROCESSING_ENGINE,
            searchAliases = listOf("rotate mp4", "9:16 vertical video", "16:9 letterbox"),
            defaultInputSample = "90, 9:16",
            defaultParamOption = "9:16"
        ),

        // ==================== 13. FINANCE & EVERYDAY UTILITIES (+10 Unique Tools) ====================
        ToolDefinition(
            id = "calc_bill_splitter",
            name = "Bill Splitter with Individual Contributions",
            shortPurpose = "Split dining or group bills with individual item amounts, shared tax/tip, and exact per-person totals",
            category = ToolCategory.CALC,
            icon = Icons.Default.ReceiptLong,
            supportedFormats = "Input: Person:Amount lines + Tax% | Tip% • Output: Itemized Split Breakdown",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("split restaurant bill", "unequal bill split", "dinner calculator"),
            defaultInputSample = "Rahul: 28.50\nMaya: 22.00\nAlex: 31.75\nLiam: 19.25",
            defaultParamOption = "Tax:8.5|Tip:18"
        ),
        ToolDefinition(
            id = "calc_tip_calculator",
            name = "Smart Tip & Gratuity Calculator",
            shortPurpose = "Calculate tip amounts across service tiers (10%–25%), pre/post-tax options, and per-person rounding",
            category = ToolCategory.CALC,
            icon = Icons.Default.MonetizationOn,
            supportedFormats = "Input: Bill Amount, Tip %, Split Count, Tax %",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("gratuity calculator", "restaurant tip", "tip per person"),
            defaultInputSample = "124.80, 18, 4, 8.25"
        ),
        ToolDefinition(
            id = "calc_shared_expense",
            name = "Shared Expense & Proportional Split Calculator",
            shortPurpose = "Split rent, utilities, and household expenses equally or proportionally by each member's income",
            category = ToolCategory.CALC,
            icon = Icons.Default.Groups,
            supportedFormats = "Input: Total Expense + Member:Income/Weight • Output: Fair Proportional Share",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("roommate rent split", "income proportional split", "utility bill share"),
            defaultInputSample = "Expense: 3200\nRahul: 95000\nMaya: 78000\nAlex: 67000"
        ),
        ToolDefinition(
            id = "calc_group_expense_tracker",
            name = "Group Expense Tracker & Debt Simplifier",
            shortPurpose = "Log multi-person trip expenses and compute the minimum settlement transfers (Who Pays Whom)",
            category = ToolCategory.CALC,
            icon = Icons.AutoMirrored.Filled.CompareArrows,
            supportedFormats = "Input: Payer:Amount:Description lines • Output: Net Balances & Min Transfers",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("splitwise alternative", "settle up debts", "trip expense tracker"),
            defaultInputSample = "Rahul: 240 : Hotel Booking\nMaya: 95 : Airport Taxi\nAlex: 160 : Group Dinner\nLiam: 45 : Museum Tickets"
        ),
        ToolDefinition(
            id = "calc_savings_goal",
            name = "Savings Goal & Target Date Calculator",
            shortPurpose = "Calculate required monthly savings to hit a financial goal or months needed at your current save rate",
            category = ToolCategory.CALC,
            icon = Icons.Default.Savings,
            supportedFormats = "Input: Target Goal, Current Saved, Monthly Contribution, APY %",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("emergency fund calculator", "down payment goal", "savings timeline"),
            defaultInputSample = "50000, 8500, 950, 5.2"
        ),
        ToolDefinition(
            id = "calc_budget_planner",
            name = "50/30/20 & Monthly Budget Planner",
            shortPurpose = "Allocate monthly take-home income across Needs (50%), Wants (30%), and Savings/Debt (20%) with audit",
            category = ToolCategory.CALC,
            icon = Icons.Default.Analytics,
            supportedFormats = "Input: Net Monthly Income + Expense Categories • Output: Budget Health Audit",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("monthly budget", "50 30 20 rule", "personal finance planner"),
            defaultInputSample = "Income: 6200\nNeeds (Rent/Groceries/Utilities): 2850\nWants (Dining/Travel/Shopping): 1650\nSavings & Investments: 1700"
        ),
        ToolDefinition(
            id = "calc_currency_converter_live",
            name = "Currency Converter (Live Exchange Rates)",
            shortPurpose = "Convert between 25+ world currencies using real-time HTTPS FX rates with offline resilience",
            category = ToolCategory.CALC,
            icon = Icons.Default.CurrencyExchange,
            supportedFormats = "Live HTTPS API (open.er-api.com) • USD, EUR, GBP, INR, JPY, CAD, AUD, CHF, SGD, AED",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("forex converter", "usd to inr", "eur to usd", "live exchange rate"),
            defaultInputSample = "250 USD to INR, EUR, GBP, JPY, CAD, AUD",
            defaultParamOption = "USD"
        ),
        ToolDefinition(
            id = "calc_roi_cagr_analyzer",
            name = "Investment ROI & CAGR Calculator",
            shortPurpose = "Compute total Return on Investment %, Compound Annual Growth Rate (CAGR), and Rule-of-72 doubling time",
            category = ToolCategory.CALC,
            icon = Icons.Default.MonetizationOn,
            supportedFormats = "Input: Initial Investment, Final Value, Duration Years",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("cagr calculator", "stock roi", "annualized return"),
            defaultInputSample = "15000, 28500, 5"
        ),
        ToolDefinition(
            id = "calc_net_worth_fire",
            name = "Net Worth & FIRE Retirement Calculator",
            shortPurpose = "Calculate Total Net Worth (Assets − Liabilities), 4% Safe Withdrawal Rate, and FIRE target number",
            category = ToolCategory.CALC,
            icon = Icons.Default.Savings,
            supportedFormats = "Input: Assets, Liabilities, Annual Expenses • Output: Net Worth & FIRE Readiness",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("financial independence", "fire number", "net worth tracker"),
            defaultInputSample = "Assets: 420000 | Liabilities: 85000 | AnnualSpend: 42000"
        ),
        ToolDefinition(
            id = "calc_credit_card_payoff",
            name = "Credit Card Debt Payoff & Interest Saver",
            shortPurpose = "Calculate exact months to debt-free, total interest cost, and savings from extra monthly payments",
            category = ToolCategory.CALC,
            icon = Icons.Default.Calculate,
            supportedFormats = "Input: Balance, APR %, Monthly Payment, Extra Payment",
            engineType = EngineType.CALCULATOR_CONVERTER_ENGINE,
            searchAliases = listOf("debt snowball", "credit card interest", "loan payoff"),
            defaultInputSample = "6800, 21.9, 220, 100"
        )
    )

    private val toolsById: Map<String, ToolDefinition> = allTools.associateBy { it.id }

    fun getToolById(toolId: String): ToolDefinition? = toolsById[toolId]

    fun getToolsByCategory(category: ToolCategory): List<ToolDefinition> =
        allTools.filter { it.category == category }

    fun getCategoryCount(category: ToolCategory): Int =
        allTools.count { it.category == category }

    fun searchTools(query: String, selectedCategory: ToolCategory? = null): List<ToolDefinition> {
        val trimmed = query.trim().lowercase()
        return allTools.filter { tool ->
            val matchesCategory = selectedCategory == null || tool.category == selectedCategory
            val matchesQuery = trimmed.isEmpty() ||
                tool.name.lowercase().contains(trimmed) ||
                tool.shortPurpose.lowercase().contains(trimmed) ||
                tool.category.title.lowercase().contains(trimmed) ||
                tool.supportedFormats.lowercase().contains(trimmed) ||
                tool.searchAliases.any { it.lowercase().contains(trimmed) }
            matchesCategory && matchesQuery
        }
    }
}
