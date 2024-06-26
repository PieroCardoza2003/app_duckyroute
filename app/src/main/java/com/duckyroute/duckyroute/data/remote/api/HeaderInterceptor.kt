package com.duckyroute.duckyroute.data.remote.api

import com.duckyroute.duckyroute.MainApplication.Companion.preferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferencesManager.getKey("accessToken") ?: ""
        val request = chain.request().newBuilder()
                .addHeader("authorization", token)
                .build()
        return chain.proceed(request)
    }
}