package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.remote.UsuarioApiRepository
import com.duckyroute.duckyroute.domain.model.ResponseToken
import com.duckyroute.duckyroute.domain.model.VerifyCodeRequest

class VerifyCodeUserUseCase {
    private val repository = UsuarioApiRepository()

    suspend operator fun invoke(request: VerifyCodeRequest): ResponseToken? = repository.verifyCodeUser(request)
}