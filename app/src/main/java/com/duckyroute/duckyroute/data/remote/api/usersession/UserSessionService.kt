package com.duckyroute.duckyroute.data.remote.api.usersession

import android.util.Log
import com.duckyroute.duckyroute.data.remote.RetrofitHelper
import com.duckyroute.duckyroute.domain.model.UserSessionRequest
import com.duckyroute.duckyroute.domain.model.UserSessionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class UserSessionService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getUserSession(request: UserSessionRequest): UserSessionResponse? {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UserSessionApiClient::class.java).getUserSession(request)
            response.body()
        }
    }

    suspend fun getNewAccessToken(token: String): String? {
        return withContext(Dispatchers.IO){
            Log.d("prints", "En el servicio de la api para solicitar el token")
            try {
                val response = retrofit.create(UserSessionApiClient::class.java).getNewAccessToken(token)
                response.body()
            }catch (ex: IOException){
                Log.d("prints", "error: $ex")
                null
            }

        }
    }

}