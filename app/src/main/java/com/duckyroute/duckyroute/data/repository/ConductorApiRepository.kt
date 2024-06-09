package com.duckyroute.duckyroute.data.repository

import com.duckyroute.duckyroute.data.remote.api.conductor.ConductorService
import com.duckyroute.duckyroute.domain.model.ConductorResponse

class ConductorApiRepository {

    private val api = ConductorService()

    suspend fun getConductor(id: Int): ConductorResponse? {
        return api.getConductor(id)
    }
}