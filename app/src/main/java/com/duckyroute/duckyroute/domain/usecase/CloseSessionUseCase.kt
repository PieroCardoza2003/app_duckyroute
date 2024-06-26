package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.MainApplication.Companion.preferencesManager
import com.duckyroute.duckyroute.data.repository.local.ConductorRepository
import com.duckyroute.duckyroute.data.repository.remote.UserSessionRepository
import com.duckyroute.duckyroute.domain.model.CloseSessionRequest
import java.io.IOException

class CloseSessionUseCase {

    private val apiRepository = UserSessionRepository()
    private val dbRepository = ConductorRepository()

    suspend operator fun invoke(): Any? {
        return try {
            val session = preferencesManager.getUserSession()

            if (session != null)
                apiRepository.deleteUserSession(CloseSessionRequest(session.usuarioId))

            clearUserData()
            null
        }catch (ex: IOException){
            clearUserData()
            null
        }
    }

    private suspend fun clearUserData() {
        dbRepository.deleteConductor()
        preferencesManager.deleteUserSession()
    }

}