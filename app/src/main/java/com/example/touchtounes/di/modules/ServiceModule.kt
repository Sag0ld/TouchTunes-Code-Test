package com.example.touchtounes.di.modules

import com.example.services.SearchService
import org.koin.dsl.module

val serviceModule = module {
    single { SearchService(searchRepository = get()) }
}