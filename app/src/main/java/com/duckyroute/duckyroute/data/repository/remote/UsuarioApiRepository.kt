package com.duckyroute.duckyroute.data.repository.remote

import com.duckyroute.duckyroute.data.remote.api.usuario.UsuarioService
import com.duckyroute.duckyroute.domain.model.ChangePasswordRequest
import com.duckyroute.duckyroute.domain.model.ChangePhoneRequest
import com.duckyroute.duckyroute.domain.model.NewPasswordRequest
import com.duckyroute.duckyroute.domain.model.EmailRequest
import com.duckyroute.duckyroute.domain.model.ResponseToken
import com.duckyroute.duckyroute.domain.model.VerifyCodeRequest

class UsuarioApiRepository {
    private val api = UsuarioService()

    suspend fun findUser(request: EmailRequest): Any?{
        return api.findUser(request)
    }

    suspend fun verifyCodeUser(request: VerifyCodeRequest): ResponseToken?{
        return api.verifyCodeUser(request)
    }

    suspend fun newPassword(request: NewPasswordRequest): Any?{
        return api.newPassword(request)
    }

    suspend fun changePassword(request: ChangePasswordRequest): Any?{
        return api.changePassword(request)
    }

    suspend fun changePhone(request: ChangePhoneRequest): Any?{
        return api.changePhone(request)
    }
}