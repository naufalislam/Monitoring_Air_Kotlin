package com.pale.adapter

import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.pale.R
import com.pale.activity.AlatActivity
import com.pale.data.TambahEditHapusAlatResponse
import com.pale.fragment.DeviceFragment
import com.pale.fragment.TambahAlatFragment
import com.pale.fragment.UpdateAlatFragment
import com.pale.model.AlatModel
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AlatAdapter(val alat : ArrayList<AlatModel.Data>)
    :RecyclerView.Adapter<AlatAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
          LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_alat, parent, false)
    )

    override fun getItemCount()= alat.size

    override fun onBindViewHolder(holder: AlatAdapter.ViewHolder, position: Int) {

        val data = alat[position]
        val idData_alat = data.id
        val idData_kolam = data.id_kolam
        val namaData_kolam = data.nama_kolam
        val status = data.status


        holder.textIdAlat.text = data.id_kolam
        holder.textNamaAlat.text = data.nama_kolam
        if (status == "1"){
            holder.textStatus.text ="Aktif"
            holder.textStatus.setTextColor(Color.parseColor("#00ff00"))
        }
        else{
            holder.textStatus.text = "Berhenti"
            holder.textStatus.setTextColor(Color.parseColor("#ff0000"))
        }
        holder.textNamaAlat.setOnClickListener {
//            listener.onClick( data )
            detailalat(holder.textNamaAlat, idData_alat.toString().toInt(),idData_kolam.toString().toInt(),namaData_kolam.toString())
        }
        holder.ImageViewEdit.setOnClickListener {
//            listener.onClick(data)
            editalat(holder.ImageViewEdit, idData_alat.toString().toInt(),idData_kolam.toString().toInt(), namaData_kolam.toString())
//            Log.e("id", idData_alat.toString())
//            Log.e("idAlat", idData_kolam.toString().toInt().toString())
        }
        holder.ImageViewHapus.setOnClickListener {
            Log.e("idAlat", idData_kolam.toString())
            hapusAlat(holder.ImageViewHapus,data.nama_kolam.toString(), idData_alat.toString().toInt())
        }

    }


    private fun hapusAlat(view: View,Nama :String, idData: Int){
        val activity = view.context as AppCompatActivity
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle("Hapus Data")
        alertDialogBuilder.setMessage("Apakah Yakin Menghapus Alat ${Nama}")
        alertDialogBuilder.setPositiveButton("Ya"){
            dialog, which -> hapusData(view, idData)
        }
        alertDialogBuilder.setNegativeButton("Tidak"){
            dialog, which -> dialog.dismiss()
        }
        
        alertDialogBuilder.show()

    }

    private fun hapusData(view: View, idData: Int) {
        val activity = view.context as AppCompatActivity
//        Toast.makeText(activity, idData.toString(), Toast.LENGTH_SHORT).show()

        ApiService.instance.hapusAlat(idData)
                .enqueue(object : Callback<TambahEditHapusAlatResponse> {
                    override fun onFailure(call: Call<TambahEditHapusAlatResponse>, t: Throwable) {
                        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<TambahEditHapusAlatResponse>, response: Response<TambahEditHapusAlatResponse>) {
//                        Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()
                        if (response.isSuccessful) {
                            kembaliDevicemanager(view)
                            Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()
                        }
                    }


                })
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val textIdAlat = view.findViewById<TextView>(R.id.txt_id_alat)
        val textNamaAlat = view.findViewById<TextView>(R.id.txt_nama_alat)
        val textStatus = view.findViewById<TextView>(R.id.status)
        val ImageViewEdit = view.findViewById<ImageView>(R.id.ivEdit)
        val ImageViewHapus =  view.findViewById<ImageView>(R.id.ivHapus)
    }

    public fun setData(data: List<AlatModel.Data>){
        alat.clear()
        alat.addAll(data)
        notifyDataSetChanged()
    }
    fun editalat(view: View, idData: Int,idAlat : Int, namaData : String) {

        val activity = view.context as AppCompatActivity
        SharedPrefManager.getInstance(activity).saveId(idData,idAlat, namaData)
        val myFragment: Fragment = UpdateAlatFragment()
        activity.supportFragmentManager.beginTransaction().replace(R.id.fl_container, myFragment).addToBackStack(null).commit()

    }
    fun detailalat(view: View, idData: Int,idAlat: Int, namaData: String) {
        val activity = view.context as AppCompatActivity
        SharedPrefManager.getInstance(activity).saveId(idData,idAlat,namaData)
//        val myFragment: Fragment = UpdateAlatFragment()
//        activity.supportFragmentManager.beginTransaction().replace(R.id.fl_container, myFragment).addToBackStack(null).commit()
        val intent = Intent(activity, AlatActivity::class.java)
                activity.startActivity(intent)

    }
    interface OnAdapterListener{
        fun onClick(data : AlatModel.Data)

    }

    fun kembaliDevicemanager(view: View){
        val activity = view.context as AppCompatActivity
        val fragment: Fragment = DeviceFragment()
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        fragmentManager.popBackStack()
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container, fragment)
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }
}


