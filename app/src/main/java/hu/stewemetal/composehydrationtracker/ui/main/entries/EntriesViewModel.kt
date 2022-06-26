package hu.stewemetal.composehydrationtracker.ui.main.entries

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.stewemetal.composehydrationtracker.data.HydrationRepository
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import hu.stewemetal.composehydrationtracker.ui.main.entries.EntriesState.Content
import hu.stewemetal.composehydrationtracker.ui.main.entries.EntriesState.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntriesViewModel @Inject constructor(
    private val dataSource: HydrationRepository,
) : ViewModel() {

    var uiState by mutableStateOf<EntriesState>(Loading)
        private set

    init {
        viewModelScope.launch {
            dataSource.entries()
                .flowOn(Dispatchers.IO)
                .collect {
                    val state = uiState
                    uiState = if (state is Content) {
                        state.copy(entries = it)
                    } else {
                        Content(entries = it)
                    }
                }
        }
    }
}

sealed class EntriesState {
    object Loading : EntriesState()
    data class Content(
        val entries: List<HydrationEntry> = emptyList(),
    ) : EntriesState()
}