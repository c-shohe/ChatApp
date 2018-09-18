package com.example.shohe.chatapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.example.shohe.chatapp.R.id.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*
import kotlin.collections.HashMap

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        this.initSignUpButton()
    }


    // button action
    private fun initSignUpButton() {
        s_signupButton.setOnClickListener {
            // sign up user
            val name: String = s_nameTextView.text.toString()
            val email: String = s_mailTextView.text.toString()
            val password: String = s_passwordTextView.text.toString()

            this.signupUser(name, email, password)
            Log.d("initSignUpButton()", "Sign up user.")
        }
    }

    // sign up user
    private fun signupUser(name: String, email: String, password: String) {
        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
        val user: HashMap<String, Any> = HashMap<String, Any>()
        user.put("name", name)
        user.put("email", email)
        user.put("password", password)

        database.collection("user").add(user).addOnSuccessListener {
            val dataStore: SharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)
            dataStore.edit().putString("ID", it.id).apply()
            this.transitionMainActivity()
            Log.d("signupUser()", "Added user with ID: ${it.id}")
        }.addOnFailureListener {
            Log.w("signupUser()", "Error added user: ${it}")
        }
    }


    // transition activity
    private fun transitionMainActivity() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

}