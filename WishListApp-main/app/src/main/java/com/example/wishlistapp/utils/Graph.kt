package com.example.wishlistapp.utils

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp.App
import com.example.wishlistapp.database.AppDatabase
import com.example.wishlistapp.model.AppRepository

object Graph{
    lateinit var database: AppDatabase

    val appRepository by lazy {
        AppRepository(
            wishDao = database.getWishDao()
        )
    }

    fun provide(context: Context){
        this.database = Room.databaseBuilder(context, AppDatabase::class.java, "wish.db").build()
    }
}