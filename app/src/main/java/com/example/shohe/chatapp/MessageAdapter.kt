package com.example.shohe.chatapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class MessageAdapter(private val context: Context) : BaseAdapter() {

    var messages: List<Message> = emptyList()

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(position: Int): Any {
        return messages[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return ((convertView as? MessageCellView) ?: MessageCellView(context)).apply {
            setMessage(messages[position])
        }
    }

}