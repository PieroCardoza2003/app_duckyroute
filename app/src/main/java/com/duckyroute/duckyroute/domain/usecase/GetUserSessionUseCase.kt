package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.UserSessionRepository
import com.duckyroute.duckyroute.domain.model.UserSessionRequest
import com.duckyroute.duckyroute.domain.model.UserSessionResponse

class GetUserSessionUseCase {

    private val repository = UserSessionRepository()

    suspend operator fun invoke(request: UserSessionRequest): UserSessionResponse? = repository.getUserSession(request)

}