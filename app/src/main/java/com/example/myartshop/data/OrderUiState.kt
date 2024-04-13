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
    var category: com.example.myartshop.data.Category,
    var price: Float
)
data class Artist(
    @StringRes val nameResId: Int,
    @StringRes val countryResId: Int,
)

data class CartItem(
    val photo: Photo,
    val frameType: String = "mediuem",
    val frameWidth: Int = 2,
    val photoSize: String = "small",
    val price: Double = 2.2
)
data class OrderUiState(
    val listOfPhotos: List<Photo> = emptyList(),
    val cartItems: List<CartItem> = emptyList(),
    val price: String = ""
)