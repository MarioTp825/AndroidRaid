package com.example.myapplication.dto

import io.objectbox.annotation.Id

data class RaidHolder(
    @Id
    var id: Long = 0,
    val name: String,
    var type: Int,
    var diskNumber: Int = 0
)
