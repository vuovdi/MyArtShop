package com.example.myartshop.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.myartshop.R
import com.example.myartshop.data.Artist
import com.example.myartshop.data.CartItem
import com.example.myartshop.data.Category
import com.example.myartshop.data.DataSource
import com.example.myartshop.data.OrderUiState
import com.example.myartshop.data.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    private val listOfPhotos = DataSource.listOfPhotos
    val selectedFrameOptions = mutableStateOf<Triple<String, String, String>?>(null)
    val shoppingCart = mutableStateOf<List<CartItem>>(emptyList())
    private var _selectedArtist: Artist? = null
    private var _selectedCategory: Category? = null
    private var _selectedPhoto: Photo? = null

    val selectedArtist: Artist?
        get() = _selectedArtist

    val selectedCategory: Category?
        get() = _selectedCategory

    val selectedPhoto: Photo?
        get() = _selectedPhoto

    fun setSelectedArtist(artist: Artist) {
        _selectedArtist = artist
    }

    fun setSelectedCategory(category: Category) {
        _selectedCategory = category
    }

    fun setSelectedPhoto(photo: com.example.myartshop.data.Photo) {
        _selectedPhoto = photo
    }
    init {
        updatePhotosList(DataSource.listOfPhotos)
    }



    private fun updatePhotosList(listOfPhotos: List<com.example.myartshop.data.Photo>) {
        _uiState.value = _uiState.value.copy(listOfPhotos = listOfPhotos)
    }


    fun addToCart(
        selectedPhoto: Photo?,
        frameType: String,
        frameWidth: String,
        photoSize: String,
        price: Float,
        frameAdditionalPrice: Float
    ) {
        val photo = selectedPhoto ?: return  // If selectedPhoto is null, return early
        val cartItem = CartItem(photo, frameType, frameWidth, photoSize, price, frameAdditionalPrice)
        shoppingCart.value += cartItem
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

    fun updateFrameOptions(frameType: String, frameWidth: String, photoSize: String) {
        selectedFrameOptions.value = Triple(frameType, frameWidth, photoSize)
    }

    fun sumPrice(): Float {
        var totalPrice = 0f
        shoppingCart.value.forEach {element ->
            totalPrice =  element.frameAdditionalPrice
        }
        return totalPrice
    }

    fun resetOrder() {
        shoppingCart.value = emptyList()
    }


}



