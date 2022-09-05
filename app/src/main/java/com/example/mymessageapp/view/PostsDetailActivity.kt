package com.example.mymessageapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymessageapp.databinding.PostsDetailActivityBinding

class PostsDetailActivity : AppCompatActivity() {

    private lateinit var detailActivityBinding : PostsDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = PostsDetailActivityBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
    }
}