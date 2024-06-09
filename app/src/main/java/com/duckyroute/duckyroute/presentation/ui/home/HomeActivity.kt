package com.duckyroute.duckyroute.presentation.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager
import com.duckyroute.duckyroute.databinding.ActivityHomeBinding
import com.duckyroute.duckyroute.domain.model.ConductorResponse
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.presentation.ui.ErrorActivity
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.presentation.ui.login.LoginActivity
import kotlin.reflect.KClass

class HomeActivity : AppCompatActivity() {

    private lateinit var conductor: ConductorResponse
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private var loadDialogFragment: LoadDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoadAnimation(true)
        homeViewModel.onCreate()

        homeViewModel.result.observe(this){ result ->
            when (result){
                ResponseStatus.SUCCESS -> showMessage("Bienvenido ${conductor.nombres}")
                ResponseStatus.EMPTY -> startActivity(LoginActivity::class)
                ResponseStatus.ERROR -> startActivity(ErrorActivity::class)
                else -> startActivity(ErrorActivity::class)
            }
            showLoadAnimation(false)
        }

        homeViewModel.data.observe(this){ data ->
            conductor = data
        }

        binding.button.setOnClickListener{
            val result = preferencesManager.deleteUserSession()
            showMessage("Eliminado: $result")
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
                loadDialogFragment = LoadDialogFragment("Cargando")
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