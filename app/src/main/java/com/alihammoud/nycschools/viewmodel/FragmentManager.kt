package com.alihammoud.nycschools.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.alihammoud.nycschools.R

class FragmentManager {

    // Set a function to manage adding a fragment into the screen
    fun setFragmentToActivity(manager: FragmentManager,frameId: Int ,fragment: Fragment){
        manager
            .beginTransaction()
            .add(frameId,fragment)
            .commit()

    }

    // Set a function to manage transitioning into a new fragment by replacing the older one and storing it in stack for when onBackPress is needed
    fun setFragmentToFragment(manager: FragmentTransaction,frameId: Int ,fragment: Fragment){
            manager
                .replace(frameId,fragment)
                .addToBackStack("saved")
                .commit()

    }
}