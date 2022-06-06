package com.alihammoud.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alihammoud.nycschools.databinding.ActivityMainBinding
import com.alihammoud.nycschools.view.MainFragment
import com.alihammoud.nycschools.viewmodel.FragmentManager

class MainActivity : AppCompatActivity() {

    // Define and initialize the custom fragment manager class
    private  var setFragment: FragmentManager = FragmentManager()
    // Define and initialize the main fragment to be used as main view
    private  var mainFragment: MainFragment = MainFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use binding to set up the content view of the screen (layout to be used) which eliminates the need to use findById
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the MainFragment into the frame view to be displayed as the first screen when the app launches
        setFragment.setFragmentToActivity(supportFragmentManager, R.id.frame, mainFragment)


    }


}