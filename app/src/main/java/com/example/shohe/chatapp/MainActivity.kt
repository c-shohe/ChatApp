package com.example.shohe.chatapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataStore: SharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (dataStore.getString("ID", "").isEmpty()) {
            this.transitionLoginActivity()
        }

        Log.d("onCreate()", "you are resisted")
    }


    // transition activity
    private fun transitionLoginActivity() {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        this.startActivity(intent)
    }
}
