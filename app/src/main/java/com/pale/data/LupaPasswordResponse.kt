package com.pale.data

import com.google.gson.annotations.SerializedName

class LupaPasswordResponse (
    @SerializedName("message") val message : String,
    @SerializedName("status") val status : Boolean
    )