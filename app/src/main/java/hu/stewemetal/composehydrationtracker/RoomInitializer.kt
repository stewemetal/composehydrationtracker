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
import java.time.format.DateTimeFormatter

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
                listOf(
                    RoomHydrationEntry(1, 100, daysBeforeToday(4)),
                    RoomHydrationEntry(2, 250, daysBeforeToday(4)),
                    RoomHydrationEntry(3, 500, daysBeforeToday(3)),
                    RoomHydrationEntry(4, 100, daysBeforeToday(2)),
                    RoomHydrationEntry(5, 500, daysBeforeToday(2)),
                    RoomHydrationEntry(6, 100, daysBeforeToday(1)),
                    RoomHydrationEntry(7, 100, daysBeforeToday(1)),
                    RoomHydrationEntry(8, 100, daysBeforeToday(1)),
                    RoomHydrationEntry(9, 500, daysBeforeToday(0)),
                    RoomHydrationEntry(10, 500, daysBeforeToday(0)),
                ).forEach {
                    dao.insert(it)
                }
            }
        }
    }

    private fun daysBeforeToday(days: Int): String =
        LocalDate.now().minusDays(days.toLong()).format(DateTimeFormatter.ISO_LOCAL_DATE)
}
