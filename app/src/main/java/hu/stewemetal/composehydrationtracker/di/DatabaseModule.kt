package hu.stewemetal.composehydrationtracker.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.stewemetal.composehydrationtracker.data.HydrationEntryDao
import hu.stewemetal.composehydrationtracker.data.HydrationTrackerDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun roomDatabase(@ApplicationContext context: Context): HydrationTrackerDatabase =
        Room.databaseBuilder(
            context,
            HydrationTrackerDatabase::class.java,
            "hydration.db"
        ).build()

    @Provides
    @Singleton
    fun provideHydrationEntryDao(database: HydrationTrackerDatabase): HydrationEntryDao =
        database.hydrationEntryDao()
}