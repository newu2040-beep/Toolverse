package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.domain.catalog.ToolCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context and verify catalog count`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Toolverse", appName)
        assertTrue("Expected 200+ tools in ToolCatalog, found ${ToolCatalog.allTools.size}", ToolCatalog.allTools.size >= 200)
        assertEquals(
            "All tool IDs in ToolCatalog must be unique",
            ToolCatalog.allTools.size,
            ToolCatalog.allTools.map { it.id }.distinct().size
        )
    }
}
