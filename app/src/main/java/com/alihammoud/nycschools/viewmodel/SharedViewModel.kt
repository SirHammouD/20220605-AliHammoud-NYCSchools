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
import com.android.volley.toolbox.JsonArrayRequest

import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject


class SharedViewModel: ViewModel() {

    // Setup LiveData to be obsersved and MutableLiveData so we can update it for the first database
    private var schoolsData = MutableLiveData<List<SchoolsData>>()
    val _schoolsData: LiveData<List<SchoolsData>> = schoolsData

    // Setup LiveData to be obsersved and MutableLiveData so we can update it for the second database
    private var satData = MutableLiveData<List<SATData>>()
    val _satData: LiveData<List<SATData>> = satData

    // Initialize the Volley JSONArray Request and the
    lateinit var jsonArrayRequest: JsonArrayRequest
    // Initialize the CoroutineScope to avoid app freezing and delays
    private val ioScope = CoroutineScope(Dispatchers.IO + Job() )

    // API URLs
    val API_Schools: String = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json"
    val API_SAT: String = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json"


    // Temporary list to be used when assigning data
    private lateinit var schoolList: List<SchoolsData>
    private lateinit var satList: List<SATData>

    // Function to get our Schools data
    fun getData(context: Context) {

        // Initialize a Volley Request the Queue
        val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

        // Start the CoroutineScope
        ioScope.launch {

            // Start the Volley Get request at the API url provided
            jsonArrayRequest = JsonArrayRequest(Request.Method.GET,API_Schools, null,
                {
                    // Use Gson to format parse and format the JSON array into usable data the pass it into the lists
                    try {
                        val gson = Gson()
                        val listType = object : TypeToken<List<SchoolsData>>() {}.type
                        schoolList = gson.fromJson<List<SchoolsData>>(
                            it.toString(),
                            listType
                        )
                        // Pass the dataList into our Mutable Live data to be used in the observer
                        schoolsData.postValue(schoolList)

                        Log.e("RECEIVED", schoolList.toString())

                    // Error Handling
                    }catch (e: JSONException){
                        Log.e("JSONException", e.toString())
                    }

                }, {

                    Log.e("ERROR",it.toString())
                })
            // Start the Queue
            requestQueue.add(jsonArrayRequest)

        }

    }

    // Function to get our SAT data
    fun getDetails(context: Context) {

        // Initialize a Volley Request the Queue
        val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

        // Start the CoroutineScope
        ioScope.launch {

            // Start the Volley Get request at the API url provided
            jsonArrayRequest = JsonArrayRequest(Request.Method.GET,API_SAT, null,
                {

                    // Use Gson to format parse and format the JSON array into usable data the pass it into the lists
                    try {
                        val gson = Gson()
                        val listType = object : TypeToken<List<SATData>>() {}.type
                        satList = gson.fromJson<List<SATData>>(
                            it.toString(),
                            listType
                        )
                        // Pass the dataList into our Mutable Live data to be used in the observer
                        satData.postValue(satList)

                        Log.e("RECEIVED", satList.toString())

                    // Error Handling
                    }catch (e: JSONException){
                        Log.e("JSONException", e.toString())
                    }

                }, {

                    Log.e("ERROR",it.toString())
                })
            // Start the Queue
            requestQueue.add(jsonArrayRequest)

        }

    }

}