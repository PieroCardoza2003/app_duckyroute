package com.duckyroute.duckyroute.presentation.ui.home.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.duckyroute.duckyroute.databinding.DialogVehicleBinding

class VehicleDialog: DialogFragment() {

    private lateinit var binding: DialogVehicleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCloseVehicle.setOnClickListener{
            dismiss()
        }

        binding.buttonAceptarVehicle.setOnClickListener{
            dismiss()
        }
    }
}