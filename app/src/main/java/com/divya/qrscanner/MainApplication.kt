package com.divya.qrscanner

import android.app.Application
import com.divya.qrscanner.data.Graph

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}