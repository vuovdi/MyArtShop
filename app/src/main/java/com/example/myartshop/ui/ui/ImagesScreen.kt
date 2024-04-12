package com.example.myartshop.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myartshop.data.Category
import com.example.myartshop.data.Photo
import com.example.myartshop.data.photos
import com.example.myartshop.ui.ui.theme.MyArtShopTheme

@Composable
fun PhotoGridScreen(photos: List<Photo>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        itemsIndexed(photos) { index, photo ->
            PhotoItem(photo = photo)
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(photo.title),
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Image(
            painter = painterResource(id = photo.imageResId),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun PhotoGridScreenPreview() {
    val yourPhotoList = photos
    PhotoGridScreen(photos = yourPhotoList)
}




































