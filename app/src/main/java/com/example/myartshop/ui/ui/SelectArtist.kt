package com.example.myartshop.ui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddReaction
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myartshop.R
import com.example.myartshop.data.Artist
import com.example.myartshop.data.Photo
import com.example.myartshop.data.artist1
import com.example.myartshop.data.artist2
import com.example.myartshop.data.artist3
import com.example.myartshop.data.artist4
import com.example.myartshop.data.artist5
import com.example.myartshop.data.artist6
import com.example.myartshop.data.photos

@Composable
fun SelectArtistPage(
    artistList: List<Artist>,
    modifier: Modifier = Modifier
) {
    Column {
        // Display each artist card in a column
        artistList.forEach { artist ->
            ArtistCard(artist = artist, photos = photos.filter { it.artist == artist })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ArtistCard(artist: Artist, photos: List<Photo>) {
    val numberOfPhotosPerArtist = calculateNumberOfPhotosPerArtist(photos)
    val mostExpensivePhotoPerArtist = findMostExpensivePhotoPerArtist(photos)

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Make icon clickable
            Icon(
                Icons.Rounded.AddReaction,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = stringResource(id = artist.nameResId), fontWeight = FontWeight.Bold)
                Text(text = "Number of photos: ${numberOfPhotosPerArtist[artist] ?: 0}")
                Text(text = "Most expensive photo: ${mostExpensivePhotoPerArtist[artist] ?: 0.0f}")
                Text(text = "Additional text here...")
            }
        }
    }
}

//Function to calculate number of photos per artist
fun calculateNumberOfPhotosPerArtist(photos: List<Photo>): Map<Artist, Int> {
    val numberOfPhotosPerArtist = mutableMapOf<Artist, Int>()

    for (photo in photos) {
        val artist = photo.artist
        numberOfPhotosPerArtist[artist] = numberOfPhotosPerArtist.getOrDefault(artist, 0) + 1
    }

    return numberOfPhotosPerArtist
}

//function to find most expensive photo
fun findMostExpensivePhotoPerArtist(photos: List<Photo>): Map<Artist, Float> {
    val mostExpensivePhotoPerArtist = mutableMapOf<Artist, Float>()

    for (photo in photos) {
        val artist = photo.artist
        val currentPrice = photo.price
        val currentMaxPrice = mostExpensivePhotoPerArtist.getOrDefault(artist, Float.MIN_VALUE)

        if (currentPrice > currentMaxPrice) {
            mostExpensivePhotoPerArtist[artist] = currentPrice
        }
    }

    return mostExpensivePhotoPerArtist
}

@Preview
@Composable
fun SelectArtistPagePreview() {
    val artistList = listOf(artist1, artist2, artist3, artist4, artist5, artist6)

    SelectArtistPage(artistList = artistList)
}