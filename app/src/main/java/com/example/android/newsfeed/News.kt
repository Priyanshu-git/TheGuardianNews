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
package com.example.android.newsfeed

/**
 * Returns the title of the article
 */

/**
 * Returns the section name of the article.
 */

/**
 * Returns the author name of the article.
 */

/**
 * Returns the web publication date of the article.
 */

/**
 * Returns the website URL to find more information about the news.
 */

/**
 * Returns the thumbnail of the article
 */

/**
 * Returns the TrailText of the article with string type Html
 */
/**
 * An [News] object contains information related to a single news.
 */
class News
/**
 * Constructs a new [News] object.
 *
 * @param title is the title of the article
 * @param section is the section name of the article
 * @param author is author name in article
 * @param date is the web publication date of the article
 * @param url is the website URL to find more details about the article
 * @param thumbnail is the thumbnail of the article
 * @param trailText is trail text of the article with string type Html
 */(
    /** Title of the article  */
    val title: String,
    /** Section name of the article */
    val section: String,
    /** Author name in the article  */
    val author: String,
    /** Web publication date of the article  */
    val date: String,
    /** Website URL of the article  */
    val url: String,
    /** Thumbnail of the article  */
    val thumbnail: String,
    /** TrailText of the article with string type Html  */
    val trailTextHtml: String
)
