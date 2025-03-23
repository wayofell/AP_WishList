package com.mado.wishlist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wish::class], version = 1, exportSchema = false)
abstract class WishDatabase : RoomDatabase() {
    abstract fun wishDao(): WishDao

    companion object {
        @Volatile
        private var INSTANCE: WishDatabase? = null

        fun getDatabase(context: Context): WishDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WishDatabase::class.java,
                    "wish_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}