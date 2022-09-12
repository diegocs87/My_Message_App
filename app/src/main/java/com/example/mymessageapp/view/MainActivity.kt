package com.example.mymessageapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymessageapp.R
import com.example.mymessageapp.databinding.ActivityMainBinding
import com.example.mymessageapp.view.adapters.MainPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val mainPagerAdapter by lazy { MainPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.mainPager.adapter = mainPagerAdapter
        setTabLayoutMediator()
    }

    private fun setTabLayoutMediator(){
        TabLayoutMediator(mainBinding.mainTabLayout, mainBinding.mainPager) { tabId, position ->
            setMainTabsFeatures(tabId,position)
        }.attach()
    }

    private fun setMainTabsFeatures(tabId: TabLayout.Tab, position:Int) {
        when (position) {
            0 -> {
                tabId.text = "All"
                tabId.setIcon(R.drawable.ic_all_message_tab)
            }
            1 -> {
                tabId.text = "Favorites"
                tabId.setIcon(R.drawable.ic_fav_tab)
            }
        }
    }
}