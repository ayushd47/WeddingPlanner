package com.reshika.foodmart.api

import com.reshika.foodmart.entity.Consumer
import com.reshika.foodmart.response.GetProfileResponse
import com.reshika.foodmart.response.ImageResponse
import com.reshika.foodmart.response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ConsumerAPI {
    //Register user
    @POST("user/register")
    suspend fun registerUser(
        @Body consumer: Consumer
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
        @Field("username") username:String,
        @Field("password") password:String
    ): Response<LoginResponse>

//    @GET("logout")
//    suspend fun logout(
//            @Header("Authorization") token: String
//    ):Response<LogoutResponse>

    @POST("/profile")
    suspend fun getProfile(
            @Header("Authorization") token: String
    ):Response<GetProfileResponse>

    @Multipart
    @PUT("user/image/:usernmae")
    suspend fun uploadImage(
            @Header("Authorization") token: String,
            @Path("username") username: String,
            @Part file: MultipartBody.Part
    ): Response<ImageResponse>


}
