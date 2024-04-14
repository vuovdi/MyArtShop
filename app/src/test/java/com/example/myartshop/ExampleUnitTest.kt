package com.example.myartshop

import com.example.myartshop.ui.OrderViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class OrderViewModelTest {
    private val viewModel = OrderViewModel()
    @Test
    fun orderViewModel_photoAddedToCart() {
        var currentOrderUiState = viewModel.uiState.value
    }
}