package com.reshika.foodmart.repository

import com.reshika.foodmart.api.ConsumerAPI
import com.reshika.foodmart.api.MyApiRequest
import com.reshika.foodmart.api.ProductAPI
import com.reshika.foodmart.api.ServiceBuilder
import com.reshika.foodmart.entity.Consumer
import com.reshika.foodmart.response.GetProductResponse
import com.reshika.foodmart.response.LoginResponse

class ProductRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(ProductAPI::class.java)

    suspend fun getNoodle() : GetProductResponse {
        return apiRequest {
            myApi.getNoodle(
                    ServiceBuilder.noodle!!
            )
        }
    }
    suspend fun getDrink() : GetProductResponse {
        return apiRequest {
            myApi.getProduct(
                    ServiceBuilder.drink!!
            )
        }
    }
    suspend fun getFrozen() : GetProductResponse {
        return apiRequest {
            myApi.getProduct(
                    ServiceBuilder.frozen!!
            )
        }
    }
    suspend fun getfruit() : GetProductResponse {
        return apiRequest {
            myApi.getProduct(
                    ServiceBuilder.fruit!!
            )
        }
    }
    suspend fun getVeg() : GetProductResponse {
        return apiRequest {
            myApi.getProduct(
                    ServiceBuilder.veg!!
            )
        }
    }



    suspend fun getAllProduct() : GetProductResponse {
        return apiRequest {
            myApi.getAllProduct()

        }
    }

}