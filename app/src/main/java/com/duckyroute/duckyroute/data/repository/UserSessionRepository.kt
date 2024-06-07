package com.duckyroute.duckyroute.data.repository

import com.duckyroute.duckyroute.data.remote.api.usersession.UserSessionService
import com.duckyroute.duckyroute.domain.model.UserSessionRequest
import com.duckyroute.duckyroute.domain.model.UserSessionResponse

class UserSessionRepository {
    private val api = UserSessionService()

    suspend fun getUserSession(request: UserSessionRequest): UserSessionResponse?{
        return api.getUserSession(request)
    }
}