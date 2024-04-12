package com.example.myartshop.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myartshop.data.Artist
import com.example.myartshop.data.CartItem
import com.example.myartshop.data.DataSource
import com.example.myartshop.data.OrderUiState
import com.example.myartshop.data.Painting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    private val paintingsList = DataSource.paintingsList
    val selectedPhoto = mutableStateOf<Painting?>(null)
    val selectedFrameOptions = mutableStateOf<Pair<String, Int>?>(null)
    val shoppingCart = mutableStateOf<List<CartItem>>(emptyList())
    private var _selectedArtist: Artist? = null
    val selectedArtist: Artist?
        get() = _selectedArtist

    fun setSelectedArtist(artist: Artist) {
        _selectedArtist = artist
    }
    init {
        updatePaintingsList(DataSource.paintingsList)
    }



    private fun updatePaintingsList(paintingsList: List<Painting>) {
        _uiState.value = _uiState.value.copy(paintingsList = paintingsList)
    }

    fun selectPhoto(painting: Painting) {
        selectedPhoto.value = painting
    }

    fun addToCart(frameType: String, frameWidth: Int, photoSize: String, price: Double) {
        selectedPhoto.value?.let {painting ->
            val cartItem = CartItem(painting, frameType, frameWidth, photoSize, price)
            shoppingCart.value = shoppingCart.value + cartItem
        }
    }


    fun removeFromCart(cartItem: CartItem) {
        // Få den gjeldende listen over handlekurvartikler
        val currentCartItems = shoppingCart.value.toMutableList()

        // Finn indeksen til det aktuelle elementet i handlekurven
        val index = currentCartItems.indexOfFirst { it == cartItem }

        // Hvis elementet er funnet i handlekurven, fjern det
        if (index != -1) {
            currentCartItems.removeAt(index)
            shoppingCart.value = currentCartItems
        }
    }
}




//class OrderViewModel : ViewModel() {
//
//    // Properties
//    val photoList = photos
//    val selectedPhoto = mutableStateOf<Photo?>(null)
//    val selectedFrameOptions = mutableStateOf<FrameOptions?>(null)
//    val shoppingCart = mutableStateListOf<CartItem>()
//
//    // Functions
//    fun addToCart() {
//        val selectedPhotoValue = selectedPhoto.value ?: return
//        val selectedFrameOptionsValue = selectedFrameOptions.value ?: return
//
//        val item = CartItem(
//            photo = selectedPhotoValue,
//            frameOptions = selectedFrameOptionsValue,
//            price = calculatePrice(selectedPhotoValue, selectedFrameOptionsValue)
//        )
//        shoppingCart.add(item)
//    }
//
//    private fun calculatePrice(photo: Photo, frameOptions: FrameOptions): Float {
//        // Implement logic to calculate price based on selected photo and frame options
//        val photoPrice = photo.price
//        val framePrice = frameOptions.price
//        return photoPrice + framePrice
//    }
//}
//
//// Data classes
//data class FrameOptions(val material: String, val width: Int, val price: Float)
//data class CartItem(val photo: Photo, val frameOptions: FrameOptions, val price: Float)


