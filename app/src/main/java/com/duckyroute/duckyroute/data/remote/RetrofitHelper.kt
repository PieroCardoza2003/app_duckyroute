package com.duckyroute.duckyroute.data.remote

import com.duckyroute.duckyroute.utils.DataConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DataConstants.getBaseUrlDuckyRouteApi())
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