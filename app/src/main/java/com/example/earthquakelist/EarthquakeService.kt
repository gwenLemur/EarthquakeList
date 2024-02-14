package com.example.earthquakelist
import retrofit2.Call
import com.example.earthquakelist.models.FeatureCollection

import retrofit2.http.GET

interface EarthquakeService {
    //list different endpoints in api to call
    //function returns Call<Type>
    @GET("all_day.geojson")
    fun getEarthquakePastDay():Call<FeatureCollection>
}