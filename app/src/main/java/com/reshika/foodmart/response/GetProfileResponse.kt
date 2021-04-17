package com.reshika.foodmart.response

import com.reshika.foodmart.entity.Consumer

data class GetProfileResponse(
    val success:Boolean?=null,
    val profile:ArrayList<Consumer>?=null
)