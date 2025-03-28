package com.example.myartshop.ui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myartshop.data.Category
import com.example.myartshop.data.DataSource.listOfPhotos
import com.example.myartshop.data.Photo

@Composable
fun CategoriesPage(
    categories: List<Category>,
    photos: List<Photo>,
    onChosenCategoryClicked: (Category) -> Unit,
    modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(categories) { category ->
            CategoryCard(category = category, photos = photos.filter { it.category == category }, onChosenCategoryClicked = onChosenCategoryClicked, modifier = modifier)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(category: Category, photos: List<Photo>, onChosenCategoryClicked: (Category) -> Unit, modifier: Modifier) {
    val numberOfPhotosPerCategory = calculateNumberOfPhotosPerCategory(photos)
    val mostExpensivePhotoPerCategory = findMostExpensivePhotoPerCategory(photos)

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = { onChosenCategoryClicked(category) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Icon on the far left
            Icon(
                painter = painterResource(id = category.iconRes),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = category.name, fontWeight = FontWeight.Bold)
                Text(text = "Number of photos: ${numberOfPhotosPerCategory[category] ?: 0}")
                Text(text = "Most expensive photo: ${mostExpensivePhotoPerCategory[category] ?: 0.0f}")
                Text(text = "Most popular photo: ...")
            }

        }
    }
}

//Function to calculate number of photos per category
fun calculateNumberOfPhotosPerCategory(photos: List<Photo>): Map<Category, Int> {
    val photoCountMap = mutableMapOf<Category, Int>().withDefault { 0 }

    for (photo in photos) {
        val category = photo.category
        photoCountMap[category] = photoCountMap.getValue(category) + 1
    }

    return photoCountMap
}

//Function to fin most expensive photo in category
fun findMostExpensivePhotoPerCategory(photos: List<Photo>): Map<Category, Float> {
    val mostExpensivePhotoMap = mutableMapOf<Category, Float>().withDefault { 0.0f }

    for (photo in photos) {
        val category = photo.category
        val currentMaxPrice = mostExpensivePhotoMap.getValue(category)
        val newPrice = photo.price
        if (newPrice > currentMaxPrice) {
            mostExpensivePhotoMap[category] = newPrice
        }
    }

    return mostExpensivePhotoMap
}

@Preview
@Composable
fun SelectCategoryPagePreview() {
    val yourPhotoList = listOfPhotos
//    CategoriesPage(categories = Category.entries, photos = yourPhotoList)
}