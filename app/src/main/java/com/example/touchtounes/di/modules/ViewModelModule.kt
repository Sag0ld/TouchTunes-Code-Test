package com.example.touchtounes.di.modules

import com.example.ui.search.SearchViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModule = module {
    viewModel {
        SearchViewModel(
            searchService = get(),
            networkHelper = get()
        )
    }
}

