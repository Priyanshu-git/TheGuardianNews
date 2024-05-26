package com.example.android.newsfeed.models

data class GuardianResult(
    val id: String,
    val type: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
    val apiUrl: String,
    val fields: GuardianFields,
    val tags: List<GuardianTag>,
    val isHosted: Boolean,
    val pillarId: String,
    val pillarName: String
)