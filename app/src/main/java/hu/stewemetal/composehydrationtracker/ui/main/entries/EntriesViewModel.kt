package hu.stewemetal.composehydrationtracker.ui.main.entries

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.stewemetal.composehydrationtracker.domain.HydrationDataSource
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntriesViewModel @Inject constructor(
    private val dataSource: HydrationDataSource,
): ViewModel() {

    var uiState by mutableStateOf(EntriesState())
        private set

    init {
        viewModelScope.launch {
            dataSource.entries()
                .flowOn(Dispatchers.IO)
                .collect {
                    uiState = uiState.copy(entries = it)
                }
        }
    }
}

data class EntriesState(
    val entries: List<HydrationEntry>? = null,
)