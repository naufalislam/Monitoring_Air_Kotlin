package com.pale.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.pale.R
import com.pale.activity.MainActivity
import com.pale.data.TambahEditHapusAlatResponse
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import kotlinx.android.synthetic.main.dialog_update_alat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateAlatFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.dialog_update_alat, container, false)

        var id = SharedPrefManager.getInstance(requireContext()).id
        var nama = SharedPrefManager.getInstance(requireContext()).nama


        val txtId: EditText = view.findViewById(R.id.txtUpdateId)as EditText
        val txtNama: EditText = view.findViewById(R.id.txtUpdateNama)as EditText
        val btnupdate : Button = view.findViewById(R.id.btnUpdate)

        txtId.setText(id.toString())
        txtNama.setText(nama)

//        val alat = txtUpdateId.text
//        val edtNama = txtUpdateNama.text.toString().trim()
        Log.e("id_kolam", id.toString())
        Log.e("nama_kolam",nama.toString())



        btnupdate.setOnClickListener {

            if(txtUpdateId.text.isEmpty()){
                txtUpdateId.error = "Id tidak boleh kosong"
                txtUpdateId.requestFocus()
                return@setOnClickListener
            }
            if(txtUpdateNama.text.isEmpty()){
                txtUpdateNama.error = "Nama tidak boleh kosong"
                txtUpdateNama.requestFocus()
                return@setOnClickListener
            }


            ApiService.instance.Update( id,txtUpdateId.text, txtNama.text.toString())
                    .enqueue(object : Callback<TambahEditHapusAlatResponse> {
                        override fun onFailure(call: Call<TambahEditHapusAlatResponse>, t: Throwable) {
                            Toast.makeText(getActivity(), t.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<TambahEditHapusAlatResponse>, response: Response<TambahEditHapusAlatResponse>) {
                            if (response.isSuccessful){
                                val intent = Intent(getActivity(), MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                Toast.makeText(getActivity(), response.body()?.message, Toast.LENGTH_LONG).show()
                            }else{
                                android.widget.Toast.makeText(getActivity(), response.errorBody().toString(), android.widget.Toast.LENGTH_LONG).show()
                            }
                        }


                    })
        }




        return view

    }


}