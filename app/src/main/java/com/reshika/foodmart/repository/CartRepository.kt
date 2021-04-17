package com.reshika.foodmart.repository

import com.reshika.foodmart.api.CartAPI
import com.reshika.foodmart.api.MyApiRequest
import com.reshika.foodmart.api.ServiceBuilder
import com.reshika.foodmart.model.CartModel
import com.reshika.foodmart.response.CartResponse
import com.reshika.foodmart.response.GetCartResponse


class CartRepository : MyApiRequest() {
    val myApi = ServiceBuilder.buildService(CartAPI::class.java)

    suspend fun addcart(cart: CartModel): CartResponse {
        return apiRequest {
            myApi.addCart(
                    ServiceBuilder.token!!,
                    cart
            )
        }
    }

    suspend fun getCart(): GetCartResponse {
        return apiRequest {
            myApi.getCart(
                    ServiceBuilder.token!!
            )
        }
    }
}