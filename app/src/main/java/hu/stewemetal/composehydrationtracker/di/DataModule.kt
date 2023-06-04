package hu.stewemetal.composehydrationtracker.di

import android.content.Context
import androidx.room.Room
import hu.stewemetal.composehydrationtracker.data.HydrationEntryDao
import hu.stewemetal.composehydrationtracker.data.HydrationTrackerDatabase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
@ComponentScan("hu.stewemetal.composehydrationtracker.data")
class DataModule {

    @Singleton
    fun provideRoomDatabase(context: Context): HydrationTrackerDatabase =
        Room.databaseBuilder(
            context,
            HydrationTrackerDatabase::class.java,
            "hydration.db"
        ).build()

    @Singleton
    fun provideHydrationEntryDao(database: HydrationTrackerDatabase): HydrationEntryDao =
        database.hydrationEntryDao()
}
