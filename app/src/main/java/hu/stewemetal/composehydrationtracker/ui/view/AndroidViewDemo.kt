package hu.stewemetal.composehydrationtracker.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme

@Composable
fun AndroidViewDemo(
    onButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("AndroidView Demo Title") }) },
        content = { padding ->
            AndroidView(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 8.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                factory = { context ->
//                    MaterialButton(context).apply {
//                        isAllCaps = false
//                        text = "Demo Button"
//                    }
                    CustomButtonView(context).apply {
                        setText("Demo Button")
                        setOnClickListener { onButtonClick() }
                    }
                },
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AndroidViewDemoPreview() {
    HydrationTrackerTheme {
        AndroidViewDemo { }
    }
}
