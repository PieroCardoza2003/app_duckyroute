package com.duckyroute.duckyroute.data.remote.api

import com.duckyroute.duckyroute.MainApplication.Companion.preferencesManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import com.duckyroute.duckyroute.data.remote.api.tokensession.TokenSessionService
import com.duckyroute.duckyroute.domain.model.ResponseToken

class TokenAuthenticator : Authenticator{
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this){
            return runBlocking {
                val refreshToken = preferencesManager.getKey("refreshToken") ?: ""
                val newToken = TokenSessionService().getNewAccessToken(ResponseToken(refreshToken))

                if(newToken != null){
                    preferencesManager.saveKey("accessToken", newToken.token)
                    response.request().newBuilder()
                        .header("authorization", newToken.token)
                        .build()
                }else{
                    null
                }
            }
        }
    }
}