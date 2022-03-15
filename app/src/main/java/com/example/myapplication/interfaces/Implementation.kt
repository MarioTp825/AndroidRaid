package com.example.myapplication.interfaces

import com.example.myapplication.dto.RaidSetting

interface Implementation {
    fun createRaid(group: String, st: RaidSetting): Long

    fun createDisk(group: Long, range:Long, size: Int): String?

    fun writeAt(disk: String, data: String): Boolean

    fun readAt(index: Int, disk:String): String?

    fun deleteAt(disk: String): Boolean

    fun update(disk: String, data: String): Boolean

    fun startService()

    fun closeService()
}