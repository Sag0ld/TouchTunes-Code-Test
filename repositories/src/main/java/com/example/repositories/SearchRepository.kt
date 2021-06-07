package com.example.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datasources.ItunesClientHelper
import com.example.datasources.dtos.ItunesAlbum
import com.example.domains.Album
import com.example.domains.Resource
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Exception

class SearchRepository(private val itunesClient: ItunesClientHelper) {

    suspend fun getAlbums(term: CharSequence): LiveData<Resource<List<Album>>> {
        try {
            val response = itunesClient.searchForAlbums(term)
            if (response.isSuccessful) {
                val albums: List<Album>? = response.body()?.results?.map { itunesAlbum ->
                    itunesAlbum.toAlbum()
                }
                return MutableLiveData(ResponseHandler().handleSuccess(albums.orEmpty()))
            }
        } catch (e: Exception) {
            return MutableLiveData(ResponseHandler().handleException(e, emptyList<Album>()))
        }
        return MutableLiveData(null)
    }
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception, data: T): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), data)
            is SocketTimeoutException -> Resource.error("Timeout", data)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), data)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}


private fun ItunesAlbum.toAlbum(): Album {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA)

    return Album(
        name = collectionName,
        artworkUrl = artworkUrl100,
        releaseDate = dateFormat.parse(releaseDate) ?: Date(),
        genre = primaryGenreName,
        price = collectionPrice,
        currency = currency,
        copyright = copyright
    )
}


