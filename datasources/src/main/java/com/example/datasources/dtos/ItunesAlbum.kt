package com.example.datasources.dtos

import com.google.gson.annotations.SerializedName

data class ItunesAlbum(
    @SerializedName("wrapperType")
    val wrapperType: String,
    @SerializedName("artistId")
    val artistId: Long,
    @SerializedName("collectionId")
    val collectionId: Long,
    @SerializedName("amgArtistId")
    val amgArtistId: Long,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String,
    @SerializedName("collectionPrice")
    val collectionPrice: Float,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String,
    @SerializedName("trackCount")
    val trackCount: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String
)