package hu.stewemetal.composehydrationtracker

import android.app.Application
import hu.stewemetal.composehydrationtracker.data.RoomInitializer
import hu.stewemetal.composehydrationtracker.di.DataModule
import hu.stewemetal.composehydrationtracker.ui.ViewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class HydrationTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HydrationTrackerApplication)
            modules(
                DataModule().module,
                ViewModelModule().module,
            )
        }

        inject<RoomInitializer>().value.init()
    }
}
