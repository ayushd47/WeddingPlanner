package com.reshika.foodmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.reshika.foodmart.adapter.NoodleAdapter
import com.reshika.foodmart.api.ServiceBuilder
import com.reshika.foodmart.entity.Product
import com.reshika.foodmart.model.CartModel
import com.reshika.foodmart.repository.CartRepository
import com.reshika.foodmart.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddToCartActivity : AppCompatActivity(),View.OnClickListener{
    private lateinit var itemImage:ImageView
    private lateinit var txtName:TextView
    private lateinit var txtPrice:TextView
    private lateinit var txtAvailability:TextView
    private lateinit var txtDesc:TextView
    private lateinit var addtocart:ExtendedFloatingActionButton
    var image=""
    var data=""

 var addcart:Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)

        itemImage = findViewById(R.id.itemImage)
        txtName = findViewById(R.id.txtName)
        txtPrice = findViewById(R.id.txtPrice)
        txtAvailability = findViewById(R.id.txtAvailability)
        txtDesc = findViewById(R.id.txtDesc)
        addtocart = findViewById(R.id.addtocart)

        addtocart.setOnClickListener(this)

          addcart=intent.getParcelableExtra("data")
        if (addcart!=null){
            image= ServiceBuilder.loadImagePath()
            image+= addcart?.ProductImage
            ServiceBuilder.image=addcart?.ProductImage.toString()
            Glide.with(this)
                    .load(image)
                    .into(itemImage )
            txtName.setText(addcart?.ProductName)
            txtPrice.setText(addcart?.ProductPrice)
            txtAvailability.setText(addcart?.ProductAvailable)
            txtDesc.setText(addcart?.ProductDesc)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addtocart->{
                addToCart()
            }
        }
    }

    private fun addToCart() {
        Toast.makeText(this, "path ${ServiceBuilder.image}", Toast.LENGTH_SHORT).show()

        val ProductName=txtName.text.toString()
        val ProductImage=ServiceBuilder.image
        val ProductPrice=txtPrice.text.toString()
        val ProductAvailable=txtAvailability.text.toString()
        val ProductDesc=txtDesc.text.toString()
        val username="${ServiceBuilder.username}"

        val cart= CartModel(ProductName = ProductName, ProductImage =ProductImage,ProductPrice =ProductPrice,ProductAvailable = ProductAvailable,ProductDesc = ProductDesc,username = username)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cartRepository= CartRepository()
                val response =cartRepository.addcart(cart)
                if (response.success==true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddToCartActivity, "Added", Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddToCartActivity, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}