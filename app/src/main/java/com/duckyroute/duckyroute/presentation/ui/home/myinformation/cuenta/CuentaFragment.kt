package com.duckyroute.duckyroute.presentation.ui.home.myinformation.cuenta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentCuentaBinding
import com.duckyroute.duckyroute.databinding.FragmentInformationBinding
import com.duckyroute.duckyroute.domain.model.local.Conductor
import com.duckyroute.duckyroute.presentation.ui.home.HomeViewModel


class CuentaFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels() //viewModel compartido
    private var _binding: FragmentCuentaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCuentaBinding.inflate(inflater, container, false)
        fragmentActions()
        return binding.root
    }

    private fun fragmentActions() {

        binding.textviewEditarpasswordCuenta.setOnClickListener{
            findNavController().navigate(R.id.action_mapsFragment_to_securityFragment)
        }

        binding.edittextEmailCuenta.setText(homeViewModel.getConductor().email)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}