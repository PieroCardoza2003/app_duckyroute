package com.duckyroute.duckyroute.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duckyroute.duckyroute.data.local.database.dao.ConductorDAO
import com.duckyroute.duckyroute.domain.model.local.Conductor

@Database(entities = [Conductor::class], version = 1) // agregar demas clases
abstract class AppDatabase: RoomDatabase() {

    abstract fun conductorDao(): ConductorDAO

    companion object {
        const val NAME = "app_database"
    }

}