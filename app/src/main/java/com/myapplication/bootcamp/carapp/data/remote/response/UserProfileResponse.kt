package com.myapplication.bootcamp.carapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @Expose
    @SerializedName("created_at")
    var created_at: String,

    @Expose
    @SerializedName("display_name")
    var display_name: String,

    @Expose
    @SerializedName("email")
    var email: String
)
