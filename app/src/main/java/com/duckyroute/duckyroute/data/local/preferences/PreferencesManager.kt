package com.duckyroute.duckyroute.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.duckyroute.duckyroute.domain.model.UserSessionResponse
import java.io.IOException

class PreferencesManager (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("duckyroute_preferences", Context.MODE_PRIVATE)

    private fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    private fun deleteString(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    private fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun deleteUserSession(): Boolean{
        try {
            deleteString("usuario")
            deleteString("accessToken")
            deleteString("refreshToken")
            deleteString("remember")
            return true
        }catch (e: IOException){
            print(e)
        }
        return false
    }

    fun saveUserSession(data: UserSessionResponse): Boolean{
        try{
            saveString("usuario", data.usuarioId.toString())
            saveString("accessToken", data.accessToken)
            saveString("refreshToken", data.refreshToken)
            saveString("remember", "true")
            return true
        } catch (e: IOException){
            print(e)
        }
        return false
    }

    fun checkUserSession(): Boolean{
        if(checkKeys()){
            val remember = getString("remember","")
            if(remember.equals("true")){
                return true
            }
        }
        return false
    }

    fun checkKeys(): Boolean {
        return (contains("usuario") &&
                contains("accessToken") &&
                contains("refreshToken") &&
                contains("remember"))
    }

}