package com.example.quickstore005

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemAdapter(var context: Context, var list:ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var my_view:View = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false)
        return ItemHolder(my_view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(list[position].name, list[position].price, list[position].photo)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var item_name: TextView = itemView.findViewById(R.id.name)
        var item_price:TextView = itemView.findViewById(R.id.price)
        var item_photo :ImageView = itemView.findViewById(R.id.photo)

        fun bind(n:String, p: Int, u:String){
            item_name.text = n
            item_price.text = p.toString()
            Picasso.get().load("http://10.100.22.100/"+u).into(item_photo)
        }
    }
    }
