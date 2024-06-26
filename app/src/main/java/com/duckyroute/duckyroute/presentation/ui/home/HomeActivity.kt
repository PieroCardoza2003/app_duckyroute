package com.duckyroute.duckyroute.presentation.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.ActivityHomeBinding
import com.duckyroute.duckyroute.databinding.NavHeaderBinding
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.domain.model.local.Conductor
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.presentation.ui.error.ErrorActivity
import com.duckyroute.duckyroute.presentation.ui.login.LoginActivity
import kotlin.reflect.KClass

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var conductor: Conductor
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private var loadDialogFragment: LoadDialogFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDrawerNavigation()
        homeViewModel.onCreate()

        homeViewModel.loading.observe(this){ state ->
            showLoadAnimation(state)
        }

        homeViewModel.result.observe(this){ result ->
            when (result){
                ResponseStatus.SUCCESS -> loadDataConductor()
                ResponseStatus.EMPTY -> {
                    showMessage("Se cerró la sesión")
                    startActivity(LoginActivity::class)
                }
                ResponseStatus.ERROR -> startActivity(ErrorActivity::class)
                else -> startActivity(ErrorActivity::class)
            }
        }

    }

    private fun loadDataConductor(){
        val drawerBinding = NavHeaderBinding.bind(binding.navigationDrawer.getHeaderView(0))

        conductor = homeViewModel.getConductor()

        drawerBinding.textviewLetraDrawer.text = conductor.nombres[0].toString()
        drawerBinding.textviewNombresDrawer.text = conductor.nombres
        drawerBinding.textviewApellidosDrawer.text = conductor.apellidos

        showMessage("Bienvenido ${conductor.nombres}")
    }

    private fun setupDrawerNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentcontainer_home) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.navigationDrawer, navController)

        binding.navigationDrawer.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.button_inicio -> {
                    if (navController.currentDestination?.id != R.id.mapsFragment)
                        navController.popBackStack(R.id.mapsFragment, false)
                }
                R.id.button_minformacion -> {
                    if (navController.currentDestination?.id != R.id.informationFragment){
                        navController.navigate(R.id.action_mapsFragment_to_informationFragment)
                    }
                }
                R.id.button_seguridad -> {
                    if (navController.currentDestination?.id != R.id.securityFragment) {
                        navController.navigate(R.id.action_mapsFragment_to_securityFragment)
                    }
                }
                R.id.button_cerrarsesion -> homeViewModel.closeSession()
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        when {
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> binding.drawerLayout.closeDrawer(GravityCompat.START)
            navController.currentDestination?.id == R.id.mapsFragment -> super.onBackPressed()
            else -> navController.popBackStack(R.id.mapsFragment, false)
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