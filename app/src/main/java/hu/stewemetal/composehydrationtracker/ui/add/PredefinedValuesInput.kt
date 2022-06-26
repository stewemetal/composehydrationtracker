package hu.stewemetal.composehydrationtracker.ui.add

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme

@Composable
fun PredefinedValuesInput(
    onAddDrink: (Int) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Button(onClick = { onAddDrink(100) }) {
            Text("100 ml")
        }
        Button(onClick = { onAddDrink(250) }) {
            Text("250 ml")
        }
        Button(onClick = { onAddDrink(500) }) {
            Text("500 ml")
        }
    }
}

@Preview(
    name = "Predefined intake, Light mode",
    showBackground = true,
)
@Preview(
    name = "Predefined intake, Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PredefinedValuesInputPreview() {
    HydrationTrackerTheme {
        PredefinedValuesInput {}
    }
}