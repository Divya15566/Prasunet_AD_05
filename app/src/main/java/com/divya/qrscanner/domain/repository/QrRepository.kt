package com.divya.qrscanner.domain.repository

import com.divya.qrscanner.data.QrCode
import com.divya.qrscanner.data.QrDao
import kotlinx.coroutines.flow.Flow

class QrRepository(private val qrDao:QrDao) {

    suspend fun addQr(qrCode: QrCode){
        qrDao.addQr(qrCode)
    }

    fun getHistory():Flow<List<QrCode>> = qrDao.getHistory()

}