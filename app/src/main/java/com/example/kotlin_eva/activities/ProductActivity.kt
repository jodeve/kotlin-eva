package com.example.kotlin_eva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.kotlin_eva.R
import com.example.kotlin_eva.RoundCornersTransform
import com.example.kotlin_eva.interfaces.Products
import com.example.kotlin_eva.models.Product
import com.example.kotlin_eva.services.ProductsApi
import com.example.kotlin_eva.services.Statusbar
import com.squareup.picasso.Picasso

class ProductActivity : AppCompatActivity(), Products {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        Statusbar.makeWhite(this)
        val productId = intent.getStringExtra("productId")
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        ProductsApi.show(this, this, productId!!)
            .start()
    }

    override fun callback(products: ArrayList<Product>) {
    }

    override fun setProduct(product: Product) {
        val productIndicator = findViewById<ProgressBar>(R.id.productIndicator)
        productIndicator.visibility = View.GONE
        val productView = findViewById<LinearLayout>(R.id.productView)
        productView.visibility = View.VISIBLE
        val productImage = findViewById<ImageView>(R.id.productImage)
        Picasso.get()
            .load(product.image)
            .transform(RoundCornersTransform(50f))
            .into(productImage)
        val productName = findViewById<TextView>(R.id.productName)
        productName.text = product.name
    }
}