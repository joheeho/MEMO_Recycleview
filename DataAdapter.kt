package com.example.memo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter (val datalist: ArrayList<Data>): RecyclerView.Adapter<DataAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: DataAdapter.CustomViewHolder, position: Int) {
        holder.trash.setImageResource(datalist.get(position).trash)
        holder.text.text = datalist.get(position).text

    }
    class CustomViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){
        val trash = itemView.findViewById<ImageView>(R.id.iv_trash)  // 쓰레기 모양 이모티콘
        val text = itemView.findViewById<TextView>(R.id.textView2)  // 메모

    }
}