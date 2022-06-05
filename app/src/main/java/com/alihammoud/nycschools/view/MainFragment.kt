package com.alihammoud.nycschools.view

import android.os.Bundle
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
import com.alihammoud.nycschools.viewmodel.SchoolViewModel



class MainFragment : Fragment() {

    private lateinit var schoolViewModel: SchoolViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mainRecyclerAdapter: MainRecyclerAdapter = MainRecyclerAdapter()
    //private var list: MutableList<MutableLiveData<SchoolsData>> = mutableListOf()
    private var schoolsData = MutableLiveData<List<SchoolsData>>()
    val API_Schools: String = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json"
    val API_SAT: String = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(layoutInflater)
        schoolViewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        context?.let { schoolViewModel.getData(it) }

        schoolViewModel._schoolsData.observe(viewLifecycleOwner, Observer{
            mainRecyclerAdapter.setData(it)
        } )

        binding.recyclerMain.apply {
            layoutManager = linearLayoutManager
            adapter = mainRecyclerAdapter
        }

    return  binding.root
}


}