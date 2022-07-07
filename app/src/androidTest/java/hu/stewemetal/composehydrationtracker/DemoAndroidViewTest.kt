package hu.stewemetal.composehydrationtracker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import hu.stewemetal.composehydrationtracker.ui.view.AndroidViewDemo
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DemoAndroidViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun composable_withAndroidView() {
        composeTestRule.apply {
            var buttonClicked = false
            setContent {
                HydrationTrackerTheme {
                    AndroidViewDemo { buttonClicked = true }
                }
            }

            onNodeWithText("AndroidView Demo Title").assertIsDisplayed()

            Espresso.onView(withText("Demo Button")).check(matches(isDisplayed()))
            // This doesn't work as of Compose 1.2.0-rc03
            // https://issuetracker.google.com/issues/215231631
            Espresso.onView(withText("Demo Button")).perform(click())

            // This works for now (but it might make the test flaky)
            Espresso.onView(withText("Demo Button")).check { view, _ -> view.performClick() }

            assertEquals(true, buttonClicked)

            onRoot().printToLog("AndroidView test")
        }
    }
}
