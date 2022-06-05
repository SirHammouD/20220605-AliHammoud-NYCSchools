package com.alihammoud.nycschools.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.alihammoud.nycschools.R
import com.alihammoud.nycschools.databinding.ActivityMainBinding
import com.alihammoud.nycschools.databinding.FragmentDetailsBinding
import com.alihammoud.nycschools.viewmodel.SchoolViewModel


class DetailsFragment : Fragment() {

    private lateinit var schoolViewModel: SchoolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        schoolViewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(layoutInflater)


        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}