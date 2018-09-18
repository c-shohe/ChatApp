package com.example.shohe.chatapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val listView: ListView by lazy { findViewById(R.id.listView) as ListView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataStore: SharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (dataStore.getString("ID", "").isEmpty()) {
            this.transitionLoginActivity()
        }

        Log.d("onCreate()", "you are resisted")
        this.initListView()
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
    
}
