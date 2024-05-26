/*
 * MIT License
 *
 * Copyright (c) 2018 Soojeong Shin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.example.android.newsfeed.fragment

import android.os.Bundle
import android.util.Log
import androidx.loader.content.Loader
import com.example.android.newsfeed.News
import com.example.android.newsfeed.NewsLoader
import com.example.android.newsfeed.NewsPreferences
import com.example.android.newsfeed.R

/**
 * The SportFragment is a [BaseArticlesFragment] subclass that
 * reuses methods of the parent class by passing the specific type of article to be fetched.
 */
class SportFragment : BaseArticlesFragment() {
    override fun fetchData(url: String) {
        val uri = NewsPreferences.getPreferredUrl(requireContext(), getString(R.string.sport))
        super.fetchData(uri.toString())
    }
}
