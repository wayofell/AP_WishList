package com.mado.wishlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mado.wishlist.data.Wish
import com.mado.wishlist.data.WishRepository
import kotlinx.coroutines.launch

class WishViewModel(private val repository: WishRepository) : ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    val allWishes = repository.allWishes

    fun onWishTitleChanged(newString: String) {
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString: String) {
        wishDescriptionState = newString
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch {
            repository.insertWish(wish)
        }
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch {
            repository.updateWish(wish)
        }
    }
}