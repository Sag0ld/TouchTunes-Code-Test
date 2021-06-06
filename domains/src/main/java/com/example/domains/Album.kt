package com.example.domains

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import java.text.SimpleDateFormat
import java.util.*

@Keep
class Album(
    val name: String,
    val artworkUrl: String,
    val releaseDate: Date,
    val genre: String,
    val price: Float,
    val currency: String,
    val copyright: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readSerializable() as? Date ?: Date(),
        parcel.readString().orEmpty(),
        parcel.readFloat(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(artworkUrl)
        parcel.writeSerializable(releaseDate)
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