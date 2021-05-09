package com.myapplication.bootcamp.carapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("authentication_token")
    var authentication_token: String,

    @Expose
    @SerializedName("person")
    var person: Person

){
    data class Person(
        @Expose
        @SerializedName("key")
        var key: String,

        @Expose
        @SerializedName("display_name")
        var display_name: String
    )
}