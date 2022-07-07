package hu.stewemetal.composehydrationtracker.ui.view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(100.dp)
            .height(50.dp)
    ) {
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomButtonPreview() {
    HydrationTrackerTheme() {
        CustomButton(
            text = "Custom Button"
        )
    }
}