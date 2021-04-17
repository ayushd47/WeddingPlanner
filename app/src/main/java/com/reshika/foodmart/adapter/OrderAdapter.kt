package com.reshika.foodmart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reshika.foodmart.R
import com.reshika.foodmart.api.ServiceBuilder
import com.reshika.foodmart.entity.Order
import com.reshika.foodmart.model.CartModel

class OrderAdapter(
    val lstcart: MutableList<Order>,
    val context: Context
): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var tvname: TextView
        var tvprice: TextView
        var tvAvailability: TextView
        var tvDesc: TextView
        val btnDelete: Button
        val btnOrder: Button
        var imgProfile: ImageView

        init {

            tvname=itemView.findViewById(R.id.tvname)

            tvprice=itemView.findViewById(R.id.tvprice)
            imgProfile=itemView.findViewById(R.id.imgProfile)
            tvAvailability=itemView.findViewById(R.id.tvAvailability)
            tvDesc=itemView.findViewById(R.id.tvDesc)
            btnDelete=itemView.findViewById(R.id.btnDelete)
            btnOrder=itemView.findViewById(R.id.btnorder)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lstcart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = lstcart[position]
        holder.tvname.text = cart.ProductName
        holder.tvprice.text = cart.ProductPrice
        var image= ServiceBuilder.loadImagePath()+cart.ProductImage
        Toast.makeText(context, "k xa${image}", Toast.LENGTH_SHORT).show()
        Glide.with(context).load(image).into(holder.imgProfile)
        holder.btnDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                lstcart.removeAt(position);
                notifyDataSetChanged();
            }
        })






    }
}