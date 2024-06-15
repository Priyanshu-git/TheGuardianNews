package com.example.android.newsfeed.fragment

import com.example.android.newsfeed.NewsPreferences
import com.example.android.newsfeed.R

class BusinessFragment : BaseArticlesFragment() {

    override fun fetchData(url: String) {
        val uri = NewsPreferences.getPreferredUrl(requireContext(), getString(R.string.business))
        super.fetchData(uri.toString())
    }
}
