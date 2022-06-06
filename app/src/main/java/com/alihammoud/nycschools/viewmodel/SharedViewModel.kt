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

    private var schoolsData = MutableLiveData<List<SchoolsData>>()
    val _schoolsData: LiveData<List<SchoolsData>> = schoolsData

    private var satData = MutableLiveData<List<SATData>>()
    val _satData: LiveData<List<SATData>> = satData

    //lateinit var jsonObjectRequest: JsonObjectRequest
    lateinit var jsonArrayRequest: JsonArrayRequest
    private val ioScope = CoroutineScope(Dispatchers.IO + Job() )

    val app_token: String = "YhvZ3lqLmtqMo8i0UFhl7xy79"
    //?$app_token=APP_TOKEN
    val API_Schools: String = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json"
    val API_SAT: String = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json"

    lateinit var schoolList: List<SchoolsData>
    lateinit var satList: List<SATData>

    fun getData(context: Context) {

        val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

        ioScope.launch {

            jsonArrayRequest = JsonArrayRequest(Request.Method.GET,API_Schools, null,
                {

                    try {
                        val gson = Gson()
                        val listType = object : TypeToken<List<SchoolsData>>() {}.type
                        schoolList = gson.fromJson<List<SchoolsData>>(
                            it.toString(),
                            listType
                        )
                        schoolsData.postValue(schoolList)

                        Log.e("RECEIVED", schoolList.toString())

                    }catch (e: JSONException){
                        Log.e("JSONException", e.toString())
                    }

                }, {

                    Log.e("ERROR",it.toString())
                })
            requestQueue.add(jsonArrayRequest)

        }

    }

    fun getDetails(context: Context) {

        val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

        ioScope.launch {

            jsonArrayRequest = JsonArrayRequest(Request.Method.GET,API_SAT, null,
                {

                    try {
                        val gson = Gson()
                        val listType = object : TypeToken<List<SATData>>() {}.type
                        satList = gson.fromJson<List<SATData>>(
                            it.toString(),
                            listType
                        )
                        satData.postValue(satList)

                        Log.e("RECEIVED", satList.toString())

                    }catch (e: JSONException){
                        Log.e("JSONException", e.toString())
                    }

                }, {

                    Log.e("ERROR",it.toString())
                })
            requestQueue.add(jsonArrayRequest)

        }

    }

}