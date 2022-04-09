package com.example.myapplication.modul

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produk (
    var id : Int? = null,
    var name : String? = null,
    var price : Int? = null,
    var image : String? = null,
    var selectedQuantity : Int? = null
) :Parcelable