package com.pale.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pale.activity.MainActivity
import com.pale.R
import com.pale.data.TambahEditHapusAlatResponse
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import kotlinx.android.synthetic.main.dialog_tambah_alat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahAlatFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_tambah_alat, container, false)

        val btnSimpan: Button = view.findViewById(R.id.btnSave)



        btnSimpan.setOnClickListener{

//            val edtId: EditText = view.findViewById(R.id.txtInputId)as EditText
//            val edtNama: EditText = view.findViewById(R.id.txtInputNama)as EditText
            val id = SharedPrefManager.getInstance(requireContext()).data.id
            val alat = txtInputId.text
            val edtNama = txtInputNama.text.toString().trim()

            if(alat.isEmpty()){
                txtInputId.error = "Id tidak boleh kosong"
                txtInputId.requestFocus()
                return@setOnClickListener
            }
            if(edtNama.isEmpty()){
                txtInputNama.error = "Nama tidak boleh kosong"
                txtInputNama.requestFocus()
                return@setOnClickListener
            }

            ApiService.instance.Tambah(id, alat, edtNama)
                    .enqueue(object : Callback<TambahEditHapusAlatResponse>{
                        override fun onFailure(call: Call<TambahEditHapusAlatResponse>, t: Throwable) {
                            Toast.makeText(getActivity(), t.message, Toast.LENGTH_LONG).show()
                        }
                        override fun onResponse(call: Call<TambahEditHapusAlatResponse>, response: Response<TambahEditHapusAlatResponse>) {
                            if(response.isSuccessful){
                                val intent = Intent(getActivity(), MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                Toast.makeText(getActivity(), response.body()?.message, Toast.LENGTH_LONG).show()
                            }else{
                                Toast.makeText(getActivity(), response.errorBody().toString(), Toast.LENGTH_LONG).show()
                            }
                        }


                    })


        }
        return view


    }


}