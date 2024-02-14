package com.example.earthquakelist.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geometry (
    val coordinates: List<Double>

):Parcelable{
    fun getX():Double{
        return coordinates[0]
    }
    fun getY():Double{
        return coordinates[1]
    }
}