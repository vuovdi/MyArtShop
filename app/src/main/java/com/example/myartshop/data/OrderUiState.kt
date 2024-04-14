package com.example.myartshop.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.myartshop.R

/**
 * Data class that represents the current UI state
 */

enum class Category(val iconRes: Int) {
    Nature(R.drawable.ic_nature),
    Food(R.drawable.ic_food),
    Sports(R.drawable.ic_sports),
    Misc(R.drawable.ic_misc),
    Architecture(R.drawable.ic_architecture)
}

data class Photo(
    /** Unik ID til bildet **/
    val id: Long,
    @StringRes val title: Int,
    @DrawableRes
    var imageResId: Int,
    var artist: Artist,
    var category: Category,
    var price: Float
)
data class Artist(
    @StringRes val nameResId: Int,
    @StringRes val countryResId: Int,
)

data class CartItem(
    val photo: Photo,
    val frameType: String,
    val frameWidth: String,
    val photoSize: String,
    val price: Float,
    val frameAdditionalPrice: Float
)
data class OrderUiState(
    val listOfPhotos: List<Photo> = emptyList(),
    val cartItems: List<CartItem> = emptyList(),
    val price: String = ""
)