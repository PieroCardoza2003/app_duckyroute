package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.remote.UsuarioApiRepository
import com.duckyroute.duckyroute.domain.model.ChangePasswordRequest

class ChangePasswordUseCase {
    private val repository = UsuarioApiRepository()

    suspend operator fun invoke(request: ChangePasswordRequest): Any? = repository.changePassword(request)
}