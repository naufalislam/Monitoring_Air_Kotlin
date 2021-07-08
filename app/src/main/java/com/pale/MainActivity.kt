package com.pale

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pale.fragment.AccountFragment
import com.pale.fragment.DeviceFragment
import com.pale.fragment.HomeFragment


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       Set Home Fragment menjadi default
        loadFragment(HomeFragment())
//        inisialisasi BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
//        listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

//    untuk load fragment yang sesuai
    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit()
            return true
        }
        return false
    }

//    method listener untuk logika pemilihan
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when(item.itemId){
            R.id.home_menu -> fragment = HomeFragment()
            R.id.device_menu -> fragment = DeviceFragment()
            R.id.account_menu -> fragment = AccountFragment()
        }
        return loadFragment(fragment)
    }


    override fun onPointerCaptureChanged(hasCapture: Boolean) {}

}