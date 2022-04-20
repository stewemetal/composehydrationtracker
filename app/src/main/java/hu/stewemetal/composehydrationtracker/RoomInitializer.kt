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
import javax.inject.Inject

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
                    RoomHydrationEntry(1, 100, "2022-04-13"),
                    RoomHydrationEntry(2, 250, "2022-04-13"),
                    RoomHydrationEntry(3, 500, "2022-04-14"),
                    RoomHydrationEntry(4, 100, "2022-04-15"),
                    RoomHydrationEntry(5, 500, "2022-04-15"),
                    RoomHydrationEntry(6, 100, "2022-04-16"),
                    RoomHydrationEntry(7, 100, "2022-04-16"),
                    RoomHydrationEntry(8, 100, "2022-04-16"),
                    RoomHydrationEntry(9, 500, "2022-04-17"),
                    RoomHydrationEntry(10, 500, "2022-04-17"),
                ).forEach {
                    dao.insert(it)
                }
            }
        }
    }

}
