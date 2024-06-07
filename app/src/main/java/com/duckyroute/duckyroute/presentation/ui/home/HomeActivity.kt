package com.duckyroute.duckyroute.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager
import com.duckyroute.duckyroute.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val result = preferencesManager.deleteUserSession()
            Toast.makeText(this, "eliminado: $result", Toast.LENGTH_SHORT).show()
        }
    }
}