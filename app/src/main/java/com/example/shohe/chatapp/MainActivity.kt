package com.example.shohe.chatapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private val listView: ListView by lazy { findViewById(R.id.listView) as ListView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.checkUserStatus()
        this.initListView()
        this.initSendButton()
        this.getMessages()
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


    // press send button
    private fun initSendButton() {
        sendButton.setOnClickListener {
            this.getUserName(onSuccess = {
                this.sendMessage(it)
            },onError = {})

        }
    }


    private fun getMessageRef() : DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        return database.getReference("chat")
    }


    // send message
    private fun sendMessage(name: String) {
        val id: String = getSharedPreferences("USER", Context.MODE_PRIVATE).getString("ID", "")
        val text: String = messageTextView.text.toString()
        val message: Message = Message(id, name, text)
        this.getMessageRef().push().setValue(message).addOnSuccessListener {
            this.getMessages()
        }
    }

    // get message
    private fun getMessages() {
        this.getMessageRef().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot?) {
                if (data != null) {
                    val adapter: MessageAdapter = MessageAdapter(applicationContext)
                    val ms: MutableList<Message> = mutableListOf()
                    for (ds in data.children) {

                        val message: Message = Message(
                                (ds.child("id").value as String),
                                (ds.child("name").value as String),
                                (ds.child("text").value as String)
                                )
                        ms.add(message)
                    }
                    adapter.messages = ms
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError?) {
                Log.w("getMessages()", "Error onCancelled: ${error}")
            }
        })
    }

    private fun getUserName(onSuccess: (String) -> Unit, onError: (Exception?) -> Unit) {
        val id: String = getSharedPreferences("USER", Context.MODE_PRIVATE).getString("ID", "")
        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
        database.collection("user").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (data: DocumentSnapshot in it.getResult()) {
                    if (data.id == id) {
                        val name: String? = data.get("name") as? String
                        if (name != null) { onSuccess(name) }
                    }
                }
            } else {
                Log.w("getUser()", "Error getting documents: ${it.getException()}")
            }
        }
    }

}
