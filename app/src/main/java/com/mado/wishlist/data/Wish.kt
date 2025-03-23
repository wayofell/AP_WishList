package com.mado.wishlist.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishes")
data class Wish(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String = "",
    val description: String = ""
)