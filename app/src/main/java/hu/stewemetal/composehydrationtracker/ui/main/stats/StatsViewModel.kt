package hu.stewemetal.composehydrationtracker.ui.main.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.stewemetal.composehydrationtracker.data.HydrationRepository
import hu.stewemetal.composehydrationtracker.domain.model.ConsumptionPerDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class StatsViewModel(
    private val dataSource: HydrationRepository,
) : ViewModel() {

    var uiState by mutableStateOf(StatsState())
        private set

    init {
        viewModelScope.launch {
            dataSource.stats()
                .flowOn(Dispatchers.IO)
                .collect {
                    uiState = uiState.copy(stats = it)
                }
        }
    }
}

data class StatsState(
    val stats: List<ConsumptionPerDay>? = null,
)
