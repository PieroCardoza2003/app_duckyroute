package com.duckyroute.duckyroute.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.duckyroute.duckyroute.domain.model.UserSessionResponse
import java.io.IOException

class PreferencesManager (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("duckyroute_preferences", Context.MODE_PRIVATE)

    fun saveKey(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getKey(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    private fun deleteKey(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    private fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun deleteUserSession(): Boolean{
        return try {
            deleteKey("usuario")
            deleteKey("accessToken")
            deleteKey("refreshToken")
            true
        }catch (e: IOException){
            false
        }
    }

    fun saveUserSession(data: UserSessionResponse): Boolean{
        return try{
            saveKey("usuario", data.usuarioId.toString())
            saveKey("accessToken", data.accessToken)
            saveKey("refreshToken", data.refreshToken)
            true
        } catch (e: IOException){
            false
        }
    }

    fun getUserSession(): UserSessionResponse? {
        return try {
            if (checkUserSession()) {
                val usuario = getKey("usuario")?.toInt() ?: -1
                val accessToken = getKey("accessToken") ?: ""
                val refreshToken = getKey("refreshToken") ?: ""
                UserSessionResponse(usuario, accessToken, refreshToken)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun checkUserSession(): Boolean {
        return (contains("usuario") &&
                contains("accessToken") &&
                contains("refreshToken"))
    }

}