package com.example.datasources.dtos

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("resultCount")
    val resultCount: Long,
    @SerializedName("results")
    val results: List<ItunesAlbum>
)