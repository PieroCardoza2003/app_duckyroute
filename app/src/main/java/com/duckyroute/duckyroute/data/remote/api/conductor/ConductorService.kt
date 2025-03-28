package com.duckyroute.duckyroute.data.remote.api.conductor

import com.duckyroute.duckyroute.data.remote.api.RetrofitHelperAuth
import com.duckyroute.duckyroute.domain.model.ConductorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConductorService {

    private val retrofit = RetrofitHelperAuth.getRetrofit()

    suspend fun getConductor(id: Int): ConductorResponse?{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(ConductorApiClient::class.java).getConductor(id)
            response.body()
        }
    }
}