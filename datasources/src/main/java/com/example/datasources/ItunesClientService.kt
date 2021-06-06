package com.example.datasources

import com.example.datasources.dtos.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesClientService {
    // https://itunes.apple.com/search?term={term}&entity=album
    @GET("/search?entity=album")
    suspend fun searchForAlbums(@Query("term") term: CharSequence): Response<SearchResponse>
}

