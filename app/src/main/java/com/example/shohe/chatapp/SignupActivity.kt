package com.example.shohe.chatapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        this.initSignUpButton()
    }


    // button action
    private fun initSignUpButton() {
        signupButton.setOnClickListener {
            // transition sign up activity
            Log.d("initSignUpButton()", "Sign up user.")
        }
    }

}