package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.dto.MyObjectBox
import io.objectbox.BoxStore

object Store {
    lateinit var box: BoxStore
        private set

    fun init(context: Context) {
        box = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}