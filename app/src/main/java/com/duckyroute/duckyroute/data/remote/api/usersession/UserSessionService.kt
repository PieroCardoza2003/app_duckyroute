package com.duckyroute.duckyroute.data.remote.api.usersession

import com.duckyroute.duckyroute.data.remote.RetrofitHelper
import com.duckyroute.duckyroute.domain.model.UserSessionRequest
import com.duckyroute.duckyroute.domain.model.UserSessionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserSessionService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getUserSession(request: UserSessionRequest): UserSessionResponse? {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UserSessionApiClient::class.java).getUserSession(request)
            response.body()
        }
    }

}