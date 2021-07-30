package com.pale.fragment

import android.app.AlertDialog
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.pale.R
import com.pale.activity.AlatActivity
import com.pale.activity.LoginActivity
import com.pale.adapter.AlatAdapter
import com.pale.model.AlatModel
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeviceFragment :Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivity()?.setTitle("Device Manager")
    }

    private lateinit var alatAdapter : AlatAdapter

    private var idAlat : Int = 0
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_device, container, false)
        val tambah : ImageView = view.findViewById(R.id.bt_tambah)
        idAlat = SharedPrefManager.getInstance(requireContext()).data.id
        var listAlat  = view.findViewById<RecyclerView>(R.id.list_alat)
        alatAdapter = AlatAdapter(arrayListOf())




        listAlat.adapter =alatAdapter


        tambah.setOnClickListener {
            tambahAlat()
        }

        return view
    }





    


    fun tambahAlat(){
        val fragment: Fragment = TambahAlatFragment()
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container, fragment)
        fragmentTransaction.commit()
    }



    override fun onStart() {
        super.onStart()
        getAlat(idAlat)
    }





    private fun getAlat(id: Int) {
        ApiService.instance.Kolam(id)
                .enqueue(object : Callback<AlatModel> {
            override fun onFailure(call: Call<AlatModel>, t: Throwable) {
                Log.e("Device", t.toString())
            }

            override fun onResponse(call: Call<AlatModel>, response: Response<AlatModel>) {
                if (response.isSuccessful) {
                    val ListData = response.body()!!.data
                    alatAdapter.setData(ListData)
//                    ListData.forEach {
//                        Log.e("Device", "nama_kolam ${it.nama_kolam}")
//                    }
                }
            }


        })
    }
}