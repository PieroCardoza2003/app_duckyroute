package com.duckyroute.duckyroute.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.duckyroute.duckyroute.databinding.ActivityMainBinding
import com.duckyroute.duckyroute.presentation.ui.home.HomeActivity
import com.duckyroute.duckyroute.presentation.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.isLogged.observe(this) { isLogged ->
            val intent = if (isLogged) {
                Intent(this, HomeActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }

        mainViewModel.checkIsLogged()
    }
}