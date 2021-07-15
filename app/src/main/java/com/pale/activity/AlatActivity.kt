package com.pale.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.pale.R
import com.pale.model.DataModel
import com.pale.network.ApiService
import kotlinx.android.synthetic.main.activity_alat.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlatActivity : AppCompatActivity() {

    private var id : Int = 0
    private var id_kolam : Int = 0
    private var pemilik : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alat)

        id = intent.getIntExtra("intent_id",0)
        id_kolam = intent.getIntExtra("intent_kolam",0)
        val nama = intent.getStringExtra("intent_nama")

        val tvId : TextView = findViewById(R.id.tvId)
        val tvNama : TextView = findViewById(R.id.tvNama)
        val tvpH : TextView = findViewById(R.id.tvPh)
        val tvSuhu1 : TextView = findViewById(R.id.tvSuhu1)
        val tvSuhu2 : TextView = findViewById(R.id.tvSuhu2)
        val tvSuhu3 : TextView = findViewById(R.id.tvSuhu3)
        val tvSuhu4 : TextView = findViewById(R.id.tvSuhu4)



    }

    override fun onStart() {
        super.onStart()

            getDataSensor(id_kolam)


    }



    private fun getDataSensor(id : Int) {
       ApiService.instance.Terkini(id)
               .enqueue(object  : Callback<DataModel>{
                   override fun onFailure(call: Call<DataModel>, t: Throwable) {
                       Log.e("DataSensor", t.toString())
                   }
                   override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                       if (response.isSuccessful){
                           val ListData = response.body()!!.data
                           ListData.forEach {
                               Log.e("Device", "nama_kolam ${it.kolam}")
                               tvId.setText("${it.id}")
                               tvNama.setText("${it.kolam}")
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