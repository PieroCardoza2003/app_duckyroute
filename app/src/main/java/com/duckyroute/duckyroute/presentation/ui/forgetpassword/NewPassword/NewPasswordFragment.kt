package com.duckyroute.duckyroute.presentation.ui.forgetpassword.NewPassword

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentNewPasswordBinding
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.presentation.ui.error.ErrorActivity
import com.duckyroute.duckyroute.presentation.ui.forgetpassword.ForgetPasswordViewModel
import kotlin.reflect.KClass


class NewPasswordFragment : Fragment() {

    private val viewModel: ForgetPasswordViewModel by activityViewModels() //viewModel compartido
    private var _binding: FragmentNewPasswordBinding? = null
    private val binding get() = _binding!!
    private var loadDialogFragment: LoadDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewPasswordBinding.inflate(inflater, container, false)
        fragmentActions()
        return binding.root
    }

    private fun fragmentActions(){

        viewModel.loadingPassword.observe(viewLifecycleOwner){ state ->
            showLoadAnimation(state)
        }

        viewModel.responseStatusPassword.observe(viewLifecycleOwner){ result ->
            when (result) {
                ResponseStatus.SUCCESS -> {
                    showMessage("La contraseña se cambio con éxito")
                    requireActivity().finish()
                }
                ResponseStatus.UNSUCCESS -> {
                    showMessage("Ocurrio un error, vuelva a intentarlo")
                    requireActivity().finish()
                }
                ResponseStatus.EMPTY -> showMessage("La contraseña es incorrecta y/o no coinciden")
                ResponseStatus.ERROR -> startActivity(ErrorActivity::class)
                else -> startActivity(ErrorActivity::class)
            }
        }

        binding.buttonAceptarNewpassword.setOnClickListener{
            val password1 = binding.edittextNewpassword1.text.toString()
            val password2 = binding.edittextNewpasword2.text.toString()
            viewModel.verifyPassword(password1, password2)
        }

        binding.buttonCancelarNewpassword.setOnClickListener{
            requireActivity().finish()
        }

        binding.buttonHidepasswordNewpassword1.setOnClickListener{
            viewModel.showPassword1 = !viewModel.showPassword1

            if(viewModel.showPassword1){
                binding.edittextNewpassword1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.buttonHidepasswordNewpassword1.setImageResource(R.drawable.icon_eye)
            }else{
                binding.edittextNewpassword1.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.buttonHidepasswordNewpassword1.setImageResource(R.drawable.icon_noeye)
            }
            binding.edittextNewpassword1.setSelection(binding.edittextNewpassword1.text.length)
        }

        binding.buttonHidepasswordNewpassword2.setOnClickListener{
            viewModel.showPassword2 = !viewModel.showPassword2

            if(viewModel.showPassword2){
                binding.edittextNewpasword2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.buttonHidepasswordNewpassword2.setImageResource(R.drawable.icon_eye)
            }else{
                binding.edittextNewpasword2.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.buttonHidepasswordNewpassword2.setImageResource(R.drawable.icon_noeye)
            }
            binding.edittextNewpasword2.setSelection(binding.edittextNewpasword2.text.length)
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