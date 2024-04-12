package com.example.myartshop.data

import androidx.annotation.DrawableRes

/**
 * Data class that represents the current UI state
 */
data class Painting(
    @DrawableRes val imageResId: Int,
    val artist: String,
    val category: String,
    val name: String,

)

data class CartItem(
    val painting: Painting,
    val frameType: String,
    val frameWidth: Int,
    val photoSize: String,
    val price: Double
)
data class OrderUiState(
    val paintingsList: List<Painting> = emptyList(),
    val cartItems: List<CartItem> = emptyList(),
    val price: String = ""
)