package com.wellcherve.android.aadpracticeproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wellcherve.android.aadpracticeproject.adapters.LeadersViewPagerAdapter
import com.wellcherve.android.aadpracticeproject.models.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initializeViewPager2()
        gotoSubmitActivity()
    }


    private fun gotoSubmitActivity() {
        submit.setOnClickListener {
            startActivity(Intent(this, SubmitActivity::class.java))
        }
    }

    private fun initializeViewPager2() {
        val tabNames = listOf("Learning Leaders", "Skill IQ Leaders")
        viewPager2.adapter = LeadersViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tab_layout, viewPager2) {tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }


}