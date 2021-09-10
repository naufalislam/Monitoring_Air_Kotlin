package com.pale.notifikasi

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class BaseAplication : Application() {

    companion object{
        const val CHANNEL_1_ID = "channel1"
        const val CHANNEL_2_ID = "channel2"
    }

    override fun onCreate() {
        super.onCreate()
        createNotification()
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel1 = NotificationChannel(
                CHANNEL_1_ID,
                "Channel Satu",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel1.description = "Ini adalah channel satu"


            val channel2 = NotificationChannel(
                CHANNEL_2_ID,
                "Channel Dua",
                NotificationManager.IMPORTANCE_LOW
            )
            channel1.description = "Ini adalah channel dua"

            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel1)
            manager?.createNotificationChannel(channel2)
        }
    }
}