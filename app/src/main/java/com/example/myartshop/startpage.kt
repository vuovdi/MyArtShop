package com.example.myartshop

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myartshop.ui.ui.theme.MyArtShopTheme

@Composable
fun ArtShopApp(modifier: Modifier = Modifier) {
    val isDarkTheme = isSystemInDarkTheme()
    val (darkTheme, setDarkTheme) = remember { mutableStateOf(isDarkTheme) }

    MyArtShopTheme (darkTheme = darkTheme) {
        Scaffold(
            topBar = {
                ArtShopTopAppBar()
            }
        ) { contentPadding ->
            Column(modifier = modifier.padding(contentPadding)) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.choose_category),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = modifier
                            .padding(dimensionResource(R.dimen.padding_medium))
                            .width(180.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(
                            text = stringResource(R.string.button_name_artist),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    Button(
                        modifier = modifier
                            .padding(dimensionResource(R.dimen.padding_medium))
                            .width(180.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(
                            text = stringResource(R.string.button_name_category),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.shopping_cart),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
                ShoppingInfo()
                ThemeToggleButton(useDarkTheme = darkTheme, onToggle = setDarkTheme)
            }
        }
    }
}

@Composable
fun ThemeToggleButton(useDarkTheme: Boolean, onToggle: (Boolean) -> Unit) {
    Button(onClick = {onToggle(!useDarkTheme) }) {
        Text(text = if (useDarkTheme) stringResource(R.string.light_mode) else
            stringResource(R.string.dark_mode))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtShopTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.artshopicon2),
                    contentDescription = stringResource(R.string.app_logo),
                    modifier = modifier
                        .size(50.dp)
                        .padding(dimensionResource(R.dimen.padding_small)),
                )
                Text(
                    text = stringResource(R.string.app_name),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )


            }
        }
    )
}

@Composable
fun ShoppingInfo(modifier: Modifier = Modifier) {
    val pictures = 0
    val sumPictures by remember { mutableIntStateOf(pictures) }
    val totalprice = 0

    Text(
        text = stringResource(R.string.chosen_photos, pictures),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    )
    Text(
        text = stringResource(R.string.total_prize, totalprice),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    )

    // show selected photos in cart
}

@Preview
@Composable
fun MyArtShopPreview() {
    MyArtShopTheme(darkTheme = false) {
        ArtShopApp()
    }
}

@Preview
@Composable
fun ArtShopDarkPreview() {
    MyArtShopTheme(darkTheme = true) {
        ArtShopApp()
    }
}