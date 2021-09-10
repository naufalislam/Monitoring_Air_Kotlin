package com.pale.activity


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pale.R
import com.pale.notifikasi.BaseAplication
import kotlinx.android.synthetic.main.activity_notifikasi.*
import okhttp3.internal.notify


class NotifikasiActivity : AppCompatActivity() {
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifikasi)

        notificationManager = NotificationManagerCompat.from(this)


        btnkirim.setOnClickListener {
            val judul = txtJudul.text.toString()
            val pesan = txtpesan.text.toString()
            val builder = NotificationCompat.Builder(this, BaseAplication.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(judul)
                .setContentText(pesan)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ERROR)

            val notification = builder.build()
            notificationManager.notify(1,notification)

        }

        btnkirim2.setOnClickListener {
            val judul = txtJudul.text.toString()
            val pesan = txtpesan.text.toString()
            val builder = NotificationCompat.Builder(this, BaseAplication.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(judul)
                .setContentText(pesan)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)

            val notification = builder.build()
            notificationManager.notify(2,notification)

        }
    }


    }
