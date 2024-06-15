package com.example.android.newsfeed.fragment

import com.example.android.newsfeed.NewsPreferences
import com.example.android.newsfeed.R

class CultureFragment : BaseArticlesFragment() {
    override fun fetchData(url: String) {
        val uri = NewsPreferences.getPreferredUrl(requireContext(), getString(R.string.culture))
        super.fetchData(uri.toString())
    }
}
