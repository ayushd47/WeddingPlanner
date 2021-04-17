package com.reshika.foodmartwearos.entity

import android.os.Parcel
import android.os.Parcelable

import org.intellij.lang.annotations.PrintFormat


data class Product (

        var ProductName:String?=null,
        var ProductImage:String?=null,
        var ProductPrice:String?=null,
        var ProductDesc:String?=null,
        var ProductAvailable:String?=null,
        var ProductRating:String?=null,
        var ProductType:String?=null

)