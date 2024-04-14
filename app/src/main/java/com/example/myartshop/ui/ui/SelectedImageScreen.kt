package com.example.myartshop.ui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myartshop.R
import com.example.myartshop.data.CartItem
import com.example.myartshop.data.DataSource
import com.example.myartshop.data.Photo
import com.example.myartshop.ui.OrderViewModel


@Composable
fun SelectedPhotoScreen(
    photo: Photo?, viewModel: OrderViewModel, onAddToCartClicked: (Photo) -> Unit
) {
    val dataSource = DataSource
    var selectedFrameType by rememberSaveable { mutableStateOf("") }
    var selectedFrameWidth by rememberSaveable { mutableStateOf("") }
    var selectedPhotoSize by rememberSaveable { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(photo!!.title),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(bottom = 16.dp)
        ) {
            Box(Modifier.fillMaxSize()) {

                Image(
                    painter = painterResource(id = photo.imageResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                    )

            }
        }

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(R.string.details))
                Text(text = stringResource(R.string.various_details))
                Text(text = stringResource(R.string.frame_options))
                // Radio buttons for frame_type
                Row {
                    SelectOptionScreen(
                        options = dataSource.frame_type.map { stringResource(it) },
                        onSelectionChanged = { selectedFrameType = it }
                    )

                    // Radio buttons for photo_size_size
                    SelectOptionScreen(
                        options = dataSource.photo_size.map { stringResource(it) },
                        onSelectionChanged = { selectedPhotoSize = it }
                    )

                    // Radio buttons for frame_size
                    SelectOptionScreen(
                        options = dataSource.frame_size.map { stringResource(it) },
                        onSelectionChanged = { selectedFrameWidth = it }
                    )
                }
                val totalPrice = calculateTotalPrice(selectedFrameType, selectedFrameWidth, selectedPhotoSize)
                Text(text = stringResource(R.string.price_photo_and_frame ,"$totalPrice"))

                Button(
                    onClick = {
                        val cartItem = CartItem(
                            photo = photo,
                            frameType = selectedFrameType,
                            frameWidth = selectedFrameWidth,
                            photoSize = selectedPhotoSize,
                            price = photo.price,
                            frameAdditionalPrice = totalPrice
                        )
                        viewModel.addToCart(
                            selectedPhoto = photo,
                            frameType = selectedFrameType,
                            frameWidth = selectedFrameWidth,
                            photoSize = selectedPhotoSize,
                            price = photo.price,
                            frameAdditionalPrice = totalPrice
                        )
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Add to cart")
                }
            }
        }


        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Home")
        }


    }
}

@Composable
fun SelectOptionScreen(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(
                        item,
                        style = TextStyle(fontSize = 12.sp))
                }
            }
        }
    }
}

// Calculate total price based on photo price and frame choices
fun calculateTotalPrice(frameType: String, frameWidth: String, photoSize: String): Float {
    val frameTypePrice = when (frameType) {
        "wood" -> 50f
        "metal" -> 60f
        "plastic" -> 40f
        else -> 0f
    }

    val frameWidthPrice = when(frameWidth) {
        "10 mm" -> 10f
        "15 mm" -> 20f
        "20 mm" -> 30f
        else -> 0f
    }

    val photoSizePrice = when (photoSize) {
        "small" -> 20f
        "medium" -> 30f
        "large" -> 40f
        else -> 0f
    }

    return frameTypePrice + frameWidthPrice + photoSizePrice
}

/*
@Preview
@Composable
fun SelectedImagePreview() {
    MyArtShopTheme(darkTheme = false){
        SelectedPhotoScreen(
            listOfPhotos[0], onAddToCartClicked = {}
        )
    }
}
*/
