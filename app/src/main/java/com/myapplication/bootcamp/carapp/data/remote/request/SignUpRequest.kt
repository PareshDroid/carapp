package com.myapplication.bootcamp.carapp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @Expose
    @SerializedName("display_name")
    var display_name: String,

    @Expose
    @SerializedName("email")
    var email: String,

    @Expose
    @SerializedName("password")
    var password: String
)