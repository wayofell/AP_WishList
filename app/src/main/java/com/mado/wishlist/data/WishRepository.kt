package com.mado.wishlist.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {
    val allWishes: Flow<List<Wish>> = wishDao.getAllWishes()

    suspend fun insertWish(wish: Wish) {
        wishDao.insertWish(wish)
    }

    suspend fun updateWish(wish: Wish) {
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDao.deleteWish(wish)
    }

    suspend fun getWishById(id: Long): Wish? {
        return wishDao.getWishById(id)
    }
}