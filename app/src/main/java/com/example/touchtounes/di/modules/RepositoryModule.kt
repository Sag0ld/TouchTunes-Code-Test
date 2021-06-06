package com.example.touchtounes.di.modules

import com.example.repositories.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        SearchRepository(get())
    }
}