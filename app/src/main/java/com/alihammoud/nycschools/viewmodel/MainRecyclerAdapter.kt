package com.alihammoud.nycschools.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.alihammoud.nycschools.R
import com.alihammoud.nycschools.model.SchoolsData

class MainRecyclerAdapter() : RecyclerView.Adapter<MainRecyclerAdapter.DataViewHolder>(){
   private var list: List<SchoolsData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.schools_item,parent, false)

        return  DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.name.text = (currentItem.school_name)
        holder.address.text = (currentItem.city + ", "+ currentItem.street)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.name)
        val address: TextView = itemView.findViewById(R.id.address)

    }
    fun setData(newlist: List<SchoolsData>){
      list = newlist
    }
}