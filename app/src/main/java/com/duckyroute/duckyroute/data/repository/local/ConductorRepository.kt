package com.duckyroute.duckyroute.data.repository.local

import com.duckyroute.duckyroute.MainApplication.Companion.appDatabase
import com.duckyroute.duckyroute.domain.model.local.Conductor

class ConductorRepository {

    private val database = appDatabase.conductorDao()

    suspend fun insertConductor(conductor: Conductor) = database.insertConductor(conductor)

    suspend fun deleteConductor() = database.deleteConductor()

    suspend fun getConductor(id: Int): Conductor? = database.getConductor(id)

    suspend fun updatePhone(id: Int, codigoPais: String, numero: String) = database.updatePhone(id, codigoPais, numero)

}