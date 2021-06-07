package com.example.datasources

import com.example.datasources.dtos.SearchResponse
import retrofit2.Response

class ItunesClient(private val itunesClientService: ItunesClientService) : ItunesClientHelper {
    override suspend fun searchForAlbums(term: CharSequence): Response<SearchResponse> =
        itunesClientService.searchForAlbums(term)
}