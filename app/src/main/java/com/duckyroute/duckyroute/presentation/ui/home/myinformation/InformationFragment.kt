package com.duckyroute.duckyroute.presentation.ui.home.myinformation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentInformationBinding
import com.duckyroute.duckyroute.presentation.ui.home.HomeViewModel
import com.duckyroute.duckyroute.presentation.ui.home.myinformation.conductor.ConductorFragment
import com.duckyroute.duckyroute.presentation.ui.home.myinformation.cuenta.CuentaFragment
import com.duckyroute.duckyroute.presentation.ui.home.myinformation.personal.PersonalFragment
import com.duckyroute.duckyroute.presentation.ui.home.myinformation.personal.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class InformationFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels() //viewModel compartido
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    private val fragmentList by lazy {
        listOf( PersonalFragment(), ConductorFragment(), CuentaFragment() )
    }

    private val adapter by lazy {
        ViewPagerAdapter(requireActivity(), fragmentList)
    }
    
    private val tabTitles = listOf("Personal", "Conductor", "Cuenta")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        setupViewPagerAndTabs()
        return binding.root
    }

    private fun setupViewPagerAndTabs() {
        val nombre = "${homeViewModel.getConductor().nombres} ${homeViewModel.getConductor().apellidos}"
        binding.viewpagerMiinformacion.adapter = adapter

        TabLayoutMediator(binding.tablayoutMiinformacion, binding.viewpagerMiinformacion) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        binding.buttonBackInformacion.setOnClickListener{
            findNavController().popBackStack(R.id.mapsFragment, false)
        }

        binding.textviewLetraInformacion.text = nombre[0].toString()
        binding.textviewNombresInformacion.text = nombre
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}