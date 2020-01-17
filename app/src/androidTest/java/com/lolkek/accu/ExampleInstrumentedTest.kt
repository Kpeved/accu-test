package com.lolkek.accu

import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.hamcrest.core.IsNull
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Rule
    var wireMockRule = WireMockRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.lolkek.accu", appContext.packageName)

        runApp(InstrumentationRegistry.getInstrumentation().context, emptyList(), "com.lolkek.accu")
    }

    fun runApp(
        context: Context,
        extras: List<Pair<String, String>>,
        packageName: String,
        timeout: Long = 5000
    ) {
        val launcherPackage = device.launcherPackageName
        Assert.assertThat(launcherPackage, IsNull.notNullValue())
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), timeout)
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        extras.forEach { intent?.putExtra(it.first, it.second) }
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), timeout)
    }
}
