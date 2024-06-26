package com.duckyroute.duckyroute.presentation.ui.forgetpassword.OTP

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentOTPBinding
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment
import com.duckyroute.duckyroute.presentation.ui.error.ErrorActivity
import com.duckyroute.duckyroute.presentation.ui.forgetpassword.ForgetPasswordViewModel
import kotlin.reflect.KClass

class OTPFragment : Fragment() {
    private val viewModel: ForgetPasswordViewModel by activityViewModels() //viewModel compartido
    private var _binding: FragmentOTPBinding? = null
    private val binding get() = _binding!!
    private var loadDialogFragment: LoadDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOTPBinding.inflate(inflater, container, false)
        fragmentActions()
        return binding.root
    }

    private fun fragmentActions() {
        val textWatcher = createTextWatcher()

        binding.edittextCode1.addTextChangedListener(textWatcher)
        binding.edittextCode2.addTextChangedListener(textWatcher)
        binding.edittextCode3.addTextChangedListener(textWatcher)
        binding.edittextCode4.addTextChangedListener(textWatcher)
        binding.edittextCode5.addTextChangedListener(textWatcher)


        binding.buttonAceptarOtp.setOnClickListener{
            if(areAllEditTextsFilled()){
                val code = "${binding.edittextCode1.text}${binding.edittextCode2.text}${binding.edittextCode3.text}${binding.edittextCode4.text}${binding.edittextCode5.text}"
                viewModel.verifyCode(code)
            }else
                showMessage("El código está incompleto")
        }


        viewModel.responseStatusOTP.observe(viewLifecycleOwner){ result ->
            when(result){
                ResponseStatus.SUCCESS -> findNavController().navigate(R.id.action_OTPFragment_to_newPasswordFragment)
                ResponseStatus.UNSUCCESS -> showMessage("El código no es valido, vuelva a intentarlo")
                ResponseStatus.TIMEOUT -> {
                    showMessage("Se agotaron los intentos")
                    requireActivity().finish()
                }
                ResponseStatus.ERROR -> startActivity(ErrorActivity::class)
                else -> startActivity(ErrorActivity::class)
            }
        }

        viewModel.loadingOTP.observe(viewLifecycleOwner){ state ->
            showLoadAnimation(state)
        }
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if (editable.isNullOrEmpty()) {
                    focusPreviousEditText()
                } else if (editable.length == 1) {
                    focusNextEditText()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    private fun focusPreviousEditText() {
        when (binding.root.findFocus()) {
            binding.edittextCode5 -> binding.edittextCode4.requestFocus()
            binding.edittextCode4 -> binding.edittextCode3.requestFocus()
            binding.edittextCode3 -> binding.edittextCode2.requestFocus()
            binding.edittextCode2 -> binding.edittextCode1.requestFocus()
        }
    }

    private fun focusNextEditText() {
        when (binding.root.findFocus()) {
            binding.edittextCode1 -> binding.edittextCode2.requestFocus()
            binding.edittextCode2 -> binding.edittextCode3.requestFocus()
            binding.edittextCode3 -> binding.edittextCode4.requestFocus()
            binding.edittextCode4 -> binding.edittextCode5.requestFocus()
        }
    }

    private fun areAllEditTextsFilled(): Boolean {
        return binding.edittextCode1.text.isNotEmpty() &&
                binding.edittextCode2.text.isNotEmpty() &&
                binding.edittextCode3.text.isNotEmpty() &&
                binding.edittextCode4.text.isNotEmpty() &&
                binding.edittextCode5.text.isNotEmpty()
    }

    private fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadAnimation(state: Boolean) {
        if (state) {
            if (loadDialogFragment == null) {
                loadDialogFragment = LoadDialogFragment("Verificando")
                loadDialogFragment?.show(childFragmentManager, "load_dialog")
            }
        } else {
            loadDialogFragment?.dismiss()
            loadDialogFragment = null
        }
    }

    private fun startActivity(activityClass: KClass<*>){
        val intent = Intent(context, activityClass.java)
        startActivity(intent)
        requireActivity().finish()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}