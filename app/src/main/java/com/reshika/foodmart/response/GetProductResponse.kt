package com.reshika.foodmart.response

import com.reshika.foodmart.entity.Product

data class GetProductResponse(
        val success : Boolean? = null,
        val data : MutableList<Product>? = null,
        val alldata:MutableList<Product>?=null
)