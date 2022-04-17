package hu.stewemetal.composehydrationtracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HydrationTrackerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RoomInitializer(this).init()
    }
}