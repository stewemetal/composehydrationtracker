package hu.stewemetal.composehydrationtracker.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.stewemetal.composehydrationtracker.domain.HydrationDataSource
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import hu.stewemetal.composehydrationtracker.ui.add.AddDrinkState.AcceptingInput
import hu.stewemetal.composehydrationtracker.ui.add.AddDrinkState.DrinkSaved
import hu.stewemetal.composehydrationtracker.ui.main.entries.EntriesState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddDrinkViewModel @Inject constructor(
    private val dataSource: HydrationDataSource,
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
