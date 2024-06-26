package com.duckyroute.duckyroute.data.remote.api.usuario

import com.duckyroute.duckyroute.domain.model.ChangePasswordRequest
import com.duckyroute.duckyroute.domain.model.ChangePhoneRequest
import com.duckyroute.duckyroute.domain.model.NewPasswordRequest
import com.duckyroute.duckyroute.domain.model.EmailRequest
import com.duckyroute.duckyroute.domain.model.ResponseToken
import com.duckyroute.duckyroute.domain.model.VerifyCodeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioApiClient {

    @POST("/usuario/find-user")
    suspend fun findUser(@Body request: EmailRequest): Response<*>

    @POST("/usuario/verify-code")
    suspend fun verifyCodeUser(@Body request: VerifyCodeRequest): Response<ResponseToken>

    @POST("/usuario/new-password")
    suspend fun newPassword(@Body request: NewPasswordRequest): Response<*>

    @POST("/usuario/change-password")
    suspend fun changePassword(@Body request: ChangePasswordRequest): Response<*>

    @POST("/usuario/change-phone")
    suspend fun changePhone(@Body request: ChangePhoneRequest): Response<*>
}