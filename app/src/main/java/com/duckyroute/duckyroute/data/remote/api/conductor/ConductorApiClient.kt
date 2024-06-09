package com.duckyroute.duckyroute.data.remote.api.conductor

import com.duckyroute.duckyroute.domain.model.ConductorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ConductorApiClient {

    @GET("/conductor/datos/{id}")
    suspend fun getConductor(@Path("id") id: Int): Response<ConductorResponse>

}