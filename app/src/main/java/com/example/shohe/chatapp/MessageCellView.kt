package com.example.shohe.chatapp

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class MessageCellView : FrameLayout {

    val imageView: ImageView by lazy { findViewById(R.id.userImageView) as ImageView }
    val nameLabel: TextView by lazy { findViewById(R.id.userName) as TextView }
    val messageLabel: TextView by lazy { findViewById(R.id.messageLabel) as TextView }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)



    fun setMessage(message: Message) {
        nameLabel.text = message.name
        messageLabel.text = message.text
    }
}