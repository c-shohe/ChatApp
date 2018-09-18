package com.example.shohe.chatapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.initSignUpButton()
    }


    // button action
    private fun initSignUpButton() {
        signupButton.setOnClickListener {
            // transition sign up activity
            this.transitionSignUpActivity()
            Log.d("initSignUpButton()", "transition sign up activity.")
        }
    }

    // transition activity
    private fun transitionSignUpActivity() {
        val intent: Intent = Intent(this, SignupActivity::class.java)
        this.startActivity(intent)
    }
}