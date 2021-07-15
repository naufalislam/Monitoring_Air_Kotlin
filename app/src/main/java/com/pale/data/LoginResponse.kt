package com.pale.data

import com.google.gson.annotations.SerializedName

class LoginResponse (
    @SerializedName("pesan") val message : String,
    @SerializedName("status") val status : Boolean,
    @SerializedName("dataLogin") val data : LoginData
)