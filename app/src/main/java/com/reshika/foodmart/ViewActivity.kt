package com.reshika.foodmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reshika.foodmart.adapter.ProductAdapter
import com.reshika.foodmart.db.FoodMartDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        recyclerView=findViewById(R.id.recycler_popular)
        CoroutineScope(Dispatchers.IO).launch {
            val lstproduct=FoodMartDB.getInstance(this@ViewActivity).getProductDAO().getAllProduct()
            withContext(Dispatchers.Main){
                recyclerView.adapter=ProductAdapter(this@ViewActivity,lstproduct)
                recyclerView.layoutManager=LinearLayoutManager(this@ViewActivity)
            }
        }

    }
}