package hu.stewemetal.composehydrationtracker.data

import hu.stewemetal.composehydrationtracker.data.model.RoomHydrationEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.core.annotation.Factory
import java.time.LocalDate

@Factory
class RoomInitializer(
    private val hydrationEntryDao: HydrationEntryDao,
) {

    fun init() {
        hydrationEntryDao.also { dao ->
            runBlocking(Dispatchers.IO) {
                dao.insertAll(
                    getSeedEntries()
                )
            }
        }
    }

    companion object {

        fun getSeedEntries() = listOf(
            RoomHydrationEntry(1, 100, daysBeforeTheTalk(4)),
            RoomHydrationEntry(2, 250, daysBeforeTheTalk(4)),
            RoomHydrationEntry(3, 500, daysBeforeTheTalk(3)),
            RoomHydrationEntry(4, 100, daysBeforeTheTalk(2)),
            RoomHydrationEntry(5, 500, daysBeforeTheTalk(2)),
            RoomHydrationEntry(6, 100, daysBeforeTheTalk(1)),
            RoomHydrationEntry(7, 100, daysBeforeTheTalk(1)),
            RoomHydrationEntry(8, 100, daysBeforeTheTalk(1)),
            RoomHydrationEntry(9, 500, daysBeforeTheTalk(0)),
            RoomHydrationEntry(10, 100, daysBeforeTheTalk(0)),
        )

        private fun daysBeforeTheTalk(days: Int): LocalDate =
            LocalDate.of(2023, 6, 8).minusDays(days.toLong())
    }
}
