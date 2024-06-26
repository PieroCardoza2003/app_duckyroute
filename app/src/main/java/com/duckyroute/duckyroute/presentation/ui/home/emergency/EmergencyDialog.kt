package com.duckyroute.duckyroute.presentation.ui.home.emergency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.duckyroute.duckyroute.databinding.DialogEmergencyBinding
import com.duckyroute.duckyroute.presentation.permissions.Permissions

class EmergencyDialog: DialogFragment() {

    private lateinit var binding: DialogEmergencyBinding
    private lateinit var permisos: Permissions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogEmergencyBinding.inflate(inflater, container, false)
        permisos = Permissions(requireActivity())
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCloseEmergency.setOnClickListener{
            dismiss()
        }

        binding.buttonLlamarEmergency.setOnClickListener{
            permisos.makeCall()
            dismiss()
        }
    }


}