package com.example.kotlin_eva.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.kotlin_eva.R
import com.example.kotlin_eva.activities.LoginActivity
import com.example.kotlin_eva.activities.OnBoardingActivity
import com.example.kotlin_eva.components.OptionsItemView
import com.example.kotlin_eva.interfaces.AuthApiListener
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.services.AuthApi
import com.example.kotlin_eva.services.Navigator
import com.example.kotlin_eva.services.Storage

class OptionsFragment: Fragment(), AuthApiListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_options, container, false)
        val signOut = view.findViewById<OptionsItemView>(R.id.signOut)
        signOut.setOnClickListener {
            onSignOut()
        }
        val deleteAccount = view.findViewById<OptionsItemView>(R.id.deleteAccount)
        deleteAccount.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setCancelable(true)
            builder.setTitle("Delete Account")
            builder.setMessage("Are you sure you want to delete your account?")
            builder.setPositiveButton(
                "Confirm"
            ) { dialog, which ->
                AuthApi.onDeleteAccount(requireActivity(), this)
                    .start()
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, which ->
                dialog.cancel()
            }

            val dialog = builder.create()
            dialog.show()
            
        }
        return view
    }

    private fun onSignOut(){
        Storage.removeData(requireActivity(), "token")
        Navigator.navigate(requireContext(), LoginActivity::class.java)
        requireActivity().finish()
    }

    override fun onResume() {
        super.onResume()
        AppContext.setCartCount(AppContext.cartCount, requireActivity())
    }

    override fun onFinishValidateToken() {
    }

    override fun onFinishDeleteAccount() {
        onSignOut()
    }

}