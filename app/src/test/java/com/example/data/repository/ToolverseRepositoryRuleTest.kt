package com.example.data.repository

import com.example.base.FirestoreEmulatorTestBase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ToolverseRepositoryRuleTest : FirestoreEmulatorTestBase() {

    @Test
    fun ensureUserProfile_and_recordHistory_succeedsForAuthenticatedOwner() = runBlocking {
        val aliceUid = signInTestUser(ALICE_EMAIL)
        val repository = ToolverseRepository(firestore)

        val profileResult = withTimeout(DEFAULT_TIMEOUT_MS) {
            repository.ensureUserProfile("Alice Tester", ALICE_EMAIL, null)
        }
        assertTrue(profileResult.isSuccess)
        assertEquals(aliceUid, profileResult.getOrThrow().userId)

        val taskIdResult = withTimeout(DEFAULT_TIMEOUT_MS) {
            repository.recordTaskHistory(
                toolId = "pdf_image_to_pdf",
                toolName = "Image to PDF",
                category = "PDF",
                status = "COMPLETED",
                inputSummary = "3 images",
                outputName = "Scan_Output.pdf",
                sizeBytes = 128000L,
                durationMs = 320L
            )
        }
        assertTrue(taskIdResult.isSuccess)
        val taskId = taskIdResult.getOrThrow()

        val emittedHistory = withTimeout(FLOW_TIMEOUT_MS) {
            repository.observeHistory(aliceUid).first { list -> list.any { it.id == taskId } }
        }
        assertTrue(emittedHistory.any { it.id == taskId })
    }

    @Test
    fun crossUserAccess_failsWithPermissionDenied() = runBlocking {
        val aliceUid = signInTestUser(ALICE_EMAIL)
        val aliceRepo = ToolverseRepository(firestore)
        val taskId = withTimeout(DEFAULT_TIMEOUT_MS) {
            aliceRepo.recordTaskHistory(
                toolId = "sec_sha256",
                toolName = "SHA-256 Calculator",
                category = "Security",
                status = "COMPLETED",
                inputSummary = "Text payload",
                outputName = "Digest",
                sizeBytes = 64L,
                durationMs = 12L
            ).getOrThrow()
        }

        signInTestUser(BOB_EMAIL)
        val bobRepo = ToolverseRepository(firestore)
        val bobReadResult = withTimeout(DEFAULT_TIMEOUT_MS) {
            bobRepo.getHistoryItemById(aliceUid, taskId)
        }
        assertTrue(bobReadResult.isFailure)
        val firestoreEx = bobReadResult.exceptionOrNull()?.unwrapFirestoreException()
        assertNotNull(firestoreEx)
        assertEquals(FirebaseFirestoreException.Code.PERMISSION_DENIED, firestoreEx?.code)
    }

    @Test
    fun unauthenticatedUser_observeFavorites_failsWithPermissionDenied() = runBlocking {
        val aliceUid = signInTestUser(ALICE_EMAIL)
        auth.signOut()
        val repository = ToolverseRepository(firestore)

        var caughtException: Throwable? = null
        try {
            withTimeout(FLOW_TIMEOUT_MS) {
                repository.observeFavorites(aliceUid).first()
            }
        } catch (e: Throwable) {
            caughtException = e
        }
        assertNotNull("Expected exception for unauthenticated query", caughtException)
        val firestoreException = checkNotNull(caughtException?.unwrapFirestoreException()) {
            "Expected nested FirebaseFirestoreException, got $caughtException"
        }
        assertEquals(FirebaseFirestoreException.Code.PERMISSION_DENIED, firestoreException.code)
    }

    private companion object {
        const val ALICE_EMAIL = "alice@toolverse.test"
        const val BOB_EMAIL = "bob@toolverse.test"
        const val DEFAULT_TIMEOUT_MS = 5000L
        const val FLOW_TIMEOUT_MS = 3000L
    }
}
