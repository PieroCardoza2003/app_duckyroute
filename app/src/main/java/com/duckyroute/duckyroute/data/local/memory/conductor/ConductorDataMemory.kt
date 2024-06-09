package com.duckyroute.duckyroute.data.local.memory.conductor

import com.duckyroute.duckyroute.domain.model.ConductorResponse

class ConductorDataMemory {

    companion object {
        @Volatile private var instance: ConductorDataMemory? = null // Volatile modifier is necessary

        fun getInstance(): ConductorDataMemory {
            return instance ?: synchronized(this) { // synchronized to avoid concurrency problem
                instance ?: ConductorDataMemory().also { instance = it }
            }
        }
    }

    private var conductor: ConductorResponse? = null

    fun getConductor(): ConductorResponse?{
        return conductor
    }

    fun setConductor(conductor: ConductorResponse){
        this.conductor = conductor
    }
}