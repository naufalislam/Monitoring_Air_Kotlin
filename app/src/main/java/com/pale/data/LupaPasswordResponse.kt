package com.pale.data

import com.google.gson.annotations.SerializedName

class LupaPasswordResponse (
    @SerializedName("status") val status : Boolean,
    @SerializedName("pesan") val message : String
    )