package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.ConductorRepository
import com.duckyroute.duckyroute.domain.model.ConductorResponse

class ConductorUseCase {
    private val repository = ConductorRepository()

    suspend operator fun invoke(id: String): ConductorResponse? = repository.getConductor(id)


}