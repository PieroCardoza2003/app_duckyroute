package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.remote.UsuarioApiRepository
import com.duckyroute.duckyroute.domain.model.EmailRequest

class FindUserEmailUseCase {
    private val repository = UsuarioApiRepository()

    suspend operator fun invoke(request: EmailRequest): Any? = repository.findUser(request)
}