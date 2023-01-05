package com.example.kotlin_eva.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_eva.R
import com.example.kotlin_eva.RoundCornersTransform
import com.example.kotlin_eva.fragments.ActivityFragment
import com.example.kotlin_eva.interfaces.CartApiListener
import com.example.kotlin_eva.interfaces.ProductsApiListener
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.CartProduct
import com.example.kotlin_eva.models.Product
import com.example.kotlin_eva.services.CartApi
import com.example.kotlin_eva.services.ProductsApi
import com.example.kotlin_eva.services.Statusbar
import com.squareup.picasso.Picasso

class ProductActivity : AppCompatActivity(), ProductsApiListener, CartApiListener {

    lateinit var addToCart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        Statusbar.makeWhite(this)
        val productId = intent.getStringExtra("productId")
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        ProductsApi.onFetchProduct(this, this, productId!!)
            .start()
        addToCart = findViewById<Button>(R.id.addToCart)
        addToCart.setOnClickListener {
            CartApi.onAddCartProduct(this, productId)
                .start()
        }
        AppContext.setCartCount(AppContext.cartCount, this)
    }

    fun hideAddToCart(){
        addToCart.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        AppContext.setCartCount(AppContext.cartCount, this)
    }

    override fun onFinishFetchProducts(products: ArrayList<Product>) {
    }

    override fun onFinishFetchProduct(product: Product) {
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
        val productCost = findViewById<TextView>(R.id.productCost)
        productCost.text = product.costString()
        if(product.isInCart) hideAddToCart()
    }

    override fun onFinishAddCartProduct() {
        hideAddToCart()
    }

    override fun onFinishFetchCartProducts(cartProducts: ArrayList<CartProduct>) {
    }

    override fun onFinishRemoveCartProduct(position: Int) {
    }
}