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
    var catetegory: Category,
    var price: Float = 0.0f
)

data class Artist(
    @StringRes val name: Int,
    @StringRes val country: Int,
    @StringRes val birtyear: Int
)

enum class Category {
    Nature, Food, Sports, Misc, Architecture
}