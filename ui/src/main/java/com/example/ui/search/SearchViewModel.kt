package com.example.ui.search

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.domains.Album
import com.example.domains.Resource
import com.example.services.SearchService
import com.example.services.NetworkHelper
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchService: SearchService,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val results = MutableLiveData<Resource<List<Album>?>>(
        Resource.success(emptyList())
    )

    private var latestTerm: CharSequence = ""

    fun onQueryTextListener(view: SearchView) = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            search(query.orEmpty())
            view.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

    fun onRefreshListener() = SwipeRefreshLayout.OnRefreshListener {
        search(latestTerm)
    }

    private fun search(term: CharSequence) {
        if (networkHelper.isNetworkConnected()) {
            latestTerm = term
            results.value = Resource.loading(null)

            viewModelScope.launch {
                results.postValue(searchService.searchForAlbums(term).value)
            }
        } else {
            results.value = Resource.error("No internet connection")
        }
    }
}