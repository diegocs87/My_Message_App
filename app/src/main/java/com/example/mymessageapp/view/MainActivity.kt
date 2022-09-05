package com.example.mymessageapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymessageapp.databinding.ActivityMainBinding
import com.example.mymessageapp.view.adapters.MainPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val mainPagerAdapter by lazy { MainPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.mainPager.adapter = mainPagerAdapter
    }
}