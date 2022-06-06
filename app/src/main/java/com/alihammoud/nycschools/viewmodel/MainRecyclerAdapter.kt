package com.alihammoud.nycschools.viewmodel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.alihammoud.nycschools.R
import com.alihammoud.nycschools.model.SchoolsData
import com.alihammoud.nycschools.view.DetailsFragment

class MainRecyclerAdapter() : RecyclerView.Adapter<MainRecyclerAdapter.DataViewHolder>(){
    private lateinit var list: List<SchoolsData>
    private  var detailsFragment: DetailsFragment = DetailsFragment()
    private  var setFragment: FragmentManager = FragmentManager()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.schools_item,parent, false)

        return  DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]

        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        val transaction = manager.beginTransaction()


        holder.name.text = (currentItem.school_name)
        holder.phone_btn.text = (currentItem.phone_number)
        holder.phone_btn.setOnClickListener(View.OnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:"+ currentItem.phone_number)
            val context = holder.phone_btn.context
            context.startActivity(dialIntent)
        })
        holder.address_btn.text = ("Show on Maps")
        holder.address_btn.setOnClickListener(View.OnClickListener {

            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + currentItem.street + ", " + currentItem.city)
            )
            val context = holder.address_btn.context
            context.startActivity(mapIntent)
        })
        holder.address.text = (currentItem.street + ", " + currentItem.city)
        holder.card.setOnClickListener {

            var grad: String = "0"
            if (currentItem.graduation_rate == null){
                grad="0"
            }
            else{
                grad=currentItem.graduation_rate
            }
            passDetails(currentItem.id,currentItem.desc, currentItem.phone_number, currentItem.street + ", "+currentItem.city, currentItem.school_name, grad, currentItem.attendance_rate, currentItem.students_num)

            setFragment.setFragmentToFragment(transaction,R.id.frame,detailsFragment)


        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.name)
        val address: TextView = itemView.findViewById(R.id.address)
        val card: CardView = itemView.findViewById(R.id.item)
        val phone_btn: Button = itemView.findViewById(R.id.phone_btn)
        val address_btn: Button = itemView.findViewById(R.id.address_btn)

    }
    fun setData(newlist: List<SchoolsData>){
      list = newlist
    }

    fun passDetails(id: String, desc: String, phone: String, address: String, name: String, grad: String, atten: String, studentNum: String){
        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("desc", desc)
        bundle.putString("phone", phone)
        bundle.putString("address", address)
        bundle.putString("studentNum", studentNum)
        bundle.putString("name", name)
        bundle.putString("grad", grad)
        bundle.putString("atten", atten)
        detailsFragment.arguments = bundle

    }
}