package com.example.myartshop.ui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myartshop.R
import com.example.myartshop.ui.ui.theme.MyArtShopTheme

@Composable
fun StartPageScreen(
    /**0 : artist, 1 : category + onClick*/
    onArtistButtonClicked: () -> Unit,
    onCategoryButtonClicked: () -> Unit,
    modifier: Modifier = Modifier) {
    val isDarkTheme = isSystemInDarkTheme()
    val (darkTheme, setDarkTheme) = remember { mutableStateOf(isDarkTheme) }

    MyArtShopTheme (darkTheme = darkTheme) {
        Column(
            modifier = modifier,
//            verticalArrangement = Arrangement.SpaceBetween
        ) {
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
//                        .padding(dimensionResource(R.dimen.padding_medium))
                        .padding(10.dp)
                        .width(180.dp),
                    onClick = onArtistButtonClicked
                ) {
                    Text(
                        text = stringResource(R.string.artist),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Button(
                    modifier = modifier
//                        .padding(dimensionResource(R.dimen.padding_medium))
                        .padding(10.dp)
                        .width(180.dp),
                    onClick = onCategoryButtonClicked
                ) {
                    Text(
                        text = stringResource(R.string.category),
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
//            ThemeToggleButton(useDarkTheme = darkTheme, onToggle = setDarkTheme)
        }

    }
}


@Composable
fun ShoppingInfo(modifier: Modifier = Modifier) {
    val pictures = 0
    val sumPictures by remember { mutableIntStateOf(pictures) }
    val totalprice = 0

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround
    ){
    Text(
        text = stringResource(R.string.chosen_photos, pictures),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
//            .padding(dimensionResource(R.dimen.padding_small))
            .padding(5.dp)
    )
    Text(
        text = stringResource(R.string.total_price, totalprice),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
//            .padding(dimensionResource(R.dimen.padding_small))
            .padding(5.dp)
    )


    ShoppingCartItems(onRemoveButtonClick = {})
    }
}

@Composable
fun ShoppingCartItems(
//    imageRes: Int,
    title: String = "test",
    frameChoices: String = "tre",
    price: String = "3kr",
    onRemoveButtonClick: () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.image1),
                contentDescription = "yapp yapp yapp",
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(2f)) {
                Text(text = "yap")
                Text(text = "sap")
                Text(text = "dap")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.Top)// Plasserer knappen øverst til høyre
            ) {
                Text(text = stringResource(R.string.Remove))
            }
            Spacer(modifier = Modifier.width(16.dp))
        }

/** DETTE FUNKA IKKE :( SKJØNNER IKKE HVORFOR, MEN FLYTTA HARD KODEDE VERSJON LITT OPP (YAP SAP PAP)
 * */
//            Column(
//                modifier = Modifier.weight(2f)
//            ) {
//                Text(text = stringResource(R.string.Prize, price))
//                Text(text = stringResource(R.string.Frame_choices, frameChoices))
//                Text(text = stringResource(R.string.Prize, price))
//            }

        }
    }

@Preview
@Composable
fun MyArtShopPreview() {
    MyArtShopTheme(darkTheme = false) {
        MyArtShopTheme {
            StartPageScreen(onArtistButtonClicked = {}, onCategoryButtonClicked = {})
        }
    }
}

