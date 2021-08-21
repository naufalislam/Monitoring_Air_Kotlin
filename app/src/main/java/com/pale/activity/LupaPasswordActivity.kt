package com.pale.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pale.R
import com.pale.data.LupaPasswordResponse
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_lupa_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LupaPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupa_password)

        btn_reset.setOnClickListener {
        val email = txt_email.text.toString().trim()

        if(email.isEmpty()){
            txt_username.error = "Email tidak boleh kosong"
            txt_username.requestFocus()
            return@setOnClickListener
        }
//            Toast.makeText(applicationContext, "Berhasil, Silahkan Cek email anda", Toast.LENGTH_SHORT).show()
            ApiService.instance.verifikasi(email)
                .enqueue(object : Callback<LupaPasswordResponse> {
                    override fun onFailure(call: Call<LupaPasswordResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<LupaPasswordResponse>, response: Response<LupaPasswordResponse>) {
                        val t = response.body()!!
                        if (response.isSuccessful) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                            SharedPrefManager.getInstance(applicationContext).saveDataTemp(email.toString())

                            val intent = Intent (applicationContext,VerifikasiPasswordActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                        }
                    }


                })

        }

    }
}