package hu.stewemetal.composehydrationtracker.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.stewemetal.composehydrationtracker.data.HydrationEntriesRepository
import hu.stewemetal.composehydrationtracker.data.HydrationEntriesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHydrationEntriesRepository(
        impl: HydrationEntriesRepositoryImpl,
    ): HydrationEntriesRepository

}