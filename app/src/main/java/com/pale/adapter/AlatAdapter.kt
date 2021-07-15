package com.pale.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pale.R
import com.pale.model.AlatModel

class AlatAdapter(val alat : ArrayList<AlatModel.Data>, private val listener: OnAdapterListener)
    :RecyclerView.Adapter<AlatAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
          LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_alat, parent, false)
    )

    override fun getItemCount()= alat.size

    override fun onBindViewHolder(holder: AlatAdapter.ViewHolder, position: Int) {
        val data = alat[position]
        holder.textIdAlat.text = data.id
        holder.textNamaAlat.text = data.nama_kolam
        holder.textNamaAlat.setOnClickListener {
            listener.onClick( data )
        }
        holder.ImageViewEdit.setOnClickListener {
            listener.onClick(data)
        }
        holder.ImageViewHapus.setOnClickListener {
            listener.onClick(data)
        }

    }


    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val textIdAlat = view.findViewById<TextView>(R.id.txt_id_alat)
        val textNamaAlat = view.findViewById<TextView>(R.id.txt_nama_alat)
        val ImageViewEdit = view.findViewById<ImageView>(R.id.ivEdit)
        val ImageViewHapus = view.findViewById<ImageView>(R.id.ivHapus)


    }

    public fun setData(data: List<AlatModel.Data>){
        alat.clear()
        alat.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(data : AlatModel.Data)
    }
}