package com.example.wishlistapp.model

import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Entity("wish")
data class WishModel(
    @PrimaryKey(true)
    val id: Long = 0,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("description")
    val description: String,
)

@Dao
interface WishDao{
    @Insert
    abstract fun addWish(wishModel: WishModel)

    @Delete
    abstract fun deleteWish(wishModel: WishModel)

    @Query("SELECT * FROM wish")
    abstract fun selectAllWishes(): Flow<List<WishModel>>

    @Update
    abstract fun updateWish(wishModel: WishModel)
}
