package com.duckyroute.duckyroute.utils

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class EncryptUtils {
    companion object {

        fun decryptData(text: String): String{
            val data = decodeBase64(text)
            return aesDecrypt(data)
        }

        fun encryptData(text: String): String{
            val data = aesEncrypt(text)
            return encodeBase64(data)
        }

        private fun getSecretKey(): String {
            // I3U8g5Ux<T#2V+q]GcE7'9]&Iu9RmWZP
            val cadena = "U1ROVk9HYzFWWGc4VkNNeVZpdHhYVWRqUlRjbk9WMG1TWFU1VW0xWFdsQT0="
            return String(decodeBase64(String(decodeBase64(cadena))))
        }

        private fun decodeBase64(text: String): ByteArray {
            return Base64.decode(text, Base64.DEFAULT)
        }

        private fun encodeBase64(text: ByteArray): String {
            return Base64.encodeToString(text, Base64.DEFAULT)
        }

        private fun aesEncrypt(data: String): ByteArray {
            return try {
                val text = data.toByteArray()
                val secretKey: SecretKey = SecretKeySpec(getSecretKey().toByteArray(), "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                val ivParameterSpec = IvParameterSpec(ByteArray(16))
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
                cipher.doFinal(text)
            } catch (e: Exception) {
                print(e.message)
                ByteArray(0)
            }
        }

        private fun aesDecrypt(encryptedData: ByteArray): String {
            return try {
                val secretKey: SecretKey = SecretKeySpec(getSecretKey().toByteArray(), "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                val ivParameterSpec = IvParameterSpec(ByteArray(16))
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
                String(cipher.doFinal(encryptedData))
            } catch (e: Exception) {
                print(e.message)
                ""
            }
        }
    }
}