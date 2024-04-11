package com.example.myartshop.data

import androidx.annotation.DrawableRes
import com.example.myartshop.R
object DataSource {

    val paintingsList = listOf(
        Painting(R.drawable.image1, "Artist 1", "Category 1"),
        Painting(R.drawable.image2, "Artist 2", "Category 2"),
        Painting(R.drawable.image3, "Artist 3", "Category 1"),
        Painting(R.drawable.image4, "Artist 1", "Category 3"))

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

