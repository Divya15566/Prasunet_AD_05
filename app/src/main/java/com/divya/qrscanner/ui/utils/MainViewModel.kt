package com.divya.qrscanner.ui.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.divya.qrscanner.data.Graph
import com.divya.qrscanner.data.QrCode
import com.divya.qrscanner.domain.repository.QrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val _qrRepository:QrRepository = Graph.qrRepository
):ViewModel() {
    private val _textResult = mutableStateOf("")
    val textResult = _textResult

    lateinit var history:Flow<List<QrCode>>

    init {
        viewModelScope.launch {
            history = _qrRepository.getHistory()
        }
    }

    fun addQr(qrCode: QrCode){
        viewModelScope.launch {
            _qrRepository.addQr(qrCode)
        }
    }

    fun changeResult(result:String){
        _textResult.value = result
    }
}