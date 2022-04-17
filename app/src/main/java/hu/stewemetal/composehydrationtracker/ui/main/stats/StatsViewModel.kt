package hu.stewemetal.composehydrationtracker.ui.main.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.stewemetal.composehydrationtracker.domain.HydrationDataSource
import hu.stewemetal.composehydrationtracker.domain.model.ConsumptionPerDay
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val dataSource: HydrationDataSource,
): ViewModel() {

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