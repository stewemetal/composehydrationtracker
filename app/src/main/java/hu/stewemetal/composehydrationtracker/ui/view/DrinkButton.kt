package hu.stewemetal.composehydrationtracker.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import hu.stewemetal.composehydrationtracker.R.raw
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme

@Composable
fun DrinkButton(
    intakeValue: Int,
    isLoading: Boolean,
    onAnimationEnded: () -> Unit = {},
    onClick: (Int) -> Unit,
) {
    Button(
        onClick = { onClick(intakeValue) },
        modifier = Modifier
            .width(100.dp)
            .height(50.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            if (isLoading) {
                val composition by rememberLottieComposition(RawRes(raw.water_fills))
                val animationState = animateLottieCompositionAsState(
                    composition,
                )

                LottieAnimation(
                    composition = composition,
                    progress = { animationState.progress },
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                )

                Log.d(">>>", "${animationState.isAtEnd}")

                if (animationState.isAtEnd) {
                    SideEffect { onAnimationEnded() }
                }
            }
            Text("$intakeValue ml")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DrinkButtonPreview() {
    HydrationTrackerTheme() {
        var isLoading by remember { mutableStateOf(false) }
        DrinkButton(
            intakeValue = 100,
            onClick = { intake -> isLoading = true },
            isLoading = isLoading,
            onAnimationEnded = { isLoading = false }
        )
    }
}