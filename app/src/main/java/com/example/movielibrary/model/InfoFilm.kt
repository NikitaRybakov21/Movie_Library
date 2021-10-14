package com.example.movielibrary.model

import android.os.Parcel
import android.os.Parcelable

class InfoFilm(val filmName: String?, val imagePoster: Int) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(filmName)
        parcel.writeInt(imagePoster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InfoFilm> {
        override fun createFromParcel(parcel: Parcel): InfoFilm {
            return InfoFilm(parcel)
        }

        override fun newArray(size: Int): Array<InfoFilm?> {
            return arrayOfNulls(size)
        }
    }

}