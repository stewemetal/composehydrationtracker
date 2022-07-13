package hu.stewemetal.dailyconsumption.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HydrationProgress() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val width = size.width
        val height = size.height
        val centerX = width / 2f
        val centerY = height / 2f

        val arcOffset = width / 20f

        drawArc(
            color = Color.Gray,
            startAngle = arcOffset,
            sweepAngle = 360 - arcOffset,
            useCenter = false,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HydrationProgressPreview() {
    HydrationProgress()
}