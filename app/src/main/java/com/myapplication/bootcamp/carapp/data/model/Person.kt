package com.myapplication.bootcamp.carapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Person(

    val authToken: String,

    val key: String,

    val displayName: String

)