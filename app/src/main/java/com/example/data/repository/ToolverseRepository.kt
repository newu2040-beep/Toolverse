package com.example.data.repository

import android.content.Context
import com.example.R
import com.example.data.model.DocumentDraft
import com.example.data.model.FavoriteTool
import com.example.data.model.OperationType
import com.example.data.model.TaskHistoryItem
import com.example.data.model.UserProfile
import com.example.data.model.handleFirestoreError
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.snapshots
import java.util.UUID
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import org.json.JSONArray
import org.json.JSONObject

class ToolverseRepository(private val db: FirebaseFirestore) {

    constructor(context: Context) : this(
        FirebaseFirestore.getInstance(
            context.applicationContext.getString(R.string.firestore_database_id)
        )
    )

    private val auth = Firebase.auth

    private fun requireUserId(): String {
        return auth.currentUser?.uid
            ?: throw IllegalStateException("User must be signed in with Google before accessing Firestore.")
    }

    fun observeUserProfile(userId: String = requireUserId()): Flow<UserProfile?> = flow {
        val path = "users/$userId"
        emitAll(
            db.collection("users").document(userId)
                .snapshots()
                .map { snapshot ->
                    if (snapshot.exists()) {
                        snapshot.toObject(UserProfile::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                    } else {
                        null
                    }
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null) {
                        handleFirestoreError(exceptionToLog, OperationType.GET, path)
                    }
                    throw error
                }
        )
    }

    suspend fun ensureUserProfile(
        displayName: String,
        email: String,
        photoURL: String? = null
    ): Result<UserProfile> = runCatching {
        val uid = requireUserId()
        val docRef = db.collection("users").document(uid)
        try {
            val snapshot = docRef.get().await()
            if (snapshot.exists()) {
                val existing = snapshot.toObject(
                    UserProfile::class.java,
                    DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                ) ?: UserProfile(id = uid, userId = uid, displayName = displayName, email = email)
                return@runCatching existing
            }
            val safeName = displayName.trim().ifBlank { "Toolverse User" }.take(120)
            val safeEmail = email.trim().ifBlank { "user@toolverse.app" }.take(254)
            val payload = mutableMapOf<String, Any>(
                "userId" to uid,
                "displayName" to safeName,
                "email" to safeEmail,
                "themeMode" to "SYSTEM",
                "fontChoice" to "JAKARTA",
                "notificationsEnabled" to true,
                "autoCleanCache" to false,
                "compactMode" to false,
                "schemaVersion" to 1L,
                "createdAt" to FieldValue.serverTimestamp(),
                "updatedAt" to FieldValue.serverTimestamp()
            )
            if (!photoURL.isNullOrBlank()) {
                payload["photoURL"] = photoURL.take(1024)
            }
            docRef.set(payload).await()
            UserProfile(
                id = uid,
                userId = uid,
                displayName = safeName,
                email = safeEmail,
                photoURL = photoURL,
                themeMode = "SYSTEM",
                fontChoice = "JAKARTA",
                notificationsEnabled = true,
                autoCleanCache = false,
                compactMode = false,
                schemaVersion = 1L
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, docRef.path)
            throw e
        }
    }

    suspend fun getUserProfileById(targetUserId: String): Result<UserProfile?> = runCatching {
        val docRef = db.collection("users").document(targetUserId)
        try {
            val snapshot = docRef.get().await()
            snapshot.toObject(UserProfile::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.GET, docRef.path)
            throw e
        }
    }

    suspend fun updatePreferences(
        themeMode: String? = null,
        fontChoice: String? = null,
        notificationsEnabled: Boolean? = null,
        autoCleanCache: Boolean? = null,
        compactMode: Boolean? = null
    ): Result<Unit> = runCatching {
        val uid = requireUserId()
        val docRef = db.collection("users").document(uid)
        val updates = mutableMapOf<String, Any>(
            "updatedAt" to FieldValue.serverTimestamp()
        )
        themeMode?.let { updates["themeMode"] = it }
        fontChoice?.let { updates["fontChoice"] = it }
        notificationsEnabled?.let { updates["notificationsEnabled"] = it }
        autoCleanCache?.let { updates["autoCleanCache"] = it }
        compactMode?.let { updates["compactMode"] = it }
        try {
            docRef.update(updates).await()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, docRef.path)
            throw e
        }
    }

    fun observeFavorites(userId: String = requireUserId()): Flow<List<FavoriteTool>> = flow {
        val path = "users/$userId/favorites"
        emitAll(
            db.collection("users").document(userId).collection("favorites")
                .whereEqualTo("userId", userId)
                .snapshots()
                .map { snapshot ->
                    snapshot.toObjects(FavoriteTool::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                        .sortedByDescending { it.createdAt ?: Timestamp(0, 0) }
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    suspend fun addFavorite(toolId: String, toolName: String, category: String): Result<Unit> = runCatching {
        val uid = requireUserId()
        val docRef = db.collection("users").document(uid).collection("favorites").document(toolId)
        val payload = mapOf(
            "userId" to uid,
            "toolId" to toolId.take(100),
            "toolName" to toolName.take(120),
            "category" to category.take(80),
            "createdAt" to FieldValue.serverTimestamp()
        )
        try {
            docRef.set(payload).await()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, docRef.path)
            throw e
        }
    }

    suspend fun removeFavorite(toolId: String): Result<Unit> = runCatching {
        val uid = requireUserId()
        val docRef = db.collection("users").document(uid).collection("favorites").document(toolId)
        try {
            docRef.delete().await()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, docRef.path)
            throw e
        }
    }

    fun observeHistory(userId: String = requireUserId()): Flow<List<TaskHistoryItem>> = flow {
        val path = "users/$userId/history"
        emitAll(
            db.collection("users").document(userId).collection("history")
                .whereEqualTo("userId", userId)
                .snapshots()
                .map { snapshot ->
                    snapshot.toObjects(TaskHistoryItem::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                        .sortedByDescending { it.createdAt ?: Timestamp(0, 0) }
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    suspend fun recordTaskHistory(
        toolId: String,
        toolName: String,
        category: String,
        status: String,
        inputSummary: String,
        outputName: String,
        outputUri: String? = null,
        sizeBytes: Long = 0L,
        durationMs: Long = 0L,
        errorMessage: String? = null
    ): Result<String> = runCatching {
        val uid = requireUserId()
        val taskId = "task_${UUID.randomUUID().toString().replace("-", "").take(20)}"
        val docRef = db.collection("users").document(uid).collection("history").document(taskId)
        val payload = mutableMapOf<String, Any>(
            "userId" to uid,
            "toolId" to toolId.take(100),
            "toolName" to toolName.take(120),
            "category" to category.take(80),
            "status" to status,
            "inputSummary" to inputSummary.take(500),
            "outputName" to outputName.take(300),
            "sizeBytes" to sizeBytes.coerceAtLeast(0L),
            "durationMs" to durationMs.coerceAtLeast(0L),
            "createdAt" to FieldValue.serverTimestamp(),
            "updatedAt" to FieldValue.serverTimestamp()
        )
        if (!outputUri.isNullOrBlank()) {
            payload["outputUri"] = outputUri.take(1024)
        }
        if (!errorMessage.isNullOrBlank()) {
            payload["errorMessage"] = errorMessage.take(500)
        }
        try {
            docRef.set(payload).await()
            taskId
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, docRef.path)
            throw e
        }
    }

    suspend fun getHistoryItemById(targetUserId: String, taskId: String): Result<TaskHistoryItem?> = runCatching {
        val docRef = db.collection("users").document(targetUserId).collection("history").document(taskId)
        try {
            val snapshot = docRef.get().await()
            snapshot.toObject(TaskHistoryItem::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.GET, docRef.path)
            throw e
        }
    }

    suspend fun deleteHistoryItem(taskId: String): Result<Unit> = runCatching {
        val uid = requireUserId()
        val docRef = db.collection("users").document(uid).collection("history").document(taskId)
        try {
            docRef.delete().await()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, docRef.path)
            throw e
        }
    }

    suspend fun clearAllHistory(): Result<Unit> = runCatching {
        val uid = requireUserId()
        val colRef = db.collection("users").document(uid).collection("history")
        try {
            val docs = colRef.whereEqualTo("userId", uid).get().await()
            for (doc in docs.documents) {
                doc.reference.delete().await()
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, colRef.path)
            throw e
        }
    }

    fun observeDrafts(userId: String = requireUserId()): Flow<List<DocumentDraft>> = flow {
        val path = "users/$userId/drafts"
        emitAll(
            db.collection("users").document(userId).collection("drafts")
                .whereEqualTo("userId", userId)
                .snapshots()
                .map { snapshot ->
                    snapshot.toObjects(DocumentDraft::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                        .sortedByDescending { it.updatedAt ?: it.createdAt ?: Timestamp(0, 0) }
                }
                .catch { error ->
                    val firestoreError = generateSequence(error) { it.cause }
                        .filterIsInstance<FirebaseFirestoreException>()
                        .firstOrNull()
                    if (firestoreError == null && error is CancellationException) throw error
                    val exceptionToLog = firestoreError ?: (error as? Exception)
                    if (exceptionToLog != null) {
                        handleFirestoreError(exceptionToLog, OperationType.LIST, path)
                    }
                    throw error
                }
        )
    }

    suspend fun saveDraft(
        draftId: String? = null,
        draftType: String,
        title: String,
        templateId: String,
        structuredData: Map<String, String>
    ): Result<String> = runCatching {
        val uid = requireUserId()
        val resolvedId = draftId?.takeIf { it.isNotBlank() }
            ?: "draft_${UUID.randomUUID().toString().replace("-", "").take(20)}"
        val docRef = db.collection("users").document(uid).collection("drafts").document(resolvedId)
        val sanitizedTitle = title.trim().ifBlank { "Untitled Document" }.take(160)
        val sanitizedTemplate = templateId.trim().ifBlank { "ats_modern" }.take(80)
        val sanitizedMap = structuredData.entries.take(50).associate { (k, v) -> k.take(60) to v.take(4000) }

        try {
            val existing = docRef.get().await()
            if (existing.exists()) {
                val updates = mapOf(
                    "title" to sanitizedTitle,
                    "templateId" to sanitizedTemplate,
                    "structuredData" to sanitizedMap,
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                docRef.update(updates).await()
            } else {
                val payload = mapOf(
                    "userId" to uid,
                    "draftType" to draftType,
                    "title" to sanitizedTitle,
                    "templateId" to sanitizedTemplate,
                    "structuredData" to sanitizedMap,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                docRef.set(payload).await()
            }
            resolvedId
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, docRef.path)
            throw e
        }
    }

    suspend fun deleteDraft(draftId: String): Result<Unit> = runCatching {
        val uid = requireUserId()
        val docRef = db.collection("users").document(uid).collection("drafts").document(draftId)
        try {
            docRef.delete().await()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, docRef.path)
            throw e
        }
    }

    suspend fun exportUserDataJson(): Result<String> = runCatching {
        val uid = requireUserId()
        val userDoc = db.collection("users").document(uid).get().await()
        val favDocs = db.collection("users").document(uid).collection("favorites")
            .whereEqualTo("userId", uid).get().await()
        val histDocs = db.collection("users").document(uid).collection("history")
            .whereEqualTo("userId", uid).get().await()
        val draftDocs = db.collection("users").document(uid).collection("drafts")
            .whereEqualTo("userId", uid).get().await()

        val root = JSONObject()
        root.put("exportedAtEpochMs", System.currentTimeMillis())
        root.put("userId", uid)
        root.put("profile", JSONObject().apply {
            put("displayName", userDoc.getString("displayName") ?: "")
            put("email", userDoc.getString("email") ?: "")
            put("themeMode", userDoc.getString("themeMode") ?: "SYSTEM")
            put("fontChoice", userDoc.getString("fontChoice") ?: "JAKARTA")
        })
        root.put("favorites", JSONArray().apply {
            favDocs.documents.forEach { doc ->
                put(JSONObject().apply {
                    put("toolId", doc.getString("toolId"))
                    put("toolName", doc.getString("toolName"))
                    put("category", doc.getString("category"))
                })
            }
        })
        root.put("historyCount", histDocs.size())
        root.put("draftsCount", draftDocs.size())
        root.toString(2)
    }

    suspend fun deleteAllUserData(): Result<Unit> = runCatching {
        val uid = requireUserId()
        val userRef = db.collection("users").document(uid)
        try {
            val favs = userRef.collection("favorites").whereEqualTo("userId", uid).get().await()
            favs.documents.forEach { it.reference.delete().await() }
            val hist = userRef.collection("history").whereEqualTo("userId", uid).get().await()
            hist.documents.forEach { it.reference.delete().await() }
            val drafts = userRef.collection("drafts").whereEqualTo("userId", uid).get().await()
            drafts.documents.forEach { it.reference.delete().await() }
            userRef.delete().await()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, userRef.path)
            throw e
        }
    }
}
