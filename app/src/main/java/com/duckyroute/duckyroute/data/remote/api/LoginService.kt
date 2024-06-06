package com.duckyroute.duckyroute.data.remote.api

import com.duckyroute.duckyroute.data.remote.RetrofitHelper
import com.duckyroute.duckyroute.model.UserSessionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getUserSession(): UserSessionResponse? {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(LoginApiClient::class.java).getUserSession()
            response.body()
        }
    }

}