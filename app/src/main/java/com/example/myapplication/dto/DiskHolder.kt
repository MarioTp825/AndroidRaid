package com.example.myapplication.dto

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class DiskHolder(
    @Id
    var Id:Long = 0,
    val name: String = "",
    val tag: String? = null,
    var pointer: Int = -1,
    var data: MutableList<Any> = mutableListOf()
)
