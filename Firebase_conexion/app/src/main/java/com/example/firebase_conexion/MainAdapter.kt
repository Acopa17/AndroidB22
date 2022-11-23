package com.example.firebase_conexion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private var dataList = mutableListOf<Usuario>()

    fun setListData(data:MutableList<Usuario>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user:Usuario = dataList[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0){
            return dataList.size
        }else{
            return 0
        }
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView(user: Usuario){
            Glide.with(context).load(user.imageURL).into(itemView.circleImageView)
            itemView.txt_title.text = user.nombre
            itemView.txt_desc.text = user.descripcion
        }
    }


}