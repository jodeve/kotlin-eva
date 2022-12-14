package com.example.kotlin_eva

import androidx.viewpager2.widget.ViewPager2

object SlidesOnPageChangeCallback {

    fun getCallback(mainActivity: MainActivity): ViewPager2.OnPageChangeCallback {
      return object  : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                mainActivity.currentPos = position
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 2) mainActivity.navigateToSignUp()

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        }
    }

}