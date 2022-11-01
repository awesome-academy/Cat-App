package com.example.catapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.catapp.utils.ONE
import com.example.catapp.utils.THREE
import com.example.catapp.utils.TWO
import com.example.catapp.utils.ZERO
import com.example.catapp.view.homescreen.homefragments.BreedFragment
import com.example.catapp.view.homescreen.homefragments.FavouriteFragment
import com.example.catapp.view.homescreen.homefragments.ImageFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return THREE
    }

    override fun createFragment(position: Int): Fragment {
        var currentFragment: Fragment = ImageFragment()
        when (position) {
            ZERO -> {
                currentFragment = BreedFragment()
            }
            ONE -> {
                currentFragment = ImageFragment()
            }
            TWO -> {
                currentFragment = FavouriteFragment()
            }
        }
        return currentFragment
    }
}
