package hu.stewemetal.composehydrationtracker.data

import hu.stewemetal.composehydrationtracker.data.model.RoomConsumptionPerDay
import hu.stewemetal.composehydrationtracker.data.model.RoomHydrationEntry
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single(binds = [HydrationEntriesDataSource::class])
class HydrationEntriesDataSourceImpl(
    private val hydrationEntryDao: HydrationEntryDao,
) : HydrationEntriesDataSource {

    override suspend fun entries(): Flow<List<RoomHydrationEntry>> =
        hydrationEntryDao.getAll()

    override suspend fun stats(): Flow<List<RoomConsumptionPerDay>> =
        hydrationEntryDao.getConsumptionPerDay()

    override fun addEntry(entry: RoomHydrationEntry) {
        hydrationEntryDao.insert(entry)
    }
}
