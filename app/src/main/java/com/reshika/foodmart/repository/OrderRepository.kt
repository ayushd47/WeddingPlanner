package com.reshika.foodmart.repository

import com.reshika.foodmart.api.CartAPI
import com.reshika.foodmart.api.MyApiRequest
import com.reshika.foodmart.api.OrderAPI
import com.reshika.foodmart.api.ServiceBuilder
import com.reshika.foodmart.entity.Order

import com.reshika.foodmart.response.CartResponse
import com.reshika.foodmart.response.GetCartResponse

class OrderRepository : MyApiRequest() {
    val myApi = ServiceBuilder.buildService(OrderAPI::class.java)

    suspend fun addOrder(order:Order): CartResponse {
        return apiRequest {
            myApi.addOrder(
                ServiceBuilder.token!!,
                order
            )
        }
    }


}