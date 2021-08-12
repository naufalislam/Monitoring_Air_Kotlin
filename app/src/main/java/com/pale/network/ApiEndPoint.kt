package com.pale.network

import android.text.Editable
import com.pale.data.JumlahAlatResponse
import com.pale.data.LoginResponse
import com.pale.data.LupaPasswordResponse
import com.pale.data.TambahEditHapusAlatResponse
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
    ): Call<TambahEditHapusAlatResponse>

    @FormUrlEncoded
    @PUT("update/{id}")
    fun Update(
            @Path("id") id: Int,
            @Field("alat") alat: Editable,
            @Field("nama") nama: String
    ): Call<TambahEditHapusAlatResponse>

    @DELETE("hapus/{id}")
    fun hapusAlat(
            @Path("id") id:Int
    ): Call<TambahEditHapusAlatResponse>

    @GET("terkini/{id}")
    fun Terkini(
            @Path("id") id:Int,
    ): Call<DataModel>

    @GET("jumlah_alat/{id}")
    fun jumlah_alat(
            @Path("id") id:Int,
    ): Call<JumlahAlatResponse>


    @FormUrlEncoded
    @POST("password/email")
    fun verifikasi(
            @Field("email") email:String
    ): Call<LupaPasswordResponse>

    @FormUrlEncoded
    @POST("password/reset")
    fun resetpassword(
            @Field("email") email:String,
            @Field("token") token:String,
            @Field("password") password:String,
            @Field("password_confirmation") password_confirmation:String
    ): Call<LupaPasswordResponse>
}