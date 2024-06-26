package com.duckyroute.duckyroute.data.remote.api

import com.duckyroute.duckyroute.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelperAuth {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.getBaseUrlDuckyRouteApi())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .authenticator(TokenAuthenticator())
            .build()
    }
}