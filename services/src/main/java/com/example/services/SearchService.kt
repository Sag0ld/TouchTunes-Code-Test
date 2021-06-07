package com.example.services

import androidx.lifecycle.LiveData
import com.example.domains.Album
import com.example.domains.Resource
import com.example.repositories.SearchRepository

class SearchService(private val searchRepository: SearchRepository) {
    suspend fun searchForAlbums(term: CharSequence) : LiveData<Resource<List<Album>>> {
        return searchRepository.getAlbums(term)
    }
}