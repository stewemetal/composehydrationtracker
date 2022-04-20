package hu.stewemetal.composehydrationtracker.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.stewemetal.composehydrationtracker.data.HydrationRepository
import hu.stewemetal.composehydrationtracker.ui.add.AddDrinkState.AcceptingInput
import hu.stewemetal.composehydrationtracker.ui.add.AddDrinkState.DrinkSaved
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddDrinkViewModel @Inject constructor(
    private val dataSource: HydrationRepository,
) : ViewModel() {

    var uiState by mutableStateOf<AddDrinkState>(AcceptingInput)
        private set

    fun addDrink(milliliters: Int) {
        viewModelScope.launch {
            withContext(IO){
                dataSource.addEntry(milliliters)
            }
            uiState = DrinkSaved
        }
    }
}

sealed class AddDrinkState {
    object AcceptingInput: AddDrinkState()
    object DrinkSaved: AddDrinkState()
}
