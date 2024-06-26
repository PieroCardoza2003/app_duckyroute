package com.duckyroute.duckyroute.data.remote.api

import com.duckyroute.duckyroute.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.getBaseUrlDuckyRouteApi())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}