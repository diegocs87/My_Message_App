package com.example.mymessageapp.view.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymessageapp.view.fragments.AllPostsFragment
import com.example.mymessageapp.view.fragments.FavoritesFragment

class MainPagerAdapter (fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragmentAll = AllPostsFragment()
        val fragmentFavorites = FavoritesFragment()
        when (position) {
            0 -> return fragmentAll

            1-> return fragmentFavorites

            else -> return fragmentAll
        }
    }
}