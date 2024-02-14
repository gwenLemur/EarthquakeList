package com.example.earthquakelist.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Metadata (
    val count: Integer,
    val title: String,
    val status: Integer
):Parcelable