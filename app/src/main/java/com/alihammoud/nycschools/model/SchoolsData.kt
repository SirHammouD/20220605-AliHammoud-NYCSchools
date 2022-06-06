package com.alihammoud.nycschools.model

import com.google.gson.annotations.SerializedName

data class SchoolsData(

    @SerializedName("dbn")
    val id: String,

    @SerializedName("school_name")
    val school_name: String,
    @SerializedName("primary_address_line_1")
    val street: String,
    @SerializedName("city")
    val city: String,

    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("overview_paragraph")
    val desc: String,

    @SerializedName("total_students")
    val students_num: String,
    @SerializedName("attendance_rate")
    val attendance_rate: String,
    @SerializedName("graduation_rate")
    val graduation_rate: String,


)
