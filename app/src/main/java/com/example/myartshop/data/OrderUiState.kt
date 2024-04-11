package com.example.myartshop.data

/**
 * Data class that represents the current UI state
 */
data class OrderUiState(
    /** Flavor of the cupcakes in the order (such as "Chocolate", "Vanilla", etc..) */
    val frame_width: String = "",
    /** Selected date for pickup (such as "Jan 1") */
    val photo_size: String = "",
    /** Total price for the order */
    val frame_type: String = "",
)