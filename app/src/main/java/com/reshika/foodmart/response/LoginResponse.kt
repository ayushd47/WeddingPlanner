package com.reshika.foodmart.response

import com.reshika.foodmart.entity.Consumer

data class LoginResponse (
    val success : Boolean? = null,
    val token : String? = null,
    val data:String?=null,
    val userdata : MutableList<Consumer>?=null
)