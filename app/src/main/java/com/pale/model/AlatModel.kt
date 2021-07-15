package com.pale.model

data class AlatModel (val data : List<Data>){
    data class Data(
        val id: String?,
        val id_kolam: String?,
        val nama_kolam: String?,
        val pemilik: String?
    )
}