package com.example.wishlistapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wishlistapp.contact.Contact
import com.example.wishlistapp.contact.ContactDao
import com.example.wishlistapp.model.WishDao
import com.example.wishlistapp.model.WishModel

@Database(
    entities = [WishModel::class, Contact::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun getWishDao(): WishDao
    abstract fun getContactDao(): ContactDao
}