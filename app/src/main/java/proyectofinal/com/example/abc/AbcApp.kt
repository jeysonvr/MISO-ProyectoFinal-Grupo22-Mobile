package proyectofinal.com.example.abc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AbcApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}