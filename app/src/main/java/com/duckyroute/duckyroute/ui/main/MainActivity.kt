package com.duckyroute.duckyroute.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.duckyroute.duckyroute.databinding.ActivityMainBinding
import com.duckyroute.duckyroute.ui.home.HomeActivity
import com.duckyroute.duckyroute.ui.login.LoginActivity
import com.duckyroute.duckyroute.viewmodel.MainViewModel

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

    private fun iniciarActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}