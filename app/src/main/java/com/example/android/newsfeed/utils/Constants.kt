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
package com.example.android.newsfeed.utils

/**
 * Store Constants for the NewsFeed app.
 */
object Constants {
    /**
     * Extract the key associated with the JSONObject
     */
    const val JSON_KEY_RESPONSE: String = "response"
    const val JSON_KEY_RESULTS: String = "results"
    const val JSON_KEY_WEB_TITLE: String = "webTitle"
    const val JSON_KEY_SECTION_NAME: String = "sectionName"
    const val JSON_KEY_WEB_PUBLICATION_DATE: String = "webPublicationDate"
    const val JSON_KEY_WEB_URL: String = "webUrl"
    const val JSON_KEY_TAGS: String = "tags"
    const val JSON_KEY_FIELDS: String = "fields"
    const val JSON_KEY_THUMBNAIL: String = "thumbnail"
    const val JSON_KEY_TRAIL_TEXT: String = "trailText"

    /**
     * Read timeout for setting up the HTTP request
     */
    const val READ_TIMEOUT: Int = 10000 /* milliseconds */

    /**
     * Connect timeout for setting up the HTTP request
     */
    const val CONNECT_TIMEOUT: Int = 15000 /* milliseconds */

    /**
     * HTTP response code when the request is successful
     */
    const val SUCCESS_RESPONSE_CODE: Int = 200

    /**
     * Request method type "GET" for reading information from the server
     */
    const val REQUEST_METHOD_GET: String = "GET"

    /**
     * URL for news data from the guardian data set
     */
    const val NEWS_REQUEST_URL: String = "https://content.guardianapis.com/search"

    /**
     * Parameters
     */
    const val QUERY_PARAM: String = "q"
    const val ORDER_BY_PARAM: String = "order-by"
    const val PAGE_SIZE_PARAM: String = "page-size"
    const val ORDER_DATE_PARAM: String = "order-date"
    const val FROM_DATE_PARAM: String = "from-date"
    const val SHOW_FIELDS_PARAM: String = "show-fields"
    const val FORMAT_PARAM: String = "format"
    const val SHOW_TAGS_PARAM: String = "show-tags"
    const val API_KEY_PARAM: String = "api-key"
    const val SECTION_PARAM: String = "section"

    /**
     * The show fields we want our API to return
     */
    const val SHOW_FIELDS: String = "thumbnail,trailText"

    /**
     * The format we want our API to return
     */
    const val FORMAT: String = "json"

    /**
     * The show tags we want our API to return
     */
    const val SHOW_TAGS: String = "contributor"

    /**
     * API Key
     */
    const val API_KEY: String = "test" // Use your API Key when API rate limit exceeded

    /**
     * Default number to set the image on the top of the textView
     */
    const val DEFAULT_NUMBER: Int = 0

    /**
     * Constants value for each fragment
     */
    const val HOME: Int = 0
    const val WORLD: Int = 1
    const val SCIENCE: Int = 2
    const val SPORT: Int = 3
    const val ENVIRONMENT: Int = 4
    const val SOCIETY: Int = 5
    const val FASHION: Int = 6
    const val BUSINESS: Int = 7
    const val CULTURE: Int = 8
}
