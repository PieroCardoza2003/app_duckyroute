package com.duckyroute.duckyroute.presentation.ui.forgetpassword.FindAccount

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentFindAccountBinding
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.presentation.ui.error.ErrorActivity
import com.duckyroute.duckyroute.presentation.ui.forgetpassword.ForgetPasswordViewModel
import kotlin.reflect.KClass

class FindAccountFragment : Fragment() {

    private val viewModel: ForgetPasswordViewModel by activityViewModels() //viewModel compartido
    private var _binding: FragmentFindAccountBinding? = null
    private val binding get() = _binding!!
    private var loadDialogFragment: LoadDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindAccountBinding.inflate(inflater, container, false)
        fragmentActions()
        return binding.root
    }

    private fun fragmentActions(){

        viewModel.loadingEmail.observe(viewLifecycleOwner){ state ->
            showLoadAnimation(state)
        }

        viewModel.responseStatusEmail.observe(viewLifecycleOwner){ result ->
            when (result) {
                ResponseStatus.SUCCESS -> findNavController().navigate(R.id.action_findAccountFragment_to_OTPFragment)
                ResponseStatus.UNSUCCESS -> showMessage("No se encontraron resultados")
                ResponseStatus.INVALID_EMAIL -> showMessage("El email ingresado es incorrecto")
                ResponseStatus.ERROR -> startActivity(ErrorActivity::class)
                else -> startActivity(ErrorActivity::class)
            }
        }

        binding.buttonAceptarRecuperaCuenta.setOnClickListener{
            val email = binding.edittextEmailRecuperaCuenta.text.toString()
            viewModel.verifiEmail(email)
        }

        binding.buttonCancelarRecuperaCuenta.setOnClickListener{
            requireActivity().finish()
        }
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