package com.alihammoud.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alihammoud.nycschools.databinding.ActivityMainBinding
import com.alihammoud.nycschools.view.MainFragment
import com.alihammoud.nycschools.viewmodel.FragmentManager

class MainActivity : AppCompatActivity() {

    private  var mainFragment: MainFragment = MainFragment()
    private  var setFragment: FragmentManager = FragmentManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        mainFragment = MainFragment()
        setFragment.setFragmentToActivity(supportFragmentManager,R.id.frame,mainFragment)


    }


}