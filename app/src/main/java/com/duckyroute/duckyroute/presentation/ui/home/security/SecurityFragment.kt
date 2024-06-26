package com.duckyroute.duckyroute.presentation.ui.home.security

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentSecurityBinding
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.presentation.ui.error.ErrorActivity
import kotlin.reflect.KClass


class SecurityFragment : Fragment() {

    private val viewModel: SecurityFragmentViewModel by viewModels()
    private var _binding: FragmentSecurityBinding? = null
    private val binding get() = _binding!!
    private var loadDialogFragment: LoadDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecurityBinding.inflate(inflater, container, false)
        fragmentActions()
        return binding.root
    }

    private fun fragmentActions(){

        viewModel.loading.observe(viewLifecycleOwner){ state ->
            showLoadAnimation(state)
        }

        viewModel.responseStatus.observe(viewLifecycleOwner){ result ->
            when (result) {
                ResponseStatus.SUCCESS -> {
                    showMessage("La contraseña se cambio con éxito")
                    closeFragment()
                }
                ResponseStatus.UNSUCCESS -> showMessage("No se pudo cambiar la contraseña")
                ResponseStatus.EMPTY -> showMessage("Complete los campos")
                ResponseStatus.ERROR -> startActivity(ErrorActivity::class)
                else -> startActivity(ErrorActivity::class)
            }
        }

        binding.buttonBackSeguridad.setOnClickListener{
            closeFragment()
        }

        binding.buttonCancelarSecurity.setOnClickListener{
            closeFragment()
        }

        binding.buttonAceptarSecurity.setOnClickListener{
            val oldpassword = binding.edittextOldpasswordSecurity.text.toString()
            val newPassword = binding.edittextNewpaswordSecurity.text.toString()
            viewModel.verifyPassword(oldpassword, newPassword)
        }

        binding.buttonHidepasswordOldpassword.setOnClickListener{
            viewModel.showOldPassword = !viewModel.showOldPassword

            if(viewModel.showOldPassword){
                binding.edittextOldpasswordSecurity.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.buttonHidepasswordOldpassword.setImageResource(R.drawable.icon_eye)
            }else{
                binding.edittextOldpasswordSecurity.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.buttonHidepasswordOldpassword.setImageResource(R.drawable.icon_noeye)
            }
            binding.edittextOldpasswordSecurity.setSelection(binding.edittextOldpasswordSecurity.text.length)
        }


        binding.buttonHidepasswordNewpassword.setOnClickListener{
            viewModel.showNewPassword = !viewModel.showNewPassword

            if(viewModel.showNewPassword){
                binding.edittextNewpaswordSecurity.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.buttonHidepasswordNewpassword.setImageResource(R.drawable.icon_eye)
            }else{
                binding.edittextNewpaswordSecurity.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.buttonHidepasswordNewpassword.setImageResource(R.drawable.icon_noeye)
            }
            binding.edittextNewpaswordSecurity.setSelection(binding.edittextNewpaswordSecurity.text.length)
        }

    }

    private fun closeFragment(){
        findNavController().popBackStack(R.id.mapsFragment, false)
    }

    private fun startActivity(activityClass: KClass<*>){
        val intent = Intent(context, activityClass.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadAnimation(state: Boolean) {
        if (state) {
            if (loadDialogFragment == null) {
                loadDialogFragment = LoadDialogFragment("Cargando")
                loadDialogFragment?.show(childFragmentManager, "load_dialog")
            }
        } else {
            loadDialogFragment?.dismiss()
            loadDialogFragment = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}