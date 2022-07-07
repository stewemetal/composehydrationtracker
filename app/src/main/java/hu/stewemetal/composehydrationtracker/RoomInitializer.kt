package hu.stewemetal.composehydrationtracker

import android.app.Application
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import hu.stewemetal.composehydrationtracker.data.HydrationEntryDao
import hu.stewemetal.composehydrationtracker.data.model.RoomHydrationEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

class RoomInitializer {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface DatabaseInitializerEntryPoint {
        fun HydrationEntryDao(): HydrationEntryDao
    }

    fun init(application: Application) {
        EntryPointAccessors.fromApplication(
            application,
            DatabaseInitializerEntryPoint::class.java,
        ).HydrationEntryDao().also { dao ->
            runBlocking(Dispatchers.IO) {
                dao.insertAll(
                    getSeedEntries()
                )
            }
        }
    }

    private fun daysBeforeTheTalk(days: Int): LocalDate =
        LocalDate.of(2022, 7, 7).minusDays(days.toLong())

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
            LocalDate.of(2022, 7, 7).minusDays(days.toLong())
    }
}
