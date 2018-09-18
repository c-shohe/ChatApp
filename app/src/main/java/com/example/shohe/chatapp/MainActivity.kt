package com.example.shohe.chatapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.transitionLoinActivity()
    }


    // transition activity
    private fun transitionLoinActivity() {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        this.startActivity(intent)
    }
}
