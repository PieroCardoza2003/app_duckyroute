package com.duckyroute.duckyroute.presentation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.duckyroute.duckyroute.databinding.ActivityLoginBinding
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.presentation.ui.error.ErrorActivity
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.presentation.ui.forgetpassword.OTPActivity
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
                ResponseStatus.SUCCESS -> startActivity(HomeActivity::class, true)
                ResponseStatus.INCORRECT -> showMessage("Verifique su email y/o contraseña")
                ResponseStatus.INVALID_EMAIL -> showMessage("El email ingresado es incorrecto")
                ResponseStatus.EMPTY -> showMessage("Los campos no pueden estar en blanco")
                ResponseStatus.ERROR -> startActivity(ErrorActivity::class, true)
                else -> startActivity(ErrorActivity::class, true)
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
            startActivity(OTPActivity::class, false)
        }
    }

    private fun startActivity(activityClass: KClass<*>, end: Boolean){
        val intent = Intent(this, activityClass.java)
        startActivity(intent)
        if (end) finish()
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

