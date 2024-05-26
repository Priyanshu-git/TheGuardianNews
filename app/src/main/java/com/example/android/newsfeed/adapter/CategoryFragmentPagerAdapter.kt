package com.example.android.newsfeed.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android.newsfeed.R
import com.example.android.newsfeed.fragment.BusinessFragment
import com.example.android.newsfeed.fragment.CultureFragment
import com.example.android.newsfeed.fragment.EnvironmentFragment
import com.example.android.newsfeed.fragment.FashionFragment
import com.example.android.newsfeed.fragment.HomeFragment
import com.example.android.newsfeed.fragment.ScienceFragment
import com.example.android.newsfeed.fragment.SocietyFragment
import com.example.android.newsfeed.fragment.SportFragment
import com.example.android.newsfeed.fragment.WorldFragment
import com.example.android.newsfeed.utils.Constants

class CategoryFragmentPagerAdapter(private val mContext: Context, fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            Constants.HOME -> HomeFragment()
            Constants.WORLD -> WorldFragment()
            Constants.SCIENCE -> ScienceFragment()
            Constants.SPORT -> SportFragment()
            Constants.ENVIRONMENT -> EnvironmentFragment()
            Constants.SOCIETY -> SocietyFragment()
            Constants.FASHION -> FashionFragment()
            Constants.BUSINESS -> BusinessFragment()
            Constants.CULTURE -> CultureFragment()
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 9
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val titleResId = when (position) {
            Constants.HOME -> R.string.ic_title_home
            Constants.WORLD -> R.string.ic_title_world
            Constants.SCIENCE -> R.string.ic_title_science
            Constants.SPORT -> R.string.ic_title_sport
            Constants.ENVIRONMENT -> R.string.ic_title_environment
            Constants.SOCIETY -> R.string.ic_title_society
            Constants.FASHION -> R.string.ic_title_fashion
            Constants.BUSINESS -> R.string.ic_title_business
            else -> R.string.ic_title_culture
        }
        return mContext.getString(titleResId)
    }
}
