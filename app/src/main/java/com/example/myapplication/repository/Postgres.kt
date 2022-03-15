package com.example.myapplication.repository

import com.example.myapplication.dto.*
import com.example.myapplication.interfaces.Implementation
import com.example.myapplication.utils.Store

class Postgres : Implementation {
    private var statement = Store.box

    override fun createDisk(group: Long, range: Long, size: Int): String {
        val name = "disk_${box(DiskSetting::class.java).all.size + 1}"
        val tableHolder = DiskHolder(
            Id = 0,
            name = name,
            tag = group.toString(),
            pointer = -1
        )

        for (num in 0 until size)
            tableHolder.data.add(DataHolder(0, num))

        val id = statement.boxFor(DiskHolder::class.java).put(tableHolder)
        val raid = statement.boxFor(RaidHolder::class.java)
        val rd = raid[group]
        statement.boxFor(DiskSetting::class.java).put(
            DiskSetting(
                0,
                diskId = id,
                order = rd.diskNumber++,
                group = group,
                range = range,
            )
        )
        raid.put(rd)

        return name
    }

    override fun createRaid(group: String, st: RaidSetting): Long {
        val raid = RaidHolder(
            0,
            name = group,
            type = st.ordinal
        )
        return statement.boxFor(RaidHolder::class.java).put(raid)
    }

    override fun writeAt(disk: String, data: String): Boolean {
        val dk = statement.boxFor(DiskSetting::class.java).get(0)
        val holder = statement.boxFor(DiskHolder::class.java)
        val dt = holder[dk.diskId]
        (dt.data[++dt.pointer] as DataHolder).data = data
        return holder.put(dt) == 0L
    }

    override fun readAt(index: Int, disk: String): String? {
        val dk = statement.boxFor(DiskSetting::class.java).get(0)
        return if (!dk.state) null
        else (statement.boxFor(DiskHolder::class.java)[dk.diskId].data[index] as DataHolder).data
    }

    override fun deleteAt(disk: String): Boolean {
        val dk = statement.boxFor(DiskSetting::class.java).get(0)
        dk.state = false
        return statement.boxFor(DiskHolder::class.java).remove(dk.diskId)
    }

    override fun update(disk: String, data: String): Boolean {
        return writeAt(disk, data)
    }

    override fun startService() {
        println("Conexión establecida")
    }

    override fun closeService() {
        println("Conexión cerrada")
    }

    private fun box(cl: Class<*>) = statement.boxFor(cl)

}