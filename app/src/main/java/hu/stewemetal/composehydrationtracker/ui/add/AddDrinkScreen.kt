package hu.stewemetal.composehydrationtracker.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import hu.stewemetal.composehydrationtracker.R
import hu.stewemetal.composehydrationtracker.R.string
import hu.stewemetal.composehydrationtracker.ui.add.AddDrinkState.AcceptingInput
import hu.stewemetal.composehydrationtracker.ui.add.AddDrinkState.DrinkSaved
import hu.stewemetal.composehydrationtracker.ui.view.DrinkButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddDrinkScreen(
    viewModel: AddDrinkViewModel = koinViewModel(),
    onBackClick: () -> Boolean,
) {

    when (viewModel.uiState) {
        AcceptingInput -> {
            AddDrink(
                onBackClick = { onBackClick() },
                addDrink = { value -> viewModel.addDrink(value) }
            )
        }

        DrinkSaved -> {
            LaunchedEffect(key1 = Unit) {
                onBackClick()
            }
        }
    }

}

@Composable
fun AddDrink(
    onBackClick: () -> Unit,
    addDrink: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Filled.ArrowBack, stringResource(string.back_arrow))
                    }
                },

                title = {
                    Text(stringResource(id = string.title_add))
                },
            )
        },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.water_animation))
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(200.dp),
            )

            CustomValueInput { valueToAdd ->
                addDrink(valueToAdd)
            }

            PredefinedValuesInput { valueToAdd ->
                addDrink(valueToAdd)
            }

            var isLoading by remember { mutableStateOf(false) }
            DrinkButton(
                intakeValue = 100,
                onClick = { intake -> isLoading = true },
                isLoading = isLoading,
                onAnimationEnded = { isLoading = false }
            )
        }
    }
}
