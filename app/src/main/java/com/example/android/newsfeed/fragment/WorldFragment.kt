package com.example.android.newsfeed.fragment

import com.example.android.newsfeed.NewsPreferences
import com.example.android.newsfeed.R

class WorldFragment : BaseArticlesFragment() {
    override fun fetchData(url: String) {
        val uri = NewsPreferences.getPreferredUrl(requireContext(), getString(R.string.world))
        super.fetchData(uri.toString())
    }
}
