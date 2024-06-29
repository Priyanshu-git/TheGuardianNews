package com.example.android.newsfeed.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.preference.PreferenceManager
import android.text.Html
import android.text.format.DateUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.newsfeed.models.News
import com.example.android.newsfeed.R
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val mContext: Context, private val mNewsList: MutableList<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.news_card_item, parent, false)
    )

    override fun getItemCount() = mNewsList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_card)
        val sectionTextView: TextView = itemView.findViewById(R.id.section_card)
        val authorTextView: TextView = itemView.findViewById(R.id.author_card)
        val dateTextView: TextView = itemView.findViewById(R.id.date_card)
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnail_image_card)
        val shareImageView: ImageView = itemView.findViewById(R.id.share_image_card)
        val trailTextView: TextView = itemView.findViewById(R.id.trail_text_card)
        val cardView: CardView = itemView.findViewById(R.id.card_view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNews = mNewsList[position]

        with(holder) {
            titleTextView.text = currentNews.title
            sectionTextView.text = currentNews.section
            authorTextView.visibility = if (currentNews.author != null) View.VISIBLE else View.GONE
            authorTextView.text = currentNews.author ?: ""
            dateTextView.text = DateUtils.getRelativeTimeSpanString(
                SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'", Locale.ENGLISH)
                    .parse(currentNews.date).time, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS
            )
            trailTextView.text = Html.fromHtml(Html.fromHtml(currentNews.trailTextHtml).toString())
            cardView.setOnClickListener {
                mContext.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.url)))
            }
            thumbnailImageView.visibility = if (currentNews.thumbnail != null) View.VISIBLE else View.GONE
            Glide.with(mContext).load(currentNews.thumbnail).into(thumbnailImageView)
            shareImageView.setOnClickListener { shareData(currentNews) }
            setColorTheme(titleTextView)
            setTextSize(titleTextView)
        }
    }

    private fun setColorTheme(titleTextView: TextView) {
        val colorTheme = sharedPrefs.getString(
            mContext.getString(R.string.settings_color_key),
            mContext.getString(R.string.settings_color_default)
        )
        titleTextView.setBackgroundResource(when (colorTheme) {
            mContext.getString(R.string.settings_color_white_value) -> R.color.white
            mContext.getString(R.string.settings_color_sky_blue_value) -> R.color.nav_bar_start
            mContext.getString(R.string.settings_color_dark_blue_value) -> R.color.color_app_bar_text
            mContext.getString(R.string.settings_color_violet_value) -> R.color.violet
            mContext.getString(R.string.settings_color_light_green_value) -> R.color.light_green
            mContext.getString(R.string.settings_color_green_value) -> R.color.color_section
            else -> R.color.white
        })
        titleTextView.setTextColor(if (colorTheme == mContext.getString(R.string.settings_color_white_value)) Color.BLACK else Color.WHITE)
    }

    private fun setTextSize(titleTextView: TextView) {
        val textSize = sharedPrefs.getString(
            mContext.getString(R.string.settings_text_size_key),
            mContext.getString(R.string.settings_text_size_default)
        )
        val resources = mContext.resources
        titleTextView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            when (textSize) {
                mContext.getString(R.string.settings_text_size_medium_value) -> resources.getDimension(R.dimen.sp22)
                mContext.getString(R.string.settings_text_size_small_value) -> resources.getDimension(R.dimen.sp20)
                mContext.getString(R.string.settings_text_size_large_value) -> resources.getDimension(R.dimen.sp24)
                else -> resources.getDimension(R.dimen.sp22)
            }
        )
    }

    private fun shareData(news: News) {
        val sharingIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "${news.title} : ${news.url}")
        }
        mContext.startActivity(Intent.createChooser(sharingIntent, mContext.getString(R.string.share_article)))
    }

    fun clearAll() {
        mNewsList.clear()
        notifyDataSetChanged()
    }

    fun addAll(newsList: List<News>?) {
        mNewsList.clear()
        newsList?.let { mNewsList.addAll(it) }
        notifyDataSetChanged()
    }
}
