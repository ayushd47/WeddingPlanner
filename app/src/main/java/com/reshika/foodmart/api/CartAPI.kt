package com.reshika.foodmart.api

import com.reshika.foodmart.model.CartModel
import com.reshika.foodmart.response.CartResponse
import com.reshika.foodmart.response.GetCartResponse
import retrofit2.Response
import retrofit2.http.*

interface CartAPI {


    @POST("cart/add")
    suspend fun addCart(
            @Header("Authorization") token:String,
            @Body cart: CartModel
    ): Response<CartResponse>

    @GET("cart")
    suspend fun getCart(
        @Header("Authorization") token:String,
    ):Response<GetCartResponse>

}