package hu.stewemetal.composehydrationtracker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.stewemetal.composehydrationtracker.domain.model.ConsumptionPerDay
import hu.stewemetal.composehydrationtracker.ui.main.stats.StatsChart
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class StatsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun composable_withAndroidView() {
        composeTestRule.apply {
            var buttonClicked = false
            setContent {
                HydrationTrackerTheme {
                    StatsChart(stats = entries) { buttonClicked = true }
                }
            }

            Espresso.onView(withText("Demo Button")).check(matches(isDisplayed()))
            // https://issuetracker.google.com/issues/215231631
            // This works (might make the test flaky)...
            Espresso.onView(withText("Demo Button")).check { view, _ -> view.performClick() }
            // ...but this doesn't. :(
            Espresso.onView(withText("Demo Button")).perform(click())

            assertEquals(true, buttonClicked)

            onNodeWithTag("stats_chart").assertIsDisplayed()

            onRoot().printToLog("Chart test")
        }
    }
}

private val entries = listOf(
    ConsumptionPerDay(
        1,
        LocalDate.now(),
    ),
    ConsumptionPerDay(
        2,
        LocalDate.now().minusDays(1),
    ),
    ConsumptionPerDay(
        3,
        LocalDate.now().minusDays(2),
    ),
)
