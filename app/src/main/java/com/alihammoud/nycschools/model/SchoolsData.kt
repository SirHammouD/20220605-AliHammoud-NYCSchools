package com.alihammoud.nycschools.model

import com.google.gson.annotations.SerializedName

data class SchoolsData(

    @SerializedName("dbn")
    val id: String,
    @SerializedName("school_name")
    val school_name: String,
    @SerializedName("total_students")
    val students_num: Int,
    @SerializedName("graduation_rate")
    val graduation_rate: Float,
    @SerializedName("primary_address_line_1")
    val street: String,
    @SerializedName("city")
    val city: String


)
