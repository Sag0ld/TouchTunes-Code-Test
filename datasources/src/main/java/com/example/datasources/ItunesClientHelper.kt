package com.example.datasources

import com.example.datasources.dtos.SearchResponse
import retrofit2.Response

interface ItunesClientHelper {
    suspend fun searchForAlbums(term: CharSequence): Response<SearchResponse>
}