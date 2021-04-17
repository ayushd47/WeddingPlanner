package com.reshika.foodmartwearos.repository


import com.reshika.foodmartwearos.api.ConsumerAPI
import com.reshika.foodmartwearos.api.MyApiRequest
import com.reshika.foodmartwearos.api.ServiceBuilder
import com.reshika.foodmartwearos.entity.Consumer
import com.reshika.foodmartwearos.response.LoginResponse
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
//    suspend fun getCitizen(): GetProfileResponse {
//        return apiRequest {
//            myApi.getProfile(
//                    ServiceBuilder.token!!,
//            )
//        }
//    }
//    suspend fun uploadImage(Cemail: String, body: MultipartBody.Part)
//            : ImageResponse {
//        return apiRequest {
//            myApi.uploadImage(ServiceBuilder.token!!,Cemail, body)
//        }
//    }
//    suspend fun logout() : LogoutResponse {
//        return apiRequest {
//            myApi.logout(ServiceBuilder.token!!)
//        }
//    }
}