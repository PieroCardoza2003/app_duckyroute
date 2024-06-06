package com.duckyroute.duckyroute.data.local.preferences

import android.app.Application

class PreferencesManagerService: Application() {

    companion object{
        // para que se pueda acceder de cualquier parte de la aplicacion
        lateinit var preferencesManager: PreferencesManager
    }

    override fun onCreate() {
        // este flujo inicia cuando se inicia la aplicacion
        super.onCreate()
        preferencesManager = PreferencesManager(applicationContext)
    }
}