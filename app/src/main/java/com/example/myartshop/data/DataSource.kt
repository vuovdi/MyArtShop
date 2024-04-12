package com.example.myartshop.data

import androidx.annotation.DrawableRes
import com.example.myartshop.R
object DataSource {

    val paintingsList = listOf(
        Painting(R.drawable.image1, "Artist 1", "Category 1", "picture_name1"),
        Painting(R.drawable.image2, "Artist 2", "Category 2", "picture_name2"),
        Painting(R.drawable.image3, "Artist 3", "Category 1", "picture_name3"),
        Painting(R.drawable.image4, "Artist 1", "Category 3", "picture_name4"))

    // only example
    val cartItems = paintingsList.map { painting ->
        CartItem(
            painting = painting,
            frameType = "Wood", // Sett riktig rammetype her
            frameWidth = 2, // Sett riktig rammebredde her
            photoSize = "Medium", // Sett riktig fotosize her
            price = 2.2 // Bruk en funksjon for å beregne prisen
        )
    }
    val frame_type = listOf(
        R.string.wood,
        R.string.metal,
        R.string.plastic

    )
    val photo_size_size = listOf(
        R.string.small,
        R.string.medium,
        R.string.large
    )
    val frame_size = listOf(
        R.string.small_width,
        R.string.medium_width,
        R.string.large_width
    )
}

