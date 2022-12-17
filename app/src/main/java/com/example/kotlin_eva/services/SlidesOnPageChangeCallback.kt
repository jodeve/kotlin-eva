package com.example.kotlin_eva.services

import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin_eva.activities.OnBoardingActivity

object SlidesOnPageChangeCallback {

    fun getCallback(onBoardingActivity: OnBoardingActivity): ViewPager2.OnPageChangeCallback {
      return object  : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                onBoardingActivity.currentPos = position
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 2) onBoardingActivity.navigateToSignUp()

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        }
    }

}