package com.alihammoud.nycschools.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.alihammoud.nycschools.R

class FragmentManager {

    fun setFragmentToActivity(manager: FragmentManager,frameId: Int ,fragment: Fragment){
        manager.beginTransaction()
            .add(frameId,fragment)
            .commit()

    }

    fun setFragmentToFragment(manager: FragmentTransaction,frameId: Int ,fragment: Fragment){
            manager
                .replace(frameId,fragment)
                .addToBackStack("saved")
                .commit()

    }
}