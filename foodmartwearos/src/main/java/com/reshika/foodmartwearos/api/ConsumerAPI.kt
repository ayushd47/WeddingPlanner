package com.reshika.foodmartwearos.api


import com.reshika.foodmartwearos.entity.Consumer
import com.reshika.foodmartwearos.response.LoginResponse
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

//    @POST("/profile")
//    suspend fun getProfile(
//            @Header("Authorization") token: String
//    ):Response<GetProfileResponse>
//
//    @Multipart
//    @PUT("user/image/:Cemail")
//    suspend fun uploadImage(
//            @Header("Authorization") token: String,
//            @Path("Cemail") Cemail: String,
//            @Part file: MultipartBody.Part
//    ): Response<ImageResponse>


}
