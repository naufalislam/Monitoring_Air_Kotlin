package com.pale.data

import com.google.gson.annotations.SerializedName

class JumlahAlatResponse (
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: Int
)