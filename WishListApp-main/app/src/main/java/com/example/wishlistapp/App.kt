package com.example.wishlistapp

import android.app.Application
import com.example.wishlistapp.utils.Graph

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}