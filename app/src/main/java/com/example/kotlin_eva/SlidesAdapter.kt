package com.example.kotlin_eva

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlidesAdapter(activity: MainActivity, private val arrayList: ArrayList<Slide>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        return SlideFragment.newInstance(arrayList[position].image, arrayList[position].header)
    }


}