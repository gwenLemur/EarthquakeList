package com.example.earthquakelist.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeatureCollection(
    val features: List<Features>,
    val metadata: Metadata
):Parcelable