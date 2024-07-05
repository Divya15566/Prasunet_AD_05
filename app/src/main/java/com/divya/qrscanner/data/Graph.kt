package com.divya.qrscanner.data

import android.content.Context
import androidx.room.Room
import com.divya.qrscanner.domain.repository.QrRepository

object Graph {
    lateinit var database: QrDatabase

    val qrRepository by lazy {
        QrRepository(qrDao = database.qrDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(
            context = context,
            klass = QrDatabase::class.java,
            name = "qr.db"
        ).build()
    }
}