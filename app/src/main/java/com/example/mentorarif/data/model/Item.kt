package com.example.mentorarif.data.model

import android.os.Parcel
import android.os.Parcelable

data class Item (
        var id: String,
        var date: String,
        var price: String,
        var name: String,
        var quantity: String,
        var note: String) : Parcelable {
    constructor(parcel: Parcel) : this(
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(id)
        p0?.writeString(date)
        p0?.writeString(name)
        p0?.writeString(quantity)
        p0?.writeString(price)
        p0?.writeString(note)

    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
        }