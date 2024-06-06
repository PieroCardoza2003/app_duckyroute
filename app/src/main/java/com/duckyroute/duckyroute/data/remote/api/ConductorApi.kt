package com.duckyroute.duckyroute.data.remote.api

import com.duckyroute.duckyroute.model.ConductorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ConductorApi {

    @GET("/conductor/datos/{id}")
    suspend fun getConductor(@Path("id") id: String): Response<ConductorResponse>


}