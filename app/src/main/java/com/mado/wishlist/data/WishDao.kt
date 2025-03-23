package com.mado.wishlist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWish(wish: Wish)

    @Update
    suspend fun updateWish(wish: Wish)

    @Delete
    suspend fun deleteWish(wish: Wish)

    @Query("SELECT * FROM wishes")
    fun getAllWishes(): Flow<List<Wish>>

    @Query("SELECT * FROM wishes WHERE id = :id")
    suspend fun getWishById(id: Long): Wish?
}