package com.reshika.foodmart.repository

import com.reshika.foodmart.api.ConsumerAPI
import com.reshika.foodmart.api.ContactAPI
import com.reshika.foodmart.api.MyApiRequest
import com.reshika.foodmart.api.ServiceBuilder

import com.reshika.foodmart.entity.Contact
import com.reshika.foodmart.response.ContactResponse
import com.reshika.foodmart.response.LoginResponse

class ContactRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(ContactAPI::class.java)

    suspend fun contactUser(contact: Contact) : ContactResponse {
        return apiRequest {
            myApi.contactUser(contact)
        }
    }
}