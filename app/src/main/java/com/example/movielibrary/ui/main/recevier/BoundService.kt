package com.example.movielibrary.ui.main.recevier

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BoundService : Service() {
    private val binder: IBinder = ServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    internal inner class ServiceBinder : Binder() {
        private val service: BoundService get() = this@BoundService
    }
}