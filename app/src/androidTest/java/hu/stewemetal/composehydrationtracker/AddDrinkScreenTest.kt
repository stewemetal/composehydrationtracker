package hu.stewemetal.composehydrationtracker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.stewemetal.composehydrationtracker.ui.add.AddDrink
import hu.stewemetal.composehydrationtracker.ui.add.PredefinedValuesInput
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddDrinkScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun isCustomInputDisplayed() {
        composeTestRule.apply {
            setContent {
                HydrationTrackerTheme {
                    AddDrink(
                        onBackClick = {},
                        addDrink = {},
                    )
                }
            }

            onNodeWithTag("custom_value_input").assertIsDisplayed()
        }
    }

    @Test
    fun add_100ml() {
        composeTestRule.apply {

            var addedDrinkValue = 0

            setContent {
                HydrationTrackerTheme {
                    AddDrink(
                        onBackClick = {},
                        addDrink = { value ->
                            addedDrinkValue = value
                        },
                    )
                }
            }

            onNodeWithText("100 ml").performClick()

            assertEquals(100, addedDrinkValue)
        }
    }

    @Test
    fun add_250ml() {
        composeTestRule.apply {

            var addedDrinkValue = 0

            setContent {
                HydrationTrackerTheme {
                    AddDrink(
                        onBackClick = {},
                        addDrink = { value ->
                            addedDrinkValue = value
                        },
                    )
                }
            }

            onNodeWithText("250 ml").performClick()

            assertEquals(250, addedDrinkValue)
        }
    }

    @Test
    fun add_500ml() {
        composeTestRule.apply {

            var addedDrinkValue = 0

            setContent {
                HydrationTrackerTheme {
                    AddDrink(
                        onBackClick = {},
                        addDrink = { value ->
                            addedDrinkValue = value
                        },
                    )
                }
            }

            onNodeWithText("500 ml").performClick()

            assertEquals(500, addedDrinkValue)
        }
    }

    @Test
    fun addCustomValue() {
        composeTestRule.apply {
            var addedDrinkValue = 0

            setContent {
                HydrationTrackerTheme {
                    AddDrink(
                        onBackClick = {},
                        addDrink = { value ->
                            addedDrinkValue = value
                        },
                    )
                }
            }

            onNodeWithTag("drink_input").performTextInput("120")

            onNodeWithText("Add custom value").performClick()

            assertEquals(120, addedDrinkValue)
        }
    }

    @Test
    fun predefinedValuesInput_addsCorrectValues() {
        composeTestRule.apply {
            var addedDrinkValue = 0

            setContent {
                HydrationTrackerTheme {
                    PredefinedValuesInput(
                        onAddDrink = { value ->
                            addedDrinkValue = value
                        },
                    )
                }
            }

            onNodeWithText("500 ml").performClick()

            assertEquals(500, addedDrinkValue)
        }
    }
}