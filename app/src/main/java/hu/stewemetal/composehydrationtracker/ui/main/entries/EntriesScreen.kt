package hu.stewemetal.composehydrationtracker.ui.main.entries

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import java.time.LocalDate

@Composable
fun EntriesScreen() {

    val viewModel = hiltViewModel<EntriesViewModel>()

    val uiState = viewModel.uiState
    val entries = uiState.entries

    if (entries == null) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text("No entries")
        }
    } else {
        EntryList(entries = entries)
    }
}

@Composable
fun EntryList(entries: List<HydrationEntry>) {
    LazyColumn(
        modifier = Modifier.testTag("entries_list")
    ) {
        items(entries) { item ->
            HydrationItem(item = item)
        }
    }
}

@Preview(
    name = "List, Light mode",
    showBackground = true,
)
@Preview(
    name = "List, Dark mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun EntryListPreview() {
    HydrationTrackerTheme {
        EntryList(
            entries = List(10) {
                HydrationEntry(
                    id = null,
                    milliliters = 100+it*10,
                    dateTime = LocalDate.now(),
                )
            }
        )
    }
}

