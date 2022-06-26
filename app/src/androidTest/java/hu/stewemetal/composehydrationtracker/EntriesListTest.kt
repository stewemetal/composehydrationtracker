package hu.stewemetal.composehydrationtracker

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollToIndex
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
    fun simpleComposable() {
        composeTestRule.apply {
            setContent {
                HydrationTrackerTheme {
                    Row(
                        modifier = Modifier
                            .semantics(mergeDescendants = false) {
                                contentDescription = "List item"
                            },
                    ) {
                        Text("100 ml")
                        Text("2022-05-03")
                        Button(
                            onClick = {},
                            modifier = Modifier
//                                .clearAndSetSemantics {
//                                    text = AnnotatedString("Careful with this :)")
//                                },
                                .semantics(mergeDescendants = false) {
//                                    text = AnnotatedString("Careful with this :)")
                                },
                        ) {
                            Text("100 ml")
                        }
                    }
                }
            }

            onRoot().printToLog("RowAndTexts")

//            Thread.sleep(1000000)
        }
    }

    @Test
    fun entriesExist_entriesDisplayed() {

        composeTestRule.apply {
            setContent {
                HydrationTrackerTheme {
                    EntryList(
                        entries = entries
                    )
                }
            }
            // ...
            onRoot(useUnmergedTree = false).printToLog("EntriesListTest")

            onNodeWithTag("entries_list")
                .onChildren()
                .assertCountEquals(entries.size)

            onNodeWithText(
                "100 ml",
                useUnmergedTree = true,
            ).assertIsDisplayed()
            onNodeWithText("200 ml").assertIsDisplayed()
            onNodeWithText("300 ml").assertIsDisplayed()
        }
    }

    @Test
    fun entriesExist_overflowsTheScreen_entryListIsScrollable() {

        composeTestRule.apply {
            setContent {
                HydrationTrackerTheme {
                    EntryList(
                        entries = manyEntries
                    )
                }
            }

            onRoot(useUnmergedTree = false).printToLog("EntriesListTest")

            val listNode = onNodeWithTag("entries_list")

            val lastItem = onNodeWithText("${manyEntries.last().milliliters} ml")

            lastItem.assertDoesNotExist() // assertIsNotDisplayed won't work, as the node itself won't exist at this point

            listNode.performScrollToIndex(manyEntries.lastIndex)
            // Alternative approach:
            //  listNode.performScrollToNode(hasText("${manyEntries.last().milliliters} ml"))

            lastItem.assertIsDisplayed()
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

    private val manyEntries = List(40) { index ->
        HydrationEntry(
            index.toLong(),
            index * 10,
            LocalDate.now(),
        )
    }
}