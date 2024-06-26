package com.duckyroute.duckyroute.presentation.ui.home.chatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.domain.model.Message

class MessagesAdapter(private var listMessages: List<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val SENDER_VIEW_TYPE = 1
    private val RECEIVER_VIEW_TYPE = 2

    fun updateMessages(newList: List<Message>) {
        val diffCallback = MessageDiffCallback(listMessages, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listMessages = newList
        diffResult.dispatchUpdatesTo(this)
    }

    fun clearMessages() {
        val newList = emptyList<Message>()
        val diffCallback = MessageDiffCallback(listMessages, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listMessages = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class SenderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.textview_name_sendmessage_chat)
        val txtMessage: TextView = view.findViewById(R.id.textview_sendmessage_chat)

        fun bind(message: Message){
            txtName.text = message.name[0].toString()
            txtMessage.text = message.message
        }
    }

    inner class ReceiverViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtMessage: TextView = view.findViewById(R.id.textview_recivemessage_chat)

        fun bind(message: Message){
            txtMessage.text = message.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            SENDER_VIEW_TYPE -> SenderViewHolder(layoutInflater.inflate(R.layout.item_send_message, parent, false))
            RECEIVER_VIEW_TYPE -> ReceiverViewHolder(layoutInflater.inflate(R.layout.item_recive_message, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = listMessages[position]
        when(holder){
            is SenderViewHolder -> holder.bind(message)
            is ReceiverViewHolder -> holder.bind(message)
            else -> throw IllegalArgumentException("Invalid view holder type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listMessages[position].type == 0) {
            SENDER_VIEW_TYPE
        } else {
            RECEIVER_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int = listMessages.size
}
