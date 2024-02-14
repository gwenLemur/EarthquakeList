package com.example.earthquakelist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Features(
    val properties: Properties,
    val geometry: Geometry,

): Parcelable