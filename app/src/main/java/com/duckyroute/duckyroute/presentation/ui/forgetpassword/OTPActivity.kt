package com.duckyroute.duckyroute.presentation.ui.forgetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import com.duckyroute.duckyroute.databinding.ActivityOtpBinding
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.presentation.ui.LoadDialog.LoadDialogFragment

class OTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    private val otpViewModel: OTPViewModel by viewModels()
    private var loadDialogFragment: LoadDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()

        otpViewModel.verificationResult.observe(this){result ->
            when(result){
                ResponseStatus.SUCCESS -> showMessage("OK ES CORRECTO")
                ResponseStatus.INCORRECT -> showMessage("El código no es valido, vuelva a intentarlo")
                ResponseStatus.TIMEOUT -> showMessage("Se agotaron los intentos")
                ResponseStatus.ERROR -> showMessage("Ocurrió un error")
                else -> showMessage("Resultado inesperado")
            }
        }

        otpViewModel.isLoading.observe(this){ state ->
            showLoadAnimation(state)
        }
    }

    private fun setupListeners() {
        val textWatcher = createTextWatcher()

        binding.edittextCode1.addTextChangedListener(textWatcher)
        binding.edittextCode2.addTextChangedListener(textWatcher)
        binding.edittextCode3.addTextChangedListener(textWatcher)
        binding.edittextCode4.addTextChangedListener(textWatcher)
        binding.edittextCode5.addTextChangedListener(textWatcher)

        binding.buttonAceptarOtp.setOnClickListener{
            if(areAllEditTextsFilled()){
                val code = "${binding.edittextCode1.text}${binding.edittextCode2.text}${binding.edittextCode3.text}${binding.edittextCode4.text}${binding.edittextCode5.text}"
                showLoadAnimation(true)
                otpViewModel.verifyCode(code)
            }else
                showMessage("El código está incompleto")
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
        when (currentFocus) {
            binding.edittextCode5 -> binding.edittextCode4.requestFocus()
            binding.edittextCode4 -> binding.edittextCode3.requestFocus()
            binding.edittextCode3 -> binding.edittextCode2.requestFocus()
            binding.edittextCode2 -> binding.edittextCode1.requestFocus()
        }
    }

    private fun focusNextEditText() {
        when (currentFocus) {
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadAnimation(state: Boolean) {
        if (state) {
            if (loadDialogFragment == null) {
                loadDialogFragment = LoadDialogFragment("Verificando")
                loadDialogFragment?.show(supportFragmentManager, "load_dialog")
            }
        } else {
            loadDialogFragment?.dismiss()
            loadDialogFragment = null
        }
    }
}