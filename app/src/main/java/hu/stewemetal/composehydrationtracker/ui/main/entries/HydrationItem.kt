package hu.stewemetal.composehydrationtracker.ui.main.entries

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme
import java.time.LocalDate

@Composable
fun HydrationItem(item: HydrationEntry) {
    val backgroundColor = MaterialTheme.colors.surface
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .wrapContentHeight(
                align = Alignment.CenterVertically,
            )
            .padding(16.dp)
            .semantics(mergeDescendants = true) {}
            .testTag("hydration_item"),
    ) {
        Text(
            "${item.milliliters} ml",
            color = contentColorFor(backgroundColor = backgroundColor),
            modifier = Modifier.weight(1f),
        )
        Text(
            "${item.date}",
            color = contentColorFor(backgroundColor = backgroundColor),
        )
    }
}

@Preview(
    name = "Item, Light mode",
)
@Preview(
    name = "Item, Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HydrationItemPreview() {
    HydrationTrackerTheme {
        HydrationItem(
            item = HydrationEntry(
                id = null,
                milliliters = 100,
                date = LocalDate.of(2022, 5, 3),
            )
        )
    }
}