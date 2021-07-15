package com.pale.network

import android.text.Editable
import com.pale.data.LoginResponse
import com.pale.data.TambahAlatResponse
import com.pale.model.AlatModel
import com.pale.model.DataModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {
    @FormUrlEncoded
    @POST("login")
    fun Login(
            @Field("username") email:String,
            @Field("password") password:String
    ): Call<LoginResponse>

    @GET("kolam/{id}")
    fun Kolam(
        @Path("id") id:Int,
    ) : Call<AlatModel>

    @FormUrlEncoded
    @POST("tambah")
    fun Tambah(
            @Field("id") id:Int,
            @Field("alat") alat: Editable,
            @Field("nama") nama:String
    ): Call<TambahAlatResponse>

    @FormUrlEncoded
    @PUT("update/{idAlat}")
    fun Update(
        @Path("idAlat") idAlat: Int,
        @Field("id") id: Int,
        @Field("alat") alat: Editable,
        @Field("nama") nama: String
    ): Call<TambahAlatResponse>

    @GET("terkini/{id}")
    fun Terkini(
            @Path("id") id:Int,
    ): Call<DataModel>
}