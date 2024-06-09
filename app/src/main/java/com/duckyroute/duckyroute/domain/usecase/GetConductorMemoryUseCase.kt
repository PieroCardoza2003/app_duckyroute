package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.ConductorMemoryRepository
import com.duckyroute.duckyroute.domain.model.ConductorResponse

class GetConductorMemoryUseCase {

    private val repository = ConductorMemoryRepository()
    operator fun invoke(): ConductorResponse? = repository.getConductor()
}