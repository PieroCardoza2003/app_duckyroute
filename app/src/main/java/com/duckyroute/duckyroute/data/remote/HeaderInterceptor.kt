package com.duckyroute.duckyroute.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val invocation = request.tag(Invocation::class.java)
        val requireAuth = invocation?.method()?.getAnnotation(Authorized::class.java) != null

        val newRequest = if (requireAuth){
            Log.d("prints", "SI requiere autorizacion: ${getAccessToken()}")
            request.newBuilder()
                .addHeader("authorization", getAccessToken())
                .build()
        } else {
            Log.d("prints", "NO requiere autorizacion")
            request
        }

        Log.d("prints", "** LA RESPUESTA EN INTERCEPTOR ES: \n $newRequest")

        return chain.proceed(newRequest)
    }

    private fun getAccessToken(): String {
        return preferencesManager.getUserSession()?.accessToken ?: ""
    }

}