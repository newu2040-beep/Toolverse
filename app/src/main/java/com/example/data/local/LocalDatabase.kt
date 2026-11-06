package com.example.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "recent_tools")
data class RecentToolEntity(
    @PrimaryKey val toolId: String,
    val toolName: String,
    val category: String,
    val lastOpenedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "local_drafts")
data class LocalDraftEntity(
    @PrimaryKey val draftId: String,
    val userId: String,
    val draftType: String,
    val title: String,
    val templateId: String,
    val serializedFields: String,
    val updatedAt: Long = System.currentTimeMillis()
)

@Dao
interface ToolverseLocalDao {
    @Query("SELECT * FROM recent_tools ORDER BY lastOpenedAt DESC LIMIT 12")
    fun observeRecentTools(): Flow<List<RecentToolEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRecentTool(item: RecentToolEntity)

    @Query("DELETE FROM recent_tools")
    suspend fun clearRecentTools()

    @Query("SELECT * FROM local_drafts WHERE userId = :userId ORDER BY updatedAt DESC")
    fun observeLocalDrafts(userId: String): Flow<List<LocalDraftEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLocalDraft(draft: LocalDraftEntity)

    @Query("DELETE FROM local_drafts WHERE draftId = :draftId")
    suspend fun deleteLocalDraft(draftId: String)

    @Query("DELETE FROM local_drafts WHERE userId = :userId")
    suspend fun clearUserDrafts(userId: String)
}

@Database(
    entities = [RecentToolEntity::class, LocalDraftEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ToolverseDatabase : RoomDatabase() {
    abstract fun localDao(): ToolverseLocalDao

    companion object {
        @Volatile
        private var INSTANCE: ToolverseDatabase? = null

        fun getInstance(context: Context): ToolverseDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ToolverseDatabase::class.java,
                    "toolverse_local.db"
                ).fallbackToDestructiveMigration(true).build().also { INSTANCE = it }
            }
        }
    }
}
