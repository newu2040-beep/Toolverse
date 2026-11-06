package com.example.ui.screens

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.example.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

fun attemptAutoSignIn(
    context: Context,
    credentialManager: CredentialManager,
    onAuthSuccess: () -> Unit,
    onUnauthenticated: () -> Unit,
    scope: CoroutineScope
) {
    if (Firebase.auth.currentUser != null) {
        onAuthSuccess()
        return
    }
    val clientId = try {
        context.getString(R.string.default_web_client_id)
    } catch (e: Exception) {
        onUnauthenticated()
        return
    }

    val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(true)
        .setServerClientId(clientId)
        .setAutoSelectEnabled(true)
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    scope.launch {
        try {
            val result = credentialManager.getCredential(context, request)
            val credential = result.credential
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdToken = GoogleIdTokenCredential.createFrom(credential.data).idToken
                val authCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                Firebase.auth.signInWithCredential(authCredential).await()
                onAuthSuccess()
            } else {
                onUnauthenticated()
            }
        } catch (e: Exception) {
            onUnauthenticated()
        }
    }
}

fun onGoogleSignInClicked(
    context: Context,
    credentialManager: CredentialManager,
    onAuthSuccess: () -> Unit,
    onAuthError: (String) -> Unit,
    scope: CoroutineScope,
    onAuthCancelled: () -> Unit = {}
) {
    val clientId = try {
        context.getString(R.string.default_web_client_id)
    } catch (e: Exception) {
        onAuthError("Google Sign-In configuration missing: default_web_client_id not found")
        return
    }

    val signInOption = GetSignInWithGoogleOption.Builder(serverClientId = clientId).build()
    val request = GetCredentialRequest.Builder()
        .addCredentialOption(signInOption)
        .build()

    scope.launch {
        try {
            val activityContext = context as? Activity ?: context
            val result = credentialManager.getCredential(activityContext, request)
            val credential = result.credential
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdToken = GoogleIdTokenCredential.createFrom(credential.data).idToken
                val authCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                Firebase.auth.signInWithCredential(authCredential).await()
                onAuthSuccess()
            } else {
                onAuthError("Unexpected credential type returned.")
            }
        } catch (e: GetCredentialCancellationException) {
            Log.w("Auth", "Google Sign-In cancelled or dismissed: ${e.message}", e)
            onAuthCancelled()
        } catch (e: Exception) {
            Log.e("Auth", "Google Sign-In failed", e)
            onAuthError(e.localizedMessage ?: "Google Sign-In failed. Please check network and try again.")
        }
    }
}

fun signOutUser(
    context: Context,
    credentialManager: CredentialManager,
    onSignOutComplete: () -> Unit,
    scope: CoroutineScope
) {
    Firebase.auth.signOut()
    scope.launch {
        try {
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
        } catch (e: Exception) {
            Log.e("Auth", "Failed to clear credential state", e)
        } finally {
            onSignOutComplete()
        }
    }
}

@Composable
fun WelcomeAuthScreen(
    onAuthSuccess: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val credentialManager = remember { CredentialManager.create(context) }

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var wasCancelled by remember { mutableStateOf(false) }
    var showLegalDialog by remember { mutableStateOf(false) }
    var showEmailProviderInfoDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        attemptAutoSignIn(
            context = context,
            credentialManager = credentialManager,
            onAuthSuccess = onAuthSuccess,
            onUnauthenticated = {},
            scope = coroutineScope
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF8F3))
    ) {
        // Organic warm cream + sage green botanical wave artwork matching Screen 1 mockup
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            val topWave = Path().apply {
                moveTo(w * 0.55f, 0f)
                cubicTo(w * 0.72f, h * 0.08f, w * 0.88f, h * 0.12f, w, h * 0.24f)
                lineTo(w, 0f)
                close()
            }
            drawPath(
                path = topWave,
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFD8F0DF), Color(0xFFF3EFE6))
                )
            )

            val bottomWave = Path().apply {
                moveTo(0f, h * 0.54f)
                cubicTo(w * 0.28f, h * 0.48f, w * 0.72f, h * 0.60f, w, h * 0.50f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(
                path = bottomWave,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFCDEBD7).copy(alpha = 0.65f),
                        Color(0xFF66B58B).copy(alpha = 0.55f),
                        Color(0xFFFAF8F3)
                    ),
                    startY = h * 0.48f,
                    endY = h
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp, vertical = 20.dp)
                .widthIn(max = 480.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Brand Identity Header
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(86.dp)
                        .shadow(12.dp, CircleShape, spotColor = Color(0xFF0F5338))
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF15734E), Color(0xFF0A3D28))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.size(48.dp)) {
                        val path = Path().apply {
                            moveTo(size.width * 0.18f, size.height * 0.25f)
                            lineTo(size.width * 0.88f, size.height * 0.20f)
                            cubicTo(
                                size.width * 0.82f, size.height * 0.42f,
                                size.width * 0.64f, size.height * 0.45f,
                                size.width * 0.52f, size.height * 0.45f
                            )
                            lineTo(size.width * 0.46f, size.height * 0.86f)
                            cubicTo(
                                size.width * 0.32f, size.height * 0.86f,
                                size.width * 0.28f, size.height * 0.72f,
                                size.width * 0.34f, size.height * 0.45f
                            )
                            lineTo(size.width * 0.18f, size.height * 0.45f)
                            close()
                        }
                        drawPath(path, Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Toolverse",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF141B18)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "One App. 100+ Powerful Tools.",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF222E29)
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Work Smarter. Do More.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF52605A)
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Toolkit Floating Illustration (matching Screen 1 mockup folder + format tiles)
            ToolkitHeroIllustration()

            Spacer(modifier = Modifier.height(24.dp))

            // Authentication Action Section
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (errorMessage != null) {
                    Surface(
                        color = Color(0xFFFEE2E2),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = errorMessage!!,
                            color = Color(0xFF991B1B),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(12.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                if (wasCancelled) {
                    Surface(
                        color = Color(0xFFFEF3C7),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = "Sign-in was dismissed. Tap 'Continue with Google' below to select your account.",
                            color = Color(0xFF92400E),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(12.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Primary Pill Button: Continue with Google (Mockup Screen 1)
                Button(
                    onClick = {
                        isLoading = true
                        errorMessage = null
                        wasCancelled = false
                        onGoogleSignInClicked(
                            context = context,
                            credentialManager = credentialManager,
                            onAuthSuccess = {
                                isLoading = false
                                onAuthSuccess()
                            },
                            onAuthError = { msg ->
                                isLoading = false
                                errorMessage = msg
                            },
                            scope = coroutineScope,
                            onAuthCancelled = {
                                isLoading = false
                                wasCancelled = true
                            }
                        )
                    },
                    enabled = !isLoading,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF141B18)
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(1.dp, Color(0xFFE2DDD2), RoundedCornerShape(50))
                        .testTag("google_sign_in_button")
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(22.dp),
                            color = Color(0xFF0F5338),
                            strokeWidth = 2.5.dp
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Signing in…", fontWeight = FontWeight.SemiBold)
                    } else {
                        GoogleLogoBadge()
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Continue with Google",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF141B18)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Secondary Pill Button matching Screen 1 visual layout
                Button(
                    onClick = { showEmailProviderInfoDialog = true },
                    enabled = !isLoading,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0A3926),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("continue_email_info_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = "Google Identity Provider",
                        modifier = Modifier.size(20.dp),
                        tint = Color(0xFF6EE7B7)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Continue with Google Workspace / Email",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                val legalAnnotated = buildAnnotatedString {
                    append("By continuing, you agree to our\n")
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline, fontWeight = FontWeight.SemiBold)) {
                        append("Terms of Service")
                    }
                    append(" & ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline, fontWeight = FontWeight.SemiBold)) {
                        append("Privacy Policy")
                    }
                    append(".")
                }

                Text(
                    text = legalAnnotated,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF42524B),
                        lineHeight = 18.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { showLegalDialog = true }
                        .padding(8.dp)
                        .testTag("terms_privacy_link")
                )
            }
        }
    }

    if (showEmailProviderInfoDialog) {
        AlertDialog(
            onDismissRequest = { showEmailProviderInfoDialog = false },
            title = { Text("Unified Google Account Sign-In", fontWeight = FontWeight.Bold) },
            text = {
                Text(
                    "Toolverse uses Google Credential Manager for zero-password security. Tap 'Sign in with Google' to authenticate with your Gmail or Google Workspace email address.\n\n(If you wish to enable manual Email/Password authentication in addition to Google Sign-In, you can enable the Email/Password provider in the Firebase Console.)"
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showEmailProviderInfoDialog = false
                        isLoading = true
                        onGoogleSignInClicked(
                            context = context,
                            credentialManager = credentialManager,
                            onAuthSuccess = {
                                isLoading = false
                                onAuthSuccess()
                            },
                            onAuthError = { msg ->
                                isLoading = false
                                errorMessage = msg
                            },
                            scope = coroutineScope,
                            onAuthCancelled = {
                                isLoading = false
                                wasCancelled = true
                            }
                        )
                    }
                ) {
                    Text("Sign in with Google")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEmailProviderInfoDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    if (showLegalDialog) {
        AlertDialog(
            onDismissRequest = { showLegalDialog = false },
            title = { Text("Terms of Service & Privacy Policy", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        "• 100% Free & Uncluttered: All 175+ utilities are completely free with no paywalls, subscriptions, or locked exports.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        "• On-Device Privacy First: PDF conversions, image processing, CV generation, and file encryption execute locally on your device.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        "• Cloud Sync & Control: Only your preferences, favorite tool IDs, task metadata, and saved CV drafts sync to your private Cloud Firestore space. You can export or permanently delete your account data anytime from Profile.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showLegalDialog = false }) {
                    Text("Got It")
                }
            }
        )
    }
}

@Composable
private fun GoogleLogoBadge() {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFFF8FAFC)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "G",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF4285F4)
            )
        )
    }
}

@Composable
private fun ToolkitHeroIllustration() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp),
        contentAlignment = Alignment.Center
    ) {
        // Floating PDF Card (Left)
        Surface(
            color = Color(0xFFEF4444),
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 8.dp,
            modifier = Modifier
                .offset(x = (-74).dp, y = (-18).dp)
                .rotate(-14f)
                .size(64.dp, 76.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PictureAsPdf,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
                Text("PDF", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            }
        }

        // Floating Image Card (Top Center-Left)
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 8.dp,
            modifier = Modifier
                .offset(x = (-14).dp, y = (-48).dp)
                .rotate(-4f)
                .size(66.dp, 66.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF3B82F6)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        // Floating Word Card (Top Right)
        Surface(
            color = Color(0xFF60A5FA),
            shape = RoundedCornerShape(14.dp),
            shadowElevation = 8.dp,
            modifier = Modifier
                .offset(x = 56.dp, y = (-32).dp)
                .rotate(12f)
                .size(56.dp, 64.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("W", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
            }
        }

        // Floating Spreadsheet X Card (Far Right)
        Surface(
            color = Color(0xFF16A34A),
            shape = RoundedCornerShape(14.dp),
            shadowElevation = 8.dp,
            modifier = Modifier
                .offset(x = 84.dp, y = 14.dp)
                .rotate(18f)
                .size(54.dp, 62.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("X", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
            }
        }

        // Central Glassmorphic Document Folder
        Surface(
            color = Color(0xFFF8FAFC),
            shape = RoundedCornerShape(22.dp),
            shadowElevation = 12.dp,
            modifier = Modifier
                .offset(y = 26.dp)
                .size(156.dp, 108.dp)
                .border(1.5.dp, Color.White, RoundedCornerShape(22.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFE2ECE7))
                        )
                    )
                    .padding(16.dp)
            ) {
                Surface(
                    color = Color(0xFF16A34A),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(72.dp, 22.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("100+ TOOLS", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Icon(
                    imageVector = Icons.Default.Folder,
                    contentDescription = null,
                    tint = Color(0xFF0F5338).copy(alpha = 0.16f),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(58.dp)
                )

                // Wrench/Tool Purple Badge (Bottom Right of folder)
                Surface(
                    color = Color(0xFF6366F1),
                    shape = RoundedCornerShape(12.dp),
                    shadowElevation = 6.dp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
