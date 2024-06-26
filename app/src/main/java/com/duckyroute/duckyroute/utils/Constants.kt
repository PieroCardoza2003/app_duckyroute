package com.duckyroute.duckyroute.utils

object Constants {

    //private const val BASE_URL_DUCKY_ROUTE_API = "http://192.168.147.216:3000"
    private const val BASE_URL_DUCKY_ROUTE_API = "http://192.168.1.33:3000"
    //private const val BASE_URL_DUCKY_ROUTE_API = "https://api-duckyroute.onrender.com"
    private const val BASE_URL_DUCKY_ROUTE_WEBSOCKET = "http://192.168.147.216:3000"
    private const val CENTRAL_DUCKYROUTE = "+51935826627"

    fun getBaseUrlDuckyRouteApi(): String = BASE_URL_DUCKY_ROUTE_API
    fun getBaseUrlDuckyRouteWebSocket(): String = BASE_URL_DUCKY_ROUTE_WEBSOCKET
    fun getNumberDuckyRoute(): String = CENTRAL_DUCKYROUTE

}