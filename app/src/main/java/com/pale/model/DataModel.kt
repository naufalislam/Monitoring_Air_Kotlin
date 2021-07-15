package com.pale.model

class DataModel(val data : List<Data>) {
    data class Data(
            val id: String?,
            val kolam: String?,
            val ph: String?,
            val suhu1: String?,
            val suhu2: String?,
            val suhu3: String?,
            val suhu4: String?
    )

//    "id": 171,
//    "kolam": 179102902,
//    "ph": 6.89,
//    "suhu1": 29.88,
//    "suhu2": 29.31,
//    "suhu3": 30.38,
//    "suhu4": 30.38,
}