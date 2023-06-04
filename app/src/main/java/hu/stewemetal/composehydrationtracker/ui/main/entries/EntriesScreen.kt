package hu.stewemetal.composehydrationtracker.ui.main.entries

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hu.stewemetal.composehydrationtracker.R.string
import hu.stewemetal.composehydrationtracker.data.RoomInitializer
import hu.stewemetal.composehydrationtracker.data.model.toDomainModel
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import hu.stewemetal.composehydrationtracker.ui.main.entries.EntriesState.Content
import hu.stewemetal.composehydrationtracker.ui.main.entries.EntriesState.Loading
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun EntriesScreen(
    viewModel: EntriesViewModel = koinViewModel(),
) {

    when (val uiState = viewModel.uiState) {
        Loading -> EntriesLoading()
        is Content -> EntryList(entries = uiState.entries)
    }
}

@Composable
fun EntryList(
    entries: List<HydrationEntry> = emptyList(),
) {
    if (entries.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.testTag("entries_list")
        ) {
            items(entries) { item ->
                HydrationItem(item = item)
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(stringResource(string.empty_entries_list))
        }
    }
}

@Composable
fun EntriesLoading() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator()
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
            entries = RoomInitializer.getSeedEntries()
                .map { it.toDomainModel() }.reversed().take(8)
        )
    }
}

@Preview(
    name = "Empty list, Light mode",
    showBackground = true,
)
@Preview(
    name = "Empty list, Dark mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun EntryListEmptyPreview() {
    HydrationTrackerTheme {
        EntryList(
            entries = emptyList()
        )
    }
}

