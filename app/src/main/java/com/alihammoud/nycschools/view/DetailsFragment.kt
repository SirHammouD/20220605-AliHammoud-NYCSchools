package com.alihammoud.nycschools.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alihammoud.nycschools.databinding.FragmentDetailsBinding
import com.alihammoud.nycschools.viewmodel.MainRecyclerAdapter
import com.alihammoud.nycschools.viewmodel.SharedViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend


class DetailsFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(layoutInflater)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        context?.let { sharedViewModel.getData(it) }

        sharedViewModel._satData.observe(viewLifecycleOwner, Observer{
            Log.e("RECEIVED DETAILS FRAG", it.toString())

        } )

        binding.gradRate.apply {
            description.text = ""
            //hollow pie chart
            isDrawHoleEnabled = false
            setTouchEnabled(false)
            //adding padding
            setExtraOffsets(20f, 0f, 20f, 20f)
            setUsePercentValues(true)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.isWordWrapEnabled = true
        }

        binding.attenRate.apply {
            description.text = ""
            //hollow pie chart
            isDrawHoleEnabled = false
            setTouchEnabled(false)
            //adding padding
            setExtraOffsets(20f, 0f, 20f, 20f)
            setUsePercentValues(true)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.isWordWrapEnabled = true
        }

        return  binding.root
    }


}