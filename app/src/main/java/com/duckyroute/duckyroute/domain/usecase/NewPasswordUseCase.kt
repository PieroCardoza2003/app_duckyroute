package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.remote.UsuarioApiRepository
import com.duckyroute.duckyroute.domain.model.NewPasswordRequest

class NewPasswordUseCase {

    private val repository = UsuarioApiRepository()

    suspend operator fun invoke(request: NewPasswordRequest): Any? = repository.newPassword(request)

}