package com.duckyroute.duckyroute.presentation.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.utils.Constants

class Permissions(private val activity: Activity) {

    companion object {
        private const val REQUEST_CALL_PERMISSION = 888
        private const val REQUEST_LOCATION_PERMISSION = 777
    }


    fun handlePermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            when (requestCode) {
                REQUEST_CALL_PERMISSION -> showPermissionDeniedPopup("llamadas")
                REQUEST_LOCATION_PERMISSION -> showPermissionDeniedPopup("ubicación")
            }
        } else {
            when (requestCode) {
                REQUEST_CALL_PERMISSION -> makeCall()
                REQUEST_LOCATION_PERMISSION -> { /* Permiso de ubicación concedido, lógica adicional si es necesario */ }
            }
        }
    }


    fun makeCall() {
        if (isPermissionGranted(Manifest.permission.CALL_PHONE)) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${Constants.getNumberDuckyRoute()}"))
            activity.startActivity(intent)
        } else {
            requestPermission(Manifest.permission.CALL_PHONE, REQUEST_CALL_PERMISSION)
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(permission: String, requestCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            showPermissionDeniedPopup(permissionType(permission))
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }
    }

    private fun permissionType(permission: String): String {
        return when (permission) {
            Manifest.permission.CALL_PHONE -> "llamadas"
            Manifest.permission.ACCESS_FINE_LOCATION -> "ubicación"
            else -> "desconocido"
        }
    }

    fun showPermissionDeniedPopup(permissionType: String) {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.permision_popup, null)

        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.showAtLocation(activity.findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0)

        val btnGoToSettings = popupView.findViewById<Button>(R.id.btn_go_to_settings)
        val permissionMessage = popupView.findViewById<TextView>(R.id.permission_message)

        permissionMessage.text = "Se rechazó el permiso de $permissionType."

        btnGoToSettings.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", activity.packageName, null)
            }
            activity.startActivity(intent)
            popupWindow.dismiss()
        }
    }

}