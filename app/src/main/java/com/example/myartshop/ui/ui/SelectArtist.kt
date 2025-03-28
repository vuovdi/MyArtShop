package com.example.myartshop.ui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddReaction
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myartshop.data.Artist
import com.example.myartshop.data.DataSource.listOfPhotos
import com.example.myartshop.data.DataSource.listOfartists
import com.example.myartshop.data.Photo


@Composable
fun SelectArtistPage(
    artistList: List<Artist>,
    onChosenArtistClicked: (Artist) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        // Display each artist card in a column
        artistList.forEach { artist ->
            ArtistCard(artist = artist, photos = listOfPhotos.filter { it.artist == artist }, onChosenArtistClicked = onChosenArtistClicked, modifier)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ArtistCard(artist: Artist, photos: List<Photo>, onChosenArtistClicked: (Artist) -> Unit, modifier: Modifier) {
    val numberOfPhotosPerArtist = calculateNumberOfPhotosPerArtist(photos)
    val mostExpensivePhotoPerArtist = findMostExpensivePhotoPerArtist(photos)

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = { onChosenArtistClicked(artist) }
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


    SelectArtistPage(artistList = listOfartists, onChosenArtistClicked = {})
}