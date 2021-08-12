package com.pale.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pale.R
import com.pale.data.JumlahAlatResponse
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Toast.makeText(requireActivity(), id, Toast.LENGTH_LONG).show()
//        Log.e("kerja", id.toString())
        getActivity()?.setTitle("Home")
    }
        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val view = inflater.inflate(R.layout.fragment_home,container,false)

            val jumlahAlat: TextView = view.findViewById(R.id.tVjumlah_alat)
            val id = SharedPrefManager.getInstance(requireContext()).data.id

            getjumlahAlat(id)



            return view
    }

    private fun getjumlahAlat(Id : Int) {
        ApiService.instance.jumlah_alat(Id)
                .enqueue(object : Callback<JumlahAlatResponse> {
                    override fun onFailure(call: Call<JumlahAlatResponse>, t: Throwable) {
                        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<JumlahAlatResponse>, response: Response<JumlahAlatResponse>) {
                        if (response.isSuccessful) {
//                            Log.e("dataAlat", response.body()!!.data.toString())
                            tVjumlah_alat.setText(response.body()!!.data.toString() + " Alat")

                        } else {
                            Toast.makeText(activity, "Gagal Memuat Data", Toast.LENGTH_SHORT).show()
                        }

                    }


                })
    }


}