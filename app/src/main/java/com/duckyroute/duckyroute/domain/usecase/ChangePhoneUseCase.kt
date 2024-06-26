package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.local.ConductorRepository
import com.duckyroute.duckyroute.data.repository.remote.UsuarioApiRepository
import com.duckyroute.duckyroute.domain.model.ChangePhoneRequest

class ChangePhoneUseCase {
    val apiRepository = UsuarioApiRepository()
    private val dbRepository = ConductorRepository()

    suspend operator fun invoke(request: ChangePhoneRequest): Any?{
        val dataApi = apiRepository.changePhone(request)

        if (dataApi != null){
            dbRepository.updatePhone(request.id, request.codigo_pais, request.numero)
            return dataApi
        }
        return null
    }
}