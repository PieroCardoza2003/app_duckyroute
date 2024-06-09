package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.ConductorApiRepository
import com.duckyroute.duckyroute.domain.model.ConductorResponse

class GetConductorApiUseCase {
    private val repository = ConductorApiRepository()

    suspend operator fun invoke(id: Int): ConductorResponse? = repository.getConductor(id)


}