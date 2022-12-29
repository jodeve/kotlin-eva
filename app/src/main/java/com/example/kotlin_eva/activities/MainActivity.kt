package com.example.kotlin_eva.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.adapters.ProductsAdapter
import com.example.kotlin_eva.R
import com.example.kotlin_eva.fragments.OptionsFragment
import com.example.kotlin_eva.fragments.OrdersFragment
import com.example.kotlin_eva.fragments.ProductsFragment
import com.example.kotlin_eva.interfaces.AuthApiListener
import com.example.kotlin_eva.interfaces.ProductsApiListener
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.Product
import com.example.kotlin_eva.services.AuthApi
import com.example.kotlin_eva.services.ProductsApi
import com.example.kotlin_eva.services.Statusbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), AuthApiListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Statusbar.makeWhite(this)
        AuthApi.validateToken(this)
            .start()
        //progressBar = findViewById(R.id.progressBar)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavView)
        val productsFragment = ProductsFragment()
        val optionsFragment = OptionsFragment()
        val ordersFragment = OrdersFragment()

        setCurrentFragment(productsFragment)

        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(productsFragment)
                R.id.orders->setCurrentFragment(ordersFragment)
                R.id.options->setCurrentFragment(optionsFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onFinishValidateToken() {

    }

    override fun onFinishDeleteAccount() {
    }


}