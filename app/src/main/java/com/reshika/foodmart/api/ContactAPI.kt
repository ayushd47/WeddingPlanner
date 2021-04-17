package com.reshika.foodmart.api


import com.reshika.foodmart.entity.Contact
import com.reshika.foodmart.response.ContactResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactAPI {
    @POST("user/contact")
    suspend fun contactUser(
        @Body contact: Contact
    ): Response<ContactResponse>

}