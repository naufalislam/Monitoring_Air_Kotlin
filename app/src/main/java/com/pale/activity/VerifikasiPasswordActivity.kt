package com.pale.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pale.R
import com.pale.data.LupaPasswordResponse
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_lupa_password.*
import kotlinx.android.synthetic.main.activity_verifikasi_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifikasiPasswordActivity : AppCompatActivity() {
    lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi_password)
        email = SharedPrefManager.getInstance(applicationContext).dataTemp.toString()
        btn_update.setOnClickListener {
            if (txt_token.text.isEmpty()) {
                txt_token.error = "isi Token"
                txt_token.requestFocus()
                return@setOnClickListener
            }
            if (txt_passwordbaru.text.isEmpty()) {
                txt_passwordbaru.error = "isi Password Baru"
                txt_passwordbaru.requestFocus()
                return@setOnClickListener
            }
            if (txt_ulangipasswordbaru.text.isEmpty()) {
                txt_ulangipasswordbaru.error = "isi Konfirmasi Passsword Baru"
                txt_ulangipasswordbaru.requestFocus()
                return@setOnClickListener
            }
            if(txt_passwordbaru.text.toString().length <= 7 ){
                txt_passwordbaru.error = "password harus 8 karakter atau lebih "
                txt_passwordbaru.requestFocus()
                return@setOnClickListener
            }
            if(txt_ulangipasswordbaru.text.toString().trim() != txt_passwordbaru.text.toString().trim()){
                txt_ulangipasswordbaru.error = "isi Konfirmasi Passsword Baru tidak sesuai"
                txt_ulangipasswordbaru.requestFocus()
                return@setOnClickListener
            }

            updatePassword()
        }
    }

    private fun updatePassword() {
        ApiService.instance.resetpassword(
            email,
            txt_token.text.toString(),
            txt_passwordbaru.text.toString(),
            txt_ulangipasswordbaru.text.toString())
            .enqueue(object : Callback<LupaPasswordResponse> {
                override fun onFailure(call: Call<LupaPasswordResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<LupaPasswordResponse>, response: Response<LupaPasswordResponse>) {
                    val t = response.body()!!
                    if (response.isSuccessful) {
//                    SharedPrefManager.getInstance(applicationContext).saveDataTemp(edtPassword.text.toString())
//
                        val intent = Intent (applicationContext,LoginActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                }


            })
    }
}