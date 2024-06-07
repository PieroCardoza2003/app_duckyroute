package com.duckyroute.duckyroute.presentation.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.duckyroute.duckyroute.databinding.ActivityLoginBinding
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private var loadDialogFragment: LoadDialogFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loginViewModel.isLoading.observe(this){ state ->
            showLoadAnimation(state)
        }

        loginViewModel.infoMessage.observe(this){ message ->
            showMessage(message)
        }

        binding.buttonIniciarLogin.setOnClickListener{
            val email = binding.edittextEmailLogin.text.toString().trim()
            val password = binding.edittextPasswordLogin.text.toString().trim()

            loginViewModel.iniciarSession(email, password)
        }


        binding.textviewOlvidocontrasenaLogin.setOnClickListener{
            showMessage("Recuperar contraseña")
        }
    }

    private fun showLoadAnimation(state: Boolean) {
        if (state) {
            if (loadDialogFragment == null) {
                loadDialogFragment = LoadDialogFragment("Validando datos")
                loadDialogFragment?.show(supportFragmentManager, "load_dialog")
            }
        } else {
            loadDialogFragment?.dismiss()
            loadDialogFragment = null
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}