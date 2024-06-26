package com.duckyroute.duckyroute.presentation.ui.home.myinformation.conductor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentConductorBinding
import com.duckyroute.duckyroute.databinding.FragmentInformationBinding
import com.duckyroute.duckyroute.domain.model.local.Conductor
import com.duckyroute.duckyroute.presentation.ui.home.HomeViewModel

class ConductorFragment : Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels() //viewModel compartido
    private lateinit var conductor: Conductor
    private var _binding: FragmentConductorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConductorBinding.inflate(inflater, container, false)

        conductor = homeViewModel.getConductor()

        binding.edittextNlicenciaConductor.setText(conductor.nroLicencia)
        binding.edittextTipolicenciaConductor.setText(conductor.tipoLicencia)
        binding.edittextFechaemisionConductor.setText(conductor.fechaEmisionLicencia)
        binding.edittextFechaexpiracionConductor.setText(conductor.fechaExpiracionLicencia)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}