package com.duckyroute.duckyroute.data.remote

import android.util.Log
import com.duckyroute.duckyroute.data.remote.api.usersession.UserSessionService
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager

class TokenAuthenticator : Authenticator{
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.d("prints", "flujo normal en authenticator")
        if (response.code() == 401) {
            Log.d("prints", "401 en autenticator")
            synchronized(this){
                val token = runBlocking { updatedToken() }
                Log.d("prints", "EL TOKEN SE ACTUALIZO A : $token")
                return response.request().newBuilder()
                    .header("authorization", token)
                    .build()
            }
        }
        return null
    }

    private suspend fun updatedToken(): String {
        Log.d("prints", "Solicitar accestoken en updatedToken")
        val refreshToken = preferencesManager.getUserSession()?.refreshToken ?: ""
        Log.d("prints", "se obtuvo el refreshtoken: $refreshToken")

        val token = UserSessionService().getNewAccessToken(refreshToken)

        Log.d("prints", "se solicito el token y se obtuvo: $token")
        if(!token.isNullOrEmpty()){
            preferencesManager.saveString("accessToken", token)
            return token
        }
        return ""
    }
}