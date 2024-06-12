package com.duckyroute.duckyroute.data.remote.api.usersession

import com.duckyroute.duckyroute.data.remote.Authorized
import com.duckyroute.duckyroute.domain.model.UserSessionRequest
import com.duckyroute.duckyroute.domain.model.UserSessionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserSessionApiClient {

    @POST("/usuario/login")
    suspend fun getUserSession(@Body request: UserSessionRequest ): Response<UserSessionResponse>

    @GET("/usuario/user-token")
    suspend fun getNewAccessToken(@Body token: String): Response<String>


}