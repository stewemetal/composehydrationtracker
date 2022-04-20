package hu.stewemetal.composehydrationtracker

import android.app.Application
import com.github.mikephil.charting.utils.Utils.init
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HydrationTrackerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RoomInitializer().init(this)
    }
}