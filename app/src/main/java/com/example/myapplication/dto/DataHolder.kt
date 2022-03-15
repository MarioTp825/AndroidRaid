package com.example.myapplication.dto

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class DataHolder(
    @Id
    var Id:Long = 0,
    var index: Int = 0,
    var data: String? = null
)
