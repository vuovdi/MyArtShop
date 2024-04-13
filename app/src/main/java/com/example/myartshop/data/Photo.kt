package com.example.myartshop.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.myartshop.R

data class Photo(
    /** Unik ID til bildet **/
    val id: Long,
    @StringRes val title: Int,
    @DrawableRes
    var imageResId: Int,
    var artist: Artist,
    var category: Category,
    var price: Float
)

data class Artist(
    @StringRes val nameResId: Int,
    @StringRes val countryResId: Int,
    @StringRes val birhtyearResId: Int
)

enum class Category(val iconRes: Int) {
    Nature(R.drawable.ic_nature),
    Food(R.drawable.ic_food),
    Sports(R.drawable.ic_sports),
    Misc(R.drawable.ic_misc),
    Architecture(R.drawable.ic_architecture)
}

//Artists
val artist1 = Artist(R.string.artist1_name, R.string.artist1_country, R.string.artist1_birth)
val artist2 = Artist(R.string.artist2_name, R.string.artist2_country, R.string.artist2_birth)
val artist3 = Artist(R.string.artist3_name, R.string.artist3_country, R.string.artist3_birth)
val artist4 = Artist(R.string.artist4_name, R.string.artist4_country, R.string.artist4_birth)
val artist5 = Artist(R.string.artist5_name, R.string.artist5_country, R.string.artist5_birth)
val artist6 = Artist(R.string.artist6_name, R.string.artist6_country, R.string.artist6_birth)

//Photo on webpage
val photos = listOf(
    Photo(1, R.string.title_1, R.drawable.image1, artist1, Category.Nature, 125.0f),
    Photo(2, R.string.title_2, R.drawable.image2, artist1, Category.Nature, 130.0f),
    Photo(3, R.string.title_3, R.drawable.image3, artist1, Category.Nature, 123.0f),
    Photo(4, R.string.title_4, R.drawable.image4, artist1, Category.Nature, 122.0f),
    Photo(5, R.string.title_5, R.drawable.image5, artist1, Category.Architecture, 150.0f),
    Photo(6, R.string.title_6, R.drawable.image6, artist1, Category.Nature, 100.0f),
    Photo(7, R.string.title_7, R.drawable.image7, artist1, Category.Nature, 89.0f),
    Photo(8, R.string.title_8, R.drawable.image8, artist1, Category.Nature, 90.0f),
    Photo(9, R.string.title_9, R.drawable.image9, artist1, Category.Nature, 170.0f),
    Photo(10, R.string.title_10, R.drawable.image10, artist1, Category.Nature, 160.0f),
    Photo(11, R.string.title_11, R.drawable.android_superhero1, artist2, Category.Misc, 144.0f),
    Photo(12, R.string.title_12, R.drawable.koda, artist3, Category.Misc, 111.0f),
    Photo(13, R.string.title_13, R.drawable.leroy, artist4, Category.Sports, 110.0f),
    Photo(14, R.string.title_14, R.drawable.lola, artist5, Category.Architecture, 109.0f),
    Photo(15, R.string.title_15, R.drawable.lemon_drink, artist6, Category.Food, 75.0f),
    Photo(16, R.string.title_16, R.drawable.lemon_tree, artist6, Category.Food, 70.0f)
)

val cartItemsExample = listOf(
    CartItem(
        painting = Painting(R.drawable.image1, "Sunflower", "Van Gogh", "1888"),
        frameType = "Wooden",
        frameWidth = 2,
        photoSize = "Large",
        price = 150.0
    ),
    CartItem(
        painting = Painting(R.drawable.android_superhero1, "Starry Night", "Van Gogh", "1889"),
        frameType = "Metal",
        frameWidth = 1,
        photoSize = "Medium",
        price = 200.0
    ),
    CartItem(
        painting = Painting(R.drawable.image9, "The Last Supper", "Leonardo da Vinci", "1498"),
        frameType = "Plastic",
        frameWidth = 3,
        photoSize = "Small",
        price = 300.0
    ),
    CartItem(
        painting = Painting(R.drawable.image2, "Mona Lisa", "Leonardo da Vinci", "1503"),
        frameType = "Wooden",
        frameWidth = 2,
        photoSize = "Large",
        price = 500.0
    ),
    CartItem(
        painting = Painting(R.drawable.leroy, "Girl with a Pearl Earring", "Johannes Vermeer", "1665"),
        frameType = "Metal",
        frameWidth = 1,
        photoSize = "Medium",
        price = 250.0
    )
)
