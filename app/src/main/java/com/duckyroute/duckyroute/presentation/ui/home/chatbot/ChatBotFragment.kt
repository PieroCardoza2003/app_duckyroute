package com.duckyroute.duckyroute.presentation.ui.home.chatbot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.duckyroute.duckyroute.databinding.FragmentChatBotBinding
import com.duckyroute.duckyroute.domain.model.Message
import com.duckyroute.duckyroute.presentation.ui.home.HomeViewModel

class ChatBotFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels() //viewModel compartido
    private val viewModel: ChatbotViewModel by viewModels()

    private var _binding: FragmentChatBotBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MessagesAdapter
    private var listaMensajes: MutableList<Message> = mutableListOf()
    private var messageIdCounter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBotBinding.inflate(inflater, container, false)
        fragmentActions()
        return binding.root
    }

    private fun fragmentActions() {
        adapter = MessagesAdapter(listaMensajes)
        binding.reciclerviewMensajesChat.layoutManager = LinearLayoutManager(requireContext())
        binding.reciclerviewMensajesChat.adapter = adapter

        viewModel.sendMessage("Hola")

        viewModel.result.observe(viewLifecycleOwner){ response ->
            showMessage("Duckybot", response, 1)
        }

        binding.buttonLimpiarChat.setOnClickListener{
            adapter.clearMessages()
            listaMensajes.clear()
        }

        binding.botonEnviarChat.setOnClickListener{
            val text = binding.edittextMensajeChat.text.toString().trim()
            if (text.isNotBlank()){
                showMessage(homeViewModel.getConductor().nombres, text, 0)
                viewModel.sendMessage(text)
            }
            binding.edittextMensajeChat.text.clear()
        }

        binding.buttonBackChat.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun showMessage(nombre: String, mensaje: String, type: Int){
        listaMensajes.add(Message(messageIdCounter++, nombre, mensaje, type))
        adapter.updateMessages(listaMensajes)
        binding.reciclerviewMensajesChat.scrollToPosition(listaMensajes.size - 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}