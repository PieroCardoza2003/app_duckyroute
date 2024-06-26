package com.duckyroute.duckyroute.presentation.ui.home.myinformation.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.duckyroute.duckyroute.databinding.TelefonoBottomDialogBinding
import com.duckyroute.duckyroute.presentation.ui.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TelefonoBottomDialog: BottomSheetDialogFragment()  {

    private val homeViewModel: HomeViewModel by activityViewModels() //viewModel compartido
    private val viewModel: TelefonoViewModel by viewModels()
    private var _binding: TelefonoBottomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TelefonoBottomDialogBinding.inflate(inflater, container, false)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        val countryCodes = arrayOf("+51", "+58", "+57", "+593")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countryCodes)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCountryCode.adapter = adapter

        viewModel.result.observe(viewLifecycleOwner){ result->
            Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()
            dismiss()
        }

        viewModel.data.observe(viewLifecycleOwner){ result ->
            homeViewModel.updateTelefono(result.codigo_pais, result.numero)
        }

        binding.buttonGuardarBottomdialog.setOnClickListener {
            val countryCode = binding.spinnerCountryCode.selectedItem.toString().trim()
            val phoneNumber = binding.edittextTelefonoBottom.text.toString().trim()
            viewModel.updatePhone(countryCode, phoneNumber)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}