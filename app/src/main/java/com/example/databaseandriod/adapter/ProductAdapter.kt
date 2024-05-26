package com.example.databaseandriod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseandriod.model.ProductModel
import android.view.View
import android.widget.TextView
import com.example.databaseandriod.R

class ProductAdapter(var data : ArrayList<ProductModel>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var name : TextView = view.findViewById(R.id.lblName)
        var price : TextView = view.findViewById(R.id.lblPrice)
        var desc : TextView = view.findViewById(R.id.lblDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.sample_product,parent,false)
        return  ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.name.text = data[position].productName
        holder.price.text = data[position].productPrice.toString()
        holder.desc.text = data[position].productDesc
    }


}