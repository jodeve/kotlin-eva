package com.example.kotlin_eva.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlin_eva.activities.OnBoardingActivity
import com.example.kotlin_eva.components.SlideFragment
import com.example.kotlin_eva.models.Slide

class SlidesAdapter(activity: OnBoardingActivity, private val arrayList: ArrayList<Slide>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        return SlideFragment.newInstance(arrayList[position].image, arrayList[position].header)
    }


}