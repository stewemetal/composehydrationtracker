package hu.stewemetal.composehydrationtracker

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.stewemetal.composehydrationtracker.ui.main.entries.EntryList
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntriesListAndroidTest {

    @get:Rule
    val androidComposeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun noEntries_emptyScreenDisplayed() {
        androidComposeTestRule.apply {
            setContent {
                HydrationTrackerTheme {
                    EntryList()
                }
            }

            onNodeWithText(activity.getString(R.string.empty_entries_list))
                .assertIsDisplayed()
        }
    }
}