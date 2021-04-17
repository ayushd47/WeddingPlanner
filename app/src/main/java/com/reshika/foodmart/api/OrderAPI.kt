package com.reshika.foodmart.api

import com.reshika.foodmart.entity.Order
import com.reshika.foodmart.model.CartModel
import com.reshika.foodmart.response.CartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderAPI {
    @POST("user/order")
    suspend fun addOrder(
        @Header("Authorization") token:String,
        @Body order: Order
    ): Response<CartResponse>
}