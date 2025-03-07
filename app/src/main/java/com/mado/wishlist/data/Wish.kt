package com.mado.wishlist.data

data class Wish(
    val id: Long = 0L,
    val title: String = "",
    val description: String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(title = "Google Watch 2", description = "An android Watch"),
        Wish(title = "Ipad M4", description = "Meka Meka Meka wanna buy Ipad"),
        Wish(title = "Ipad Air M5", description = "Eldiyar wanna buy Ipad"),
        Wish(title = "Ipad Pro M6", description = "Bekbol wanna buy Ipad"),
        Wish(title = "Ipad Ultra M7", description = "Alexandr wanna buy Ipad"),
        Wish(title = "Ipad Mega M8", description = "Danila wanna buy Ipad"),
    )
}