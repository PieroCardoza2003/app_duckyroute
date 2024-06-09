package com.duckyroute.duckyroute.presentation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.duckyroute.duckyroute.databinding.ActivityLoginBinding
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.presentation.ui.ErrorActivity
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.presentation.ui.home.HomeActivity
import kotlin.reflect.KClass

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private var loadDialogFragment: LoadDialogFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loginViewModel.loginResult.observe(this){ result ->

            when (result) {
                ResponseStatus.SUCCESS -> startActivity(HomeActivity::class)
                ResponseStatus.INCORRECT -> showMessage("Verifique su email y/o contraseña")
                ResponseStatus.INVALID_EMAIL -> showMessage("El email ingresado es incorrecto")
                ResponseStatus.EMPTY -> showMessage("Los campos no pueden estar en blanco")
                ResponseStatus.ERROR -> startActivity(ErrorActivity::class)
                else -> startActivity(ErrorActivity::class)
            }
            showLoadAnimation(false)
        }

        binding.buttonIniciarLogin.setOnClickListener{
            val email = binding.edittextEmailLogin.text.toString().trim()
            val password = binding.edittextPasswordLogin.text.toString().trim()

            showLoadAnimation(true)
            loginViewModel.iniciarSession(email, password)
        }


        binding.textviewOlvidocontrasenaLogin.setOnClickListener{
            showMessage("Recuperar contraseña")
        }
    }

    private fun startActivity(activityClass: KClass<*>){
        val intent = Intent(this, activityClass.java)
        startActivity(intent)
        finish()
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

