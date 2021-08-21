package com.pale.activity

import android.app.AlarmManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.pale.R
import com.pale.model.DataModel
import com.pale.network.ApiService
import com.pale.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_alat.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlatActivity : AppCompatActivity() {

    private var id : Int = 0
    private var id_kolam : Int = 0
    private var pemilik : Int = 0
    private var namaAlat : String = "Alat"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alat)

//        id = intent.getIntExtra("intent_id",0)
        id = SharedPrefManager.getInstance(applicationContext).idAlat
        namaAlat  = SharedPrefManager.getInstance(applicationContext).nama.toString()

        Log.e("id_kolam", id.toString())

        id_kolam = intent.getIntExtra("intent_kolam",0)
        val nama = intent.getStringExtra("intent_nama")

        val tvId : TextView = findViewById(R.id.tvId)
        val tvNama : TextView = findViewById(R.id.tvNama)
        val tvpH : TextView = findViewById(R.id.tvPh)
        val tvSuhu1 : TextView = findViewById(R.id.tvSuhu1)
        val tvSuhu2 : TextView = findViewById(R.id.tvSuhu2)
        val tvSuhu3 : TextView = findViewById(R.id.tvSuhu3)
        val tvSuhu4 : TextView = findViewById(R.id.tvSuhu4)
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                getData()
                mainHandler.postDelayed(this, 100000)
            }
        })




    }

    override fun onStart() {
        super.onStart()

            getDataSensor(id)


    }
    fun getData() {
        id = SharedPrefManager.getInstance(applicationContext).id
        getDataSensor(id)
    }


    private fun getDataSensor(id : Int) {
       ApiService.instance.Terkini(id)
               .enqueue(object : Callback<DataModel> {
                   override fun onFailure(call: Call<DataModel>, t: Throwable) {
                       Log.e("DataSensor", t.toString())
                       Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_SHORT).show()
                   }

                   override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                       if (response.isSuccessful) {
                           val ListData = response.body()!!.data
                           ListData.forEach {
//                               Log.e("Device", "nama_kolam ${it.kolam}")
                               tvId.setText("ID : ${it.kolam}")
                               tvNama.setText("Nama : "+namaAlat)
                               tvPh.setText("${it.ph}")
                               tvSuhu1.setText("${it.suhu1}")
                               tvSuhu2.setText("${it.suhu2}")
                               tvSuhu3.setText("${it.suhu3}")
                               tvSuhu4.setText("${it.suhu4}")
                           }
                       }

                   }


               })
    }
}