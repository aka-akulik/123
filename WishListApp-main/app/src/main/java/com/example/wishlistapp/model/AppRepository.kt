package com.example.wishlistapp.model

import kotlinx.coroutines.flow.Flow

class AppRepository(
    val wishDao: WishDao
) {
    suspend fun addWish(wishModel: WishModel){
        wishDao.addWish(wishModel)
    }
    suspend fun getAllWishes(): Flow<List<WishModel>>{
        return wishDao.selectAllWishes()
    }
    suspend fun updateWish(wishModel: WishModel){
        return wishDao.updateWish(wishModel)
    }

    suspend fun deleteWish(wishModel: WishModel) {
        return wishDao.deleteWish(wishModel)
    }
}