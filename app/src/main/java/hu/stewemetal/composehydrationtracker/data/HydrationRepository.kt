package hu.stewemetal.composehydrationtracker.data

import hu.stewemetal.composehydrationtracker.data.model.RoomConsumptionPerDay
import hu.stewemetal.composehydrationtracker.data.model.RoomHydrationEntry
import hu.stewemetal.composehydrationtracker.data.model.toDomainModel
import hu.stewemetal.composehydrationtracker.domain.model.ConsumptionPerDay
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class HydrationRepository @Inject constructor(
    private val dataSource: HydrationEntriesDataSource,
) {

    suspend fun entries(): Flow<List<HydrationEntry>> =
        dataSource.entries()
            .map { entries ->
                entries.map(RoomHydrationEntry::toDomainModel)
            }

    suspend fun stats(): Flow<List<ConsumptionPerDay>> =
        dataSource.stats()
            .map { entries ->
                entries.map(RoomConsumptionPerDay::toDomainModel)
                    .filter { it.dateTime.isAfter(LocalDate.now().minusDays(7)) }
                    .sortedBy { it.dateTime }
            }

    fun addEntry(milliliters: Int) {
        dataSource.addEntry(
            RoomHydrationEntry(
                id = null,
                milliliters = milliliters,
                dateTime = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
            )
        )
    }
}