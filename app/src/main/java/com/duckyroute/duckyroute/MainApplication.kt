package com.duckyroute.duckyroute

import android.app.Application
import androidx.room.Room
import com.duckyroute.duckyroute.data.local.database.AppDatabase
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManager

// Este flujo inicia cuando se inicia la aplicacion y esta definido en el androidManifest
class MainApplication: Application() {

    companion object {
        lateinit var appDatabase: AppDatabase
        lateinit var preferencesManager: PreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        accessToSharedPreferences()
        accessToLocalDatabase()
    }

    private fun accessToLocalDatabase(){
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }

    private fun accessToSharedPreferences(){
        preferencesManager = PreferencesManager(applicationContext)
    }

}
