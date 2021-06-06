package com.example.domains

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import java.text.SimpleDateFormat

@Keep
class Album(
    val name: String,
    val artworkUrl: String,
    val releaseDate: String,
    val genre: String,
    val price: Float,
    val currency: String,
    val copyright: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readFloat(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(artworkUrl)
        parcel.writeString(releaseDate)
        parcel.writeString(genre)
        parcel.writeFloat(price)
        parcel.writeString(currency)
        parcel.writeString(copyright)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun formattedPrice(): CharSequence {
        return "$price $currency"
    }

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album {
            return Album(parcel)
        }

        override fun newArray(size: Int): Array<Album?> {
            return arrayOfNulls(size)
        }
    }
}