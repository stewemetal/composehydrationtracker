package hu.stewemetal.composehydrationtracker.ui.view

import android.content.res.Configuration
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HydrationProgress(
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
    modifier: Modifier = Modifier,
    numberOfIndicators: Int = 20,
    isAnimated: Boolean = false,
) {
    var rotation by remember { mutableStateOf(0f) }

    val rotationAnimation = remember { Animatable(rotation) }

    if (isAnimated) {
        LaunchedEffect(key1 = isAnimated) {
            rotationAnimation.animateTo(
                targetValue = rotation + 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                rotation = value
            }
        }
    } else {
        rotation = 0f
    }

    Canvas(modifier = modifier) {
        val strokeWidth = 10.dp.toPx()

        val arcOffset = 10f

        drawProgress(color = Color.Gray, arcOffset = arcOffset, strokeWidth = strokeWidth)
        drawProgress(color = Color.Cyan, progress = progress, arcOffset = arcOffset, strokeWidth = strokeWidth)

        rotate(rotation) {
            (0 until numberOfIndicators).forEach { index ->
                rotate(360f / numberOfIndicators * index + arcOffset) {
                    drawLine(
                        color = Color.Green,
                        start = center.copy(y = center.y / 4f),
                        end = Offset(size.width / 2f, strokeWidth + 10),
                        strokeWidth = strokeWidth / 2f,
                    )
                }
            }
        }
    }
}

fun DrawScope.drawProgress(color: Color, progress: Float = 1f, arcOffset: Float, strokeWidth: Float) {
    rotate(-90f) {
        drawArc(
            color = color,
            startAngle = arcOffset,
            sweepAngle = (progress * 360f) - if (progress >= 1f) (arcOffset * 2f) else arcOffset,
            topLeft = Offset(strokeWidth / 2f, strokeWidth / 2f),
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round),
            size = Size(size.width - strokeWidth, size.height - strokeWidth)
        )
    }
}

@Preview(
    name = "Hydration Progress, Light mode",
    showBackground = true,
)
@Preview(
    name = "Hydration Progress, Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HydrationProgressPreview() {
    HydrationProgress(
        modifier = Modifier.size(200.dp),
        progress = 0.75f,
        numberOfIndicators = 20,
        isAnimated = true,
    )
}