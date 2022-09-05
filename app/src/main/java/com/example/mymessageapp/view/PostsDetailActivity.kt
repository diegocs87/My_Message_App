package com.example.mymessageapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymessageapp.databinding.PostsDetailActivityBinding
import com.example.mymessageapp.model.MessageData

class PostsDetailActivity : AppCompatActivity() {

    private lateinit var detailActivityBinding : PostsDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = PostsDetailActivityBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
        val post = intent.getParcelableExtra<MessageData>("data")
        detailActivityBinding.postDescBody.text = post!!.description
        detailActivityBinding.userNameBody.text = post!!.user.name
        detailActivityBinding.userMailBody.text = post!!.user.email
        detailActivityBinding.userPhoneBody.text = post!!.user.phone
        detailActivityBinding.userWebBody.text = post!!.user.webSite
    }
}