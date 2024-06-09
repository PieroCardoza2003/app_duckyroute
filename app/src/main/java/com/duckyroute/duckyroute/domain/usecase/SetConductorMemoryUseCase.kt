package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.ConductorMemoryRepository
import com.duckyroute.duckyroute.domain.model.ConductorResponse

class SetConductorMemoryUseCase {

    private val repository = ConductorMemoryRepository()
    operator fun invoke(conductor: ConductorResponse) {
        repository.setConductor(conductor)
    }

}