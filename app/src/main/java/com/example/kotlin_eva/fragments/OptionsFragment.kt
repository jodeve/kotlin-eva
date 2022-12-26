package com.example.kotlin_eva.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.activities.OnBoardingActivity
import com.example.kotlin_eva.adapters.ProductsAdapter
import com.example.kotlin_eva.components.OptionsItemView
import com.example.kotlin_eva.models.Product
import com.example.kotlin_eva.services.Navigator
import com.example.kotlin_eva.services.ProductsApi
import com.example.kotlin_eva.services.Storage

class OptionsFragment: Fragment() {

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
            Storage.removeData(requireActivity(), "token")
            Navigator.navigate(requireContext(), OnBoardingActivity::class.java)
            requireActivity().finish()
        }
        return view
    }

}