package com.duckyroute.duckyroute.data.repository

import com.duckyroute.duckyroute.data.remote.api.conductor.ConductorService
import com.duckyroute.duckyroute.domain.model.ConductorResponse

class ConductorRepository {

    private val api = ConductorService()

    suspend fun getConductor(id: String): ConductorResponse? {
        return api.getConductor(id)
    }




}