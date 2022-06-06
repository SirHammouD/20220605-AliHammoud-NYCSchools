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

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mainRecyclerAdapter: MainRecyclerAdapter = MainRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(layoutInflater)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        context?.let { sharedViewModel.getData(it) }

        sharedViewModel._schoolsData.observe(viewLifecycleOwner, Observer{
            Log.e("RECEIVED MAIN FRAG", it.toString())
            mainRecyclerAdapter.setData(it)
            binding.recyclerMain.apply {
                layoutManager = linearLayoutManager
                adapter = mainRecyclerAdapter
            }

        } )



    return  binding.root
}


}