package hu.stewemetal.composehydrationtracker.ui.add

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.stewemetal.composehydrationtracker.R
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme

@Composable
fun CustomValueInput(
    onAddClicked: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.testTag("custom_value_input")
    ) {
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            label = { Text(stringResource(R.string.water_intake_hint)) },
            modifier = Modifier.testTag("drink_input")
        )

        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                onAddClicked(text.toInt())
            },
        ) {
            Text(stringResource(R.string.add_custom_value))
        }
    }
}

@Preview(
    name = "Custom intake, Light mode",
)
@Preview(
    name = "Custom intake, Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun CustomValueInputPreview() {
    HydrationTrackerTheme {
        CustomValueInput {}
    }
}