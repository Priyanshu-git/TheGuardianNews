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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
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

/**
 * The BaseArticlesFragment is a [Fragment] subclass that implements the LoaderManager.LoaderCallbacks
 * interface in order for Fragment to be a client that interacts with the LoaderManager. It is
 * base class that is responsible for displaying a set of articles, regardless of type.
 */
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

        // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
        mSwipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout")
            // restart the loader
//                initiateRefresh();
            Toast.makeText(
                activity, getString(R.string.updated_just_now),
                Toast.LENGTH_SHORT
            ).show()
        })

        // Find the loading indicator from the layout
        mLoadingIndicator = rootView.findViewById(R.id.loading_indicator)

        // Find the empty view from the layout and set it on the new recycler view
        mEmptyStateTextView = rootView.findViewById(R.id.empty_view)
        mRecyclerView.setEmptyView(mEmptyStateTextView)

        // Create a new adapter that takes an empty list of news as input
        mAdapter = NewsAdapter(activity, ArrayList())

        // Set the adapter on the {@link recyclerView}
        mRecyclerView.adapter = mAdapter

        // Check for network connectivity and initialize the loader
        initializeServiceHit(isConnected)

        return rootView
    }

    private val isConnected: Boolean
        get() {
            // Get a reference to the ConnectivityManager to check state of network connectivity
            val connectivityManager =
                requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            // Get details on the currently active default data network
            val networkInfo = connectivityManager.activeNetworkInfo

            return (networkInfo != null && networkInfo.isConnected)
        }

    private fun initializeServiceHit(isConnected: Boolean) {
        if (isConnected) {
            fetchData()
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mLoadingIndicator.visibility = View.GONE
            // Update empty state with no connection error message and image
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
