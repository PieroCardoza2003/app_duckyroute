package com.duckyroute.duckyroute.presentation.ui.error

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duckyroute.duckyroute.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCerrarError.setOnClickListener{
            finishAffinity()
        }
    }
}