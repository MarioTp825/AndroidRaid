package com.example.myapplication.dto

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class DiskSetting(
    @Id
    var Id: Long = 0,
    val diskId: Long,
    val order: Int,
    val group: Long,
    val range: Long,
    var state: Boolean = true
)
