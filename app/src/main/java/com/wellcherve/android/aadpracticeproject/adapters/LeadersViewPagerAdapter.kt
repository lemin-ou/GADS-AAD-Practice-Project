package com.wellcherve.android.aadpracticeproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wellcherve.android.aadpracticeproject.LearningLeadersFragment
import com.wellcherve.android.aadpracticeproject.SkillIQLeadersFragment

class LeadersViewPagerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(manager, lifecycle) {
    private val fragments = arrayListOf<Fragment>()

    init {
        fragments.add(LearningLeadersFragment())
        fragments.add(SkillIQLeadersFragment())
    }


    override fun getItemCount() = 2


    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}