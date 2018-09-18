package com.example.shohe.chatapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listView: ListView by lazy { findViewById(R.id.listView) as ListView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.checkUserStatus()
        this.initListView()
        this.initSendButton()
    }


    // user status: isRegisted
    private fun checkUserStatus() {
        val dataStore: SharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (dataStore.getString("ID", "").isEmpty()) {
            this.transitionLoginActivity()
        }
    }


    // transition activity
    private fun transitionLoginActivity() {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        this.startActivity(intent)
    }


    // adapter
    private fun initListView() {
        val adapter: MessageAdapter = MessageAdapter(this)
        adapter.messages = listOf(Message("id", "who", "testtest"), Message("id", "who", "world"))
        listView.adapter = adapter
    }


    // send button
    private fun initSendButton() {
        sendButton.setOnClickListener {
            
        }
    }
}
