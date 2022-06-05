package com.alihammoud.nycschools.viewmodel


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alihammoud.nycschools.model.SATData
import com.alihammoud.nycschools.model.SchoolsData
import com.android.volley.Request
import com.android.volley.RequestQueue

import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONException


class SchoolViewModel: ViewModel() {

    private var schoolsData = MutableLiveData<List<SchoolsData>>()
    val _schoolsData: LiveData<List<SchoolsData>> = schoolsData

    private var satData = MutableLiveData<SATData>()
    val _satData: LiveData<SATData> = satData

    lateinit var jsonObjectRequest: JsonObjectRequest

    val API_Schools: String = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json"
    val API_SAT: String = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json"

    fun getData(context: Context) {

        val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

        jsonObjectRequest = JsonObjectRequest(Request.Method.GET,API_Schools, null,
            {

               try {
                   val gson = Gson()
                   schoolsData = gson.fromJson(
                       it.toString(),
                       MutableLiveData<List<SchoolsData>>()::class.java
                   )
                   Log.e("RECEIVED", schoolsData.toString())

                }catch (e: JSONException){
                    Log.e("JSONException", e.toString())
                }

            }, {

            Log.e("ERROR",it.toString())
            })
        requestQueue.add(jsonObjectRequest)
    }


}