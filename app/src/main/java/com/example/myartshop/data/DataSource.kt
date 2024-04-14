package com.example.myartshop.data

import com.example.myartshop.R

object DataSource {

    val listOfartists = listOf(
        Artist(R.string.artist1_name, R.string.artist1_country),
        Artist(R.string.artist2_name, R.string.artist2_country),
        Artist(R.string.artist3_name, R.string.artist3_country),
        Artist(R.string.artist4_name, R.string.artist4_country),
        Artist(R.string.artist5_name, R.string.artist5_country),
        Artist(R.string.artist6_name, R.string.artist6_country))

    //Photo on webpage
    val listOfPhotos = listOf(
        Photo(1, R.string.title_1, R.drawable.image1, listOfartists[1], Category.Nature, 125.0f),
        Photo(2, R.string.title_2, R.drawable.image2, listOfartists[2], Category.Nature, 130.0f),
        Photo(3, R.string.title_3, R.drawable.image3, listOfartists[3], Category.Nature, 123.0f),
        Photo(4, R.string.title_4, R.drawable.image4, listOfartists[4], Category.Nature, 122.0f),
        Photo(5, R.string.title_5, R.drawable.image5, listOfartists[5], Category.Architecture, 150.0f),
        Photo(6, R.string.title_6, R.drawable.image6, listOfartists[0], Category.Nature, 100.0f),
        Photo(7, R.string.title_7, R.drawable.image7, listOfartists[1], Category.Nature, 89.0f),
        Photo(8, R.string.title_8, R.drawable.image8, listOfartists[2], Category.Nature, 90.0f),
        Photo(9, R.string.title_9, R.drawable.image9, listOfartists[3], Category.Nature, 170.0f),
        Photo(10, R.string.title_10, R.drawable.image10, listOfartists[4], Category.Nature, 160.0f),
        Photo(11, R.string.title_11, R.drawable.android_superhero1, listOfartists[5], Category.Misc, 144.0f),
        Photo(12, R.string.title_12, R.drawable.koda, listOfartists[0], Category.Misc, 111.0f),
        Photo(13, R.string.title_13, R.drawable.leroy, listOfartists[1], Category.Sports, 110.0f),
        Photo(14, R.string.title_14, R.drawable.lola, listOfartists[2], Category.Architecture, 109.0f),
        Photo(15, R.string.title_15, R.drawable.lemon_drink, listOfartists[3], Category.Food, 75.0f),
        Photo(16, R.string.title_16, R.drawable.lemon_tree, listOfartists[4], Category.Food, 70.0f)
    )


    val cartItems = listOfPhotos.map { photo ->
        CartItem(
            photo,
            frameType = "Wood", // Sett riktig rammetype her
            frameWidth = "10mm", // Sett riktig rammebredde her
            photoSize = "Medium", // Sett riktig fotosize her
            price = 23.2f, // Bruk en funksjon for å beregne prisen
            frameAdditionalPrice = 35.0f
        )
    }


    val frame_type = listOf(
        R.string.wood,
        R.string.metal,
        R.string.plastic

    )
    val photo_size = listOf(
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

