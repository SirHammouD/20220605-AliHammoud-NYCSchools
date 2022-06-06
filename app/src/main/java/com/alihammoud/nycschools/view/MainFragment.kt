package com.alihammoud.nycschools.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alihammoud.nycschools.databinding.FragmentMainBinding
import com.alihammoud.nycschools.model.SchoolsData
import com.alihammoud.nycschools.viewmodel.MainRecyclerAdapter
import com.alihammoud.nycschools.viewmodel.SharedViewModel



class MainFragment : Fragment() {

    // Declare the ViewModel, along with the UI handlers to be used
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mainRecyclerAdapter: MainRecyclerAdapter = MainRecyclerAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(layoutInflater)
        // Assign the ViewModel
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Assign the linearLayoutManager so we can have a vertical linear list to scroll through in the recyclerview
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        // Call the getData from the ViewModel to fetch data
        context?.let { sharedViewModel.getData(it) }

        // Setup an observer to observe for changes in our data and update the recyclerview accordingly
        sharedViewModel._schoolsData.observe(viewLifecycleOwner, Observer{
            Log.e("RECEIVED MAIN FRAG", it.toString())
            // Pass data into the recycler adapter
            mainRecyclerAdapter.setData(it)

            // Use binding to apply attributes to the recyclerview such as orientation and adapter
            binding.recyclerMain.apply {
                layoutManager = linearLayoutManager
                adapter = mainRecyclerAdapter
            }

        } )



    return  binding.root
}


}