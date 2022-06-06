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

    // Initialize the list which will hold the data
    private lateinit var list: List<SchoolsData>
    // Define and initialize the main fragment to be used as main view
    private  var detailsFragment: DetailsFragment = DetailsFragment()
    // Define and initialize the custom fragment manager class
    private  var setFragment: FragmentManager = FragmentManager()

    // Inflate the layout to be used when recycling items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.schools_item,parent, false)

        return  DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        // Return the data position in the list
        val currentItem = list[position]

        // Start the Fragment manager for future transaction
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        val transaction = manager.beginTransaction()

        // Call the view halder, then select each view to be populated with the needed data from the data list based on the current position
        holder.name.text = (currentItem.school_name)
        holder.phone_btn.text = (currentItem.phone_number)

        // If the phone btn clicked, start an implicit intent and open the phone dial with the provided phone number
        holder.phone_btn.setOnClickListener(View.OnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:"+ currentItem.phone_number)
            val context = holder.phone_btn.context
            context.startActivity(dialIntent)
        })
        // If the map btn clicked, start an implicit intent and open google maps with the provided address
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
        // If an item is clicked (card), start the Fragment Transaction
        holder.card.setOnClickListener {

            // Check for null values
            var grad: String = "0"
            if (currentItem.graduation_rate == null){
                grad="0"
            }
            else{
                grad=currentItem.graduation_rate
            }
            // Put the Bundle information to be sent
            passDetails(currentItem.id,currentItem.desc, currentItem.phone_number, currentItem.street + ", "+currentItem.city, currentItem.school_name, grad, currentItem.attendance_rate, currentItem.students_num)

            // New Fragment Transaction with the details sent
            setFragment.setFragmentToFragment(transaction,R.id.frame,detailsFragment)


        }
    }

    // Return the list size so the item gets recycled based on how many items in the list are present
    override fun getItemCount(): Int {
       return list.size
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        // Declare the views and holders
        val name: TextView = itemView.findViewById(R.id.name)
        val address: TextView = itemView.findViewById(R.id.address)
        val card: CardView = itemView.findViewById(R.id.item)
        val phone_btn: Button = itemView.findViewById(R.id.phone_btn)
        val address_btn: Button = itemView.findViewById(R.id.address_btn)

    }
    // Function used to get the data list and then pass it into the recycler
    fun setData(newlist: List<SchoolsData>){
      list = newlist
    }

    // Helper Function to send data Bundles between Fragments, when an item is clicked when need some data to be sent to the new fragment so UI can update accordingly
    fun passDetails(id: String, desc: String, phone: String, address: String, name: String, grad: String, atten: String, studentNum: String){
        // Initiate a Bundle then put data as needed
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