package com.myapplication.bootcamp.carapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VehicleResponse(
    @Expose
    @SerializedName("lat")
    var lat: Double,

    @Expose
    @SerializedName("lng")
    var lng: Double,

    @Expose
    @SerializedName("license_plate_number")
    var license_plate_number: String,

    @Expose
    @SerializedName("vehicle_type")
    var vehicle_type: String
)
