package com.duckyroute.duckyroute.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.33:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}