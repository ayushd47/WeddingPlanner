package com.reshika.foodmart.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.function.Consumer

object ServiceBuilder {
    private const val BASE_URL = "http://10.0.2.2:90/"
    //private const val BASE_URL = "http://192.168.43.32:3000/api/v1/"
    var token : String? = null
    var drink:String?=null
    var noodle:String?=null
    var username:String?=null
    var frozen:String?=null
    var fruit:String?=null
    var veg:String?=null
    var image:String?=null
    var data:MutableList<com.reshika.foodmart.entity.Consumer>?=null
    private val okHttp = OkHttpClient.Builder()

    //Create retrofit builder
    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()

    //Generic function
    fun <T> buildService(serviceType : Class<T>): T{
        return retrofit.create(serviceType)
    }
    fun loadImagePath(): String {
        return  BASE_URL
    }
}