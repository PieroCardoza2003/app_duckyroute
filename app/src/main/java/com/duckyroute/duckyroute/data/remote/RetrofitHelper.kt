package com.duckyroute.duckyroute.data.remote

import com.duckyroute.duckyroute.utils.DataConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(DataConstants.getBaseUrlDuckyRouteApi())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}