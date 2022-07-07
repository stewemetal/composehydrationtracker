package hu.stewemetal.composehydrationtracker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.stewemetal.composehydrationtracker.ui.ComposeViewDemoActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DemoComposeViewTest {

    @get:Rule
    val androidComposeTestRule =
        createAndroidComposeRule<ComposeViewDemoActivity>()

    @Test
    fun hybrid_ui_customComposeButton_isVisible() {
        androidComposeTestRule.apply {
            Espresso.onView(withText("ComposeView Demo Title"))
                .check(matches(isDisplayed()))
            onNodeWithText("Demo Button").assertIsDisplayed()
        }
    }
}