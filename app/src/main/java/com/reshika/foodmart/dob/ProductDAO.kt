package com.reshika.foodmart.dob

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.reshika.foodmart.entity.Product

@Dao
interface ProductDAO {

    @Insert
    suspend fun registerProduct(product: Product)

    @Query("select * from Product")
    suspend fun getAllProduct():List<Product>

}