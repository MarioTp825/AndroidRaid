package com.example.myapplication.raid

import com.example.myapplication.interfaces.Raid
import com.example.myapplication.repository.Postgres
import com.tepe.raid.utils.DataContainer

class One : Raid {
    private var sql = Postgres()
    private val raid = DataContainer.getInstance()

    init {
        sql.startService()
    }

    override fun add(data: String): Boolean {
        for (disk in raid.arrayDisk) {
            if (!sql.writeAt(disk.name, data)) return false
        }
        raid.next()
        return true
    }

    override fun deleteDisk(disk: String) = sql.deleteAt(disk)

    override fun readAt(index: Int): String {
        if (raid.arrayDisk.isEmpty()) return ""
        return sql.readAt(index, raid.arrayDisk[0].name) ?: ""
    }
}