package com.alihammoud.nycschools.model

import com.google.gson.annotations.SerializedName

data class SATData(

    @SerializedName("dbn")
    val id: String,
    @SerializedName("school_name")
    val school_name: String,
    @SerializedName("num_of_sat_test_takers")
    val takers_num: String,
    @SerializedName("sat_critical_reading_avg_score")
    val reading_avg: String,
    @SerializedName("sat_math_avg_score")
    val math_avg: String,
    @SerializedName("sat_writing_avg_score")
    val writing_avg: String


)




