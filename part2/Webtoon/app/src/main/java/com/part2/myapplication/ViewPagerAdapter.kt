package com.part2.myapplication

import android.R
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private var mainActivity: MainActivity): FragmentStateAdapter(mainActivity) {
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                return WebViewFragment(position)
            }
            1 -> {
                return WebViewFragment(position)
            }
            else -> {
                return WebViewFragment(position)
            }

        }
    }

    override fun getItemCount(): Int {
        return 3
    }

}