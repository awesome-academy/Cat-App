package com.example.catapp.view.homescreen

import android.os.Bundle
import com.example.catapp.databinding.ActivityHomeBinding
import com.example.catapp.utils.*
import com.example.catapp.utils.base.BaseActivity
import com.example.catapp.view.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    var userApi: String? = null
    private val viewPagerAdapter by lazy { ViewPagerAdapter(this@HomeActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        userApi = intent.getStringExtra(USER_API)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = ONE
        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            when (position) {
                ZERO -> {
                    tab.text = BREEDS
                }
                ONE -> {
                    tab.text = IMAGES
                }
                TWO -> {
                    tab.text = FAVOURITES
                }
            }
        }.attach()
    }
}
