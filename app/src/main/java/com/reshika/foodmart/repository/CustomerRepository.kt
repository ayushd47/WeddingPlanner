package com.reshika.foodmart.repository

import com.reshika.foodmart.api.ConsumerAPI
import com.reshika.foodmart.api.MyApiRequest
import com.reshika.foodmart.api.ServiceBuilder
import com.reshika.foodmart.entity.Consumer
import com.reshika.foodmart.response.GetProfileResponse
import com.reshika.foodmart.response.ImageResponse
import com.reshika.foodmart.response.LoginResponse
import okhttp3.MultipartBody

class CustomerRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(ConsumerAPI::class.java)

    suspend fun registerUser(consumer: Consumer) : LoginResponse {
        return apiRequest {
            myApi.registerUser(consumer)
        }
    }
    suspend fun checkUser(username : String, password : String): LoginResponse{
        return apiRequest {
            myApi.checkUser(username, password)
        }
    }
    suspend fun getCitizen(): GetProfileResponse {
        return apiRequest {
            myApi.getProfile(
                    ServiceBuilder.token!!,
            )
        }
    }
    suspend fun uploadImage(Cemail: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            myApi.uploadImage(ServiceBuilder.token!!,Cemail, body)
        }
    }
//    suspend fun logout() : LogoutResponse {
//        return apiRequest {
//            myApi.logout(ServiceBuilder.token!!)
//        }
//    }
}