package com.example.movielibrary.model

import android.os.Parcel
import android.os.Parcelable

data class Film(
    val filmName: String?,
    val imagePoster: String?,
    val rating: Float,
    val genre: String?,
    val year: Int,
    val description: String?,
    val budget: String?,) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(filmName)
        parcel.writeString(imagePoster)
        parcel.writeFloat(rating)
        parcel.writeString(genre)
        parcel.writeInt(year)
        parcel.writeString(description)
        parcel.writeString(budget)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}
