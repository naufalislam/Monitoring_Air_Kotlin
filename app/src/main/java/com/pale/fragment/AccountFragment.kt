package com.pale.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.pale.activity.LoginActivity
import com.pale.R
import com.pale.storage.SharedPrefManager

class AccountFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        val id = SharedPrefManager.getInstance(requireContext()).data.id
        val nama = SharedPrefManager.getInstance(requireContext()).data.name
        val email = SharedPrefManager.getInstance(requireContext()).data.email

        val btnLogout: CardView = view.findViewById(R.id.cvLogout)
        val btnGantiPassword : CardView = view.findViewById(R.id.cvGantiPassword)
        val txtId: TextView = view.findViewById(R.id.Id_user)as TextView
        val txtNama: TextView = view.findViewById(R.id.Nama_user)as TextView
        val txtEmail: TextView = view.findViewById(R.id.Email_user)as TextView

        Log.e("Sharedpref",id.toString())
        Log.e("Sharedpref",nama)

        txtId.setText(id.toString())
        txtNama.setText(nama)
        txtEmail.setText(email)

        btnGantiPassword.setOnClickListener { gantipassword() }

        btnLogout.setOnClickListener { logOut() }

        return view
    }

    private fun gantipassword() {
        val fragment: Fragment = GantiPasswordFragment()
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container, fragment)
        fragmentTransaction.commit()
    }

    private fun logOut() {
        SharedPrefManager.getInstance(requireActivity()).clear()
        val intent = Intent(getActivity(), LoginActivity::class.java)
        getActivity()?.startActivity(intent)
        getActivity()?.finish()
    }



}


