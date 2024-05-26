package com.example.android.newsfeed.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.android.newsfeed.EmptyRecyclerView
import com.example.android.newsfeed.News
import com.example.android.newsfeed.NewsPreferences
import com.example.android.newsfeed.R
import com.example.android.newsfeed.adapter.NewsAdapter
import com.example.android.newsfeed.models.GuardianResult
import com.example.android.newsfeed.utils.Constants
import com.example.android.newsfeed.viewmodel.NewsViewModel
import com.example.android.newsfeed.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

open class BaseArticlesFragment : Fragment() {
    private lateinit var mAdapter: NewsAdapter
    private lateinit var mEmptyStateTextView: TextView
    private lateinit var mLoadingIndicator: View
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    val viewModel: NewsViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val mRecyclerView = rootView.findViewById<EmptyRecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(activity)
        mRecyclerView.setHasFixedSize(true)

        mRecyclerView.layoutManager = layoutManager

        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh)
        mSwipeRefreshLayout.setColorSchemeColors(
            resources.getColor(R.color.swipe_color_1),
            resources.getColor(R.color.swipe_color_2),
            resources.getColor(R.color.swipe_color_3),
            resources.getColor(R.color.swipe_color_4)
        )

        mSwipeRefreshLayout.setOnRefreshListener {
            Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout")
            Toast.makeText(
                activity, getString(R.string.updated_just_now),
                Toast.LENGTH_SHORT
            ).show()
        }

        mLoadingIndicator = rootView.findViewById(R.id.loading_indicator)
        mEmptyStateTextView = rootView.findViewById(R.id.empty_view)
        mRecyclerView.setEmptyView(mEmptyStateTextView)
        mAdapter = NewsAdapter(requireContext(), ArrayList())
        mRecyclerView.adapter = mAdapter

        initializeServiceHit(isConnected)

        return rootView
    }

    private val isConnected: Boolean
        get() {
            val connectivityManager =
                requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return (networkInfo != null && networkInfo.isConnected)
        }

    private fun initializeServiceHit(isConnected: Boolean) {
        if (isConnected) {
            fetchData()
        } else {
            mLoadingIndicator.visibility = View.GONE
            mEmptyStateTextView.setText(R.string.no_internet_connection)
            mEmptyStateTextView.setCompoundDrawablesWithIntrinsicBounds(
                Constants.DEFAULT_NUMBER,
                R.drawable.ic_network_check, Constants.DEFAULT_NUMBER, Constants.DEFAULT_NUMBER
            )
        }
    }

    open fun fetchData(url: String = "") {
        lifecycleScope.launch {
            val uri = if (url.isBlank()) NewsPreferences.getPreferredUri(requireContext())
                .toString() else url
            viewModel.fetchNewsData(uri)
                .collect {
                    if ("ok" == it.response.status) {
                        updateAdapter(it.response.results)
                        hideLoader()
                    }
                }
        }
    }

    private fun hideLoader() {
        mLoadingIndicator.visibility = View.GONE
    }

    private fun updateAdapter(results: List<GuardianResult>) {
        val list = ArrayList<News>()
        results.forEach { item ->
            list.add(
                News(
                    title = item.webTitle,
                    section = item.sectionName,
                    author = item.tags.firstOrNull { it.type == "contributor" }?.webTitle ?: "",
                    date = item.webPublicationDate,
                    thumbnail = item.fields.thumbnail,
                    url = item.webUrl,
                    trailTextHtml = item.fields.trailText
                )
            )
        }
        mAdapter.clearAll()
        mAdapter.addAll(list)
    }

    companion object {
        private val LOG_TAG: String = BaseArticlesFragment::class.java.name
    }
}
