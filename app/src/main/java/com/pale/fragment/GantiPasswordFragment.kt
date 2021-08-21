package com.pale.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.pale.R
import com.pale.data.LupaPasswordResponse
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_verifikasi_password.*
import kotlinx.android.synthetic.main.dialog_tambah_alat.*
import kotlinx.android.synthetic.main.fragment_ganti_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GantiPasswordFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        getActivity()?.setTitle("Home")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ganti_password, container, false)

        val btnUpdate : CardView = view.findViewById(R.id.btn_gantiPassword)

        btnUpdate.setOnClickListener {
            val id = SharedPrefManager.getInstance(requireContext()).data.id
            val passwordlama = txt_password_lama.text.toString().trim()
            val passwordbaru2 = txt_password_baru2.text.toString().trim()

            if (txt_password_lama.text.isEmpty()) {
                txt_password_lama.error = "Isi Password Lama"
                txt_password_lama.requestFocus()
                return@setOnClickListener
            }
            if (txt_password_baru1.text.isEmpty()) {
                txt_password_baru1.error = "Isi Password Baru"
                txt_password_baru1.requestFocus()
                return@setOnClickListener
            }
            if (txt_password_baru2.text.isEmpty()) {
                txt_password_baru2.error = "Isi Password Baru"
                txt_password_baru2.requestFocus()
                return@setOnClickListener
            }
            if(txt_password_baru1.text.toString().trim() != txt_password_baru2.text.toString().trim()){
                txt_password_baru2.error = "isi Konfirmasi Passsword Baru tidak sesuai"
                txt_password_baru2.requestFocus()
                return@setOnClickListener
            }


            ApiService.instance.gantipassword(id,passwordlama,passwordbaru2)
                    .enqueue(object : Callback<LupaPasswordResponse> {
                        override fun onFailure(call: Call<LupaPasswordResponse>, t: Throwable) {
                            Toast.makeText(activity, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<LupaPasswordResponse>, response: Response<LupaPasswordResponse>) {
//                            Log.e("response", response.body()!!.status.toString())
                        if (response.body()!!.status == true) {
                            Toast.makeText(activity, "Password berhasil diganti", Toast.LENGTH_SHORT).show()
                            val fragment: Fragment = AccountFragment()
                            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.fl_container, fragment)
                            fragmentTransaction.commit()
                        } else {
                            Toast.makeText(activity, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                        }


                    })
        }



        return view
    }


}