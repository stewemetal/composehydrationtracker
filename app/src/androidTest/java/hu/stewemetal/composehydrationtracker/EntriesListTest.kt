package hu.stewemetal.composehydrationtracker

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import hu.stewemetal.composehydrationtracker.ui.main.entries.EntryList
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class EntriesListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun entriesAreDisplayedCorrectly() {

        composeTestRule.apply {
            setContent {
                HydrationTrackerTheme {
                    EntryList(
                        entries = entries
                    )
                }
            }


            onRoot().printToLog("EntriesListTest")

            onNodeWithTag("entries_list")
                .onChildren()
                .assertCountEquals(3)

            onNodeWithText("100 ml").assertIsDisplayed()
            onNodeWithText("200 ml").assertIsDisplayed()
            onNodeWithText("300 ml").assertIsDisplayed()
        }
    }

    private val entries = listOf(
        HydrationEntry(
            1,
            100,
            LocalDate.now(),
        ),
        HydrationEntry(
            2,
            200,
            LocalDate.now(),
        ),
        HydrationEntry(
            3,
            300,
            LocalDate.now(),
        ),
    )
}