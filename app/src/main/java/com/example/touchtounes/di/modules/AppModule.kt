package com.example.touchtounes.di.modules
import android.content.Context
import com.example.datasources.ItunesClient
import com.example.datasources.ItunesClientHelper
import com.example.datasources.ItunesClientService
import com.example.services.NetworkHelper
import com.example.touchtounes.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideRetrofit(baseUrl: String): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

private fun provideItunesService(retrofit: Retrofit): ItunesClientService =
    retrofit.create(ItunesClientService::class.java)

val appModule = module {
    single { provideRetrofit(BuildConfig.ITUNES_BASE_URL) }
    single { provideItunesService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ItunesClientHelper> {
        return@single ItunesClient(get())
    }
}