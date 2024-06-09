package com.duckyroute.duckyroute.data.repository

import com.duckyroute.duckyroute.data.local.memory.conductor.ConductorDataMemory
import com.duckyroute.duckyroute.domain.model.ConductorResponse

class ConductorMemoryRepository {

    private val conductorData = ConductorDataMemory.getInstance()

    fun getConductor(): ConductorResponse?{
        return conductorData.getConductor()
    }

    fun setConductor(conductor: ConductorResponse){
        conductorData.setConductor(conductor)
    }
}