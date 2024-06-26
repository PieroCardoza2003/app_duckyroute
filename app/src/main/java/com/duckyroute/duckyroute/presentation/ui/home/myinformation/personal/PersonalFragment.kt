package com.duckyroute.duckyroute.presentation.ui.home.myinformation.personal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentPersonalBinding
import com.duckyroute.duckyroute.domain.model.local.Conductor
import com.duckyroute.duckyroute.presentation.ui.home.HomeViewModel

class PersonalFragment : Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels() //viewModel compartido
    private lateinit var conductor: Conductor
    private var _binding: FragmentPersonalBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalBinding.inflate(inflater, container, false)

        conductor = homeViewModel.getConductor()

        binding.edittextNombresPersonal.setText(conductor.nombres)
        binding.edittextApellidosPersonal.setText(conductor.apellidos)
        binding.edittextDniPersonal.setText(conductor.dni)
        binding.edittextFechanacimientoPersonal.setText(conductor.fechaNacimiento)
        binding.edittextDireccionPersonal.setText(conductor.direccion ?: "")
        binding.edittextTelefonoPersonal.setText("${conductor.codigo_pais ?: ""} ${conductor.numero ?: ""}")

        fragmentActions()

        return binding.root
    }

    private fun fragmentActions(){

        binding.textviewEditartelefonoPersonal.setOnClickListener{
            val bottomSheetDialog = TelefonoBottomDialog()
            bottomSheetDialog.show(parentFragmentManager, bottomSheetDialog.tag)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}