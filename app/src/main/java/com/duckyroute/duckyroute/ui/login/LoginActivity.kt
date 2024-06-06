package com.duckyroute.duckyroute.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.duckyroute.duckyroute.databinding.ActivityLoginBinding
import com.duckyroute.duckyroute.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.ui.home.HomeActivity
import com.duckyroute.duckyroute.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loginViewModel.message.observe(this){ message ->
            showMessage(message)
        }

        loginViewModel.loginResult.observe(this){ result ->
            //showLoadingAnimation(false)
            if(result){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.buttonIniciarLogin.setOnClickListener{
            val email = binding.edittextEmailLogin.text.toString().trim()
            val password = binding.edittextPasswordLogin.text.toString().trim()

            if (loginViewModel.validarCampos(email, password)){
                //showLoadingAnimation(true)
                loginViewModel.iniciarSession()
            }
        }


        binding.textviewOlvidocontrasenaLogin.setOnClickListener{
            showMessage("Recuperar contraseña")
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}