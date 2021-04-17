package com.reshika.foodmart.response

import com.reshika.foodmart.entity.Product
import com.reshika.foodmart.model.CartModel

data class GetCartResponse(
        val success : Boolean? = null,
        val data : MutableList<CartModel>? = null
)