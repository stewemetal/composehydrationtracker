package hu.stewemetal.composehydrationtracker.data

import hu.stewemetal.composehydrationtracker.data.model.RoomConsumptionPerDay
import hu.stewemetal.composehydrationtracker.data.model.RoomHydrationEntry
import kotlinx.coroutines.flow.Flow

interface HydrationEntriesDataSource {

    suspend fun entries(): Flow<List<RoomHydrationEntry>>

    suspend fun stats(): Flow<List<RoomConsumptionPerDay>>

    fun addEntry(entry: RoomHydrationEntry)
}
