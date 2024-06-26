package com.duckyroute.duckyroute.data.remote.api.usuario

import com.duckyroute.duckyroute.data.remote.api.RetrofitHelper
import com.duckyroute.duckyroute.data.remote.api.RetrofitHelperAuth
import com.duckyroute.duckyroute.domain.model.ChangePasswordRequest
import com.duckyroute.duckyroute.domain.model.ChangePhoneRequest
import com.duckyroute.duckyroute.domain.model.NewPasswordRequest
import com.duckyroute.duckyroute.domain.model.EmailRequest
import com.duckyroute.duckyroute.domain.model.ResponseToken
import com.duckyroute.duckyroute.domain.model.VerifyCodeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioService {

    private val retrofit = RetrofitHelper.getRetrofit()
    private val retrofitAuth = RetrofitHelperAuth.getRetrofit()

    suspend fun findUser(request: EmailRequest): Any? {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UsuarioApiClient::class.java).findUser(request)
            response.body()
        }
    }

    suspend fun verifyCodeUser(request: VerifyCodeRequest): ResponseToken? {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UsuarioApiClient::class.java).verifyCodeUser(request)
            response.body()
        }
    }

    suspend fun newPassword(request: NewPasswordRequest): Any? {
        return withContext(Dispatchers.IO){
            val response = retrofitAuth.create(UsuarioApiClient::class.java).newPassword(request)
            response.body()
        }
    }

    suspend fun changePassword(request: ChangePasswordRequest): Any? {
        return withContext(Dispatchers.IO){
            val response = retrofitAuth.create(UsuarioApiClient::class.java).changePassword(request)
            response.body()
        }
    }

    suspend fun changePhone(request: ChangePhoneRequest): Any? {
        return withContext(Dispatchers.IO){
            val response = retrofitAuth.create(UsuarioApiClient::class.java).changePhone(request)
            response.body()
        }
    }

}