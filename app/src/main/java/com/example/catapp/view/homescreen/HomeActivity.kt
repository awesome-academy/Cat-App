package com.example.catapp.view.homescreen

import android.os.Bundle
import com.example.catapp.databinding.ActivityHomeBinding
import com.example.catapp.utils.USER_API
import com.example.catapp.utils.PAGE_ONE
import com.example.catapp.utils.NONE
import com.example.catapp.utils.BREEDS
import com.example.catapp.utils.PAGE_TWO
import com.example.catapp.utils.IMAGES
import com.example.catapp.utils.FAVOURITES
import com.example.catapp.utils.base.BaseActivity
import com.example.catapp.view.adapter.ViewPagerAdapter
import com.example.catapp.view.homescreen.homefragments.BreedFragment
import com.example.catapp.view.homescreen.homefragments.FavouriteFragment
import com.example.catapp.view.homescreen.homefragments.ImageFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private val breedFragment = BreedFragment()
    private val imageFragment = ImageFragment()
    private val favouriteFragment = FavouriteFragment()
    private val fragmentList = listOf(breedFragment, imageFragment, favouriteFragment)
    private val viewPagerAdapter by lazy { ViewPagerAdapter(this@HomeActivity, fragmentList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    companion object {
        var userApi: String? = null
    }

    private fun setup() {
        userApi = intent.getStringExtra(USER_API)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = PAGE_ONE
        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            when (position) {
                NONE -> {
                    tab.text = BREEDS
                }
                PAGE_ONE -> {
                    tab.text = IMAGES
                }
                PAGE_TWO -> {
                    tab.text = FAVOURITES
                }
            }
        }.attach()
    }
}
