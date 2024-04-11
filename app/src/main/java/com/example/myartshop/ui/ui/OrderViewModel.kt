package com.example.myartshop.ui.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myartshop.data.OrderUiState
import com.example.myartshop.data.Photo
import com.example.myartshop.data.photos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


/** Prices for frames */
private const val PRICE_FOR_WOOD_FRAME = 10.00
private const val PRICE_FOR_PLASTIC_FRAME = 20.00
private const val PRICE_FOR_METAL_FRAME = 30.00

/** Additional cost frame size */
private const val PRICE_FOR_10MM_FRAME = 2.00
private const val PRICE_FOR_15MM_FRAME = 4.00
private const val PRICE_FOR_20MM_FRAME = 6.00

/** Prices for photo size */
private const val PRICE_FOR_SMALL_PHOTO = 50.00
private const val PRICE_FOR_MEDIUM_PHOTO = 100.00
private const val PRICE_FOR_LARGE_PHOTO = 200.00

/**
 * [OrderViewModel] holds information about a photo order in terms of size of photo size, frame and frame type.
 * It also knows how to calculate the total price based on these order details.
 */
class OrderViewModel : ViewModel() {

    // Properties
    val photoList = photos
    val selectedPhoto = mutableStateOf<Photo?>(null)
    val selectedFrameOptions = mutableStateOf<FrameOptions?>(null)
    val shoppingCart = mutableStateListOf<CartItem>()

    // Functions
    fun addToCart() {
        val selectedPhotoValue = selectedPhoto.value ?: return
        val selectedFrameOptionsValue = selectedFrameOptions.value ?: return

        val item = CartItem(
            photo = selectedPhotoValue,
            frameOptions = selectedFrameOptionsValue,
            price = calculatePrice(selectedPhotoValue, selectedFrameOptionsValue)
        )
        shoppingCart.add(item)
    }

    private fun calculatePrice(photo: Photo, frameOptions: FrameOptions): Float {
        // Implement logic to calculate price based on selected photo and frame options
        val photoPrice = photo.price
        val framePrice = frameOptions.price
        return photoPrice + framePrice
    }
}

// Data classes
data class FrameOptions(val material: String, val width: Int, val price: Float)
data class CartItem(val photo: Photo, val frameOptions: FrameOptions, val price: Float)


