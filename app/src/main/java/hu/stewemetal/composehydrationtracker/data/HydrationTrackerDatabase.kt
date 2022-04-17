package hu.stewemetal.composehydrationtracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.stewemetal.composehydrationtracker.data.model.RoomHydrationEntry

@Database(
    version = 1,
    entities = [
        RoomHydrationEntry::class,
    ]
)
abstract class HydrationTrackerDatabase : RoomDatabase(){
    abstract fun hydrationEntryDao(): HydrationEntryDao
}