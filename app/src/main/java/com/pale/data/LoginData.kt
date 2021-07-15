package com.pale.data

import com.google.gson.annotations.SerializedName

class LoginData(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("email") val email: String
)