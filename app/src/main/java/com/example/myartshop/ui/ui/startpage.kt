package com.example.myartshop.ui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myartshop.R
import com.example.myartshop.data.CartItem
import com.example.myartshop.data.cartItemsExample
import com.example.myartshop.ui.OrderViewModel
import com.example.myartshop.ui.ui.theme.MyArtShopTheme

@Composable
fun StartPageScreen(
    viewModel: OrderViewModel,
    shoppingCart: List<CartItem>,
    /**0 : artist, 1 : category + onClick*/
    onArtistButtonClicked: () -> Unit,
    onCategoryButtonClicked: () -> Unit,
    onPayButtonClicked: () -> Unit,
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

//            ShoppingInfo(
//                viewModel = viewModel,
//                shoppingCart = shoppingCart,
//                onRemoveItem = { cartItem -> viewModel.removeFromCart(cartItem) }
//            )
//            ThemeToggleButton(useDarkTheme = darkTheme, onToggle = setDarkTheme)

            Button(
                modifier = modifier
//                        .padding(dimensionResource(R.dimen.padding_medium))
                    .padding(10.dp)
                    .width(180.dp),
                onClick = onPayButtonClicked
            ) {
                Text(
                    text = "joojojoj",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

    }
}


@Composable
fun ShoppingInfo(
    viewModel: OrderViewModel,
    shoppingCart: List<CartItem>,
    onRemoveItem: (CartItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val pictures = 0
    val sumPictures by remember { mutableIntStateOf(pictures) }
    val totalprice = 0

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(R.string.chosen_photos, pictures),
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(5.dp)
        )
        Text(
            text = stringResource(R.string.total_price, totalprice),
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(5.dp)
        )

        LazyColumn(modifier = modifier.fillMaxWidth())
        {
            items(shoppingCart) { cartItem ->
                ShoppingCartItems(
                    imageRes = cartItem.painting.imageResId,
                    title = cartItem.painting.name,
                    frameChoices = cartItem.frameType,
                    price = cartItem.price.toString(),
                    cartItem = cartItem,
                    onRemoveButtonClick = { onRemoveItem(cartItem) } // Send med onRemoveItem-funksjonen
                )
            }
        }
    }
}


@Composable
fun ShoppingCartItems(
    imageRes: Int = R.drawable.image1,
    title: String = "test",
    frameChoices: String = "tre",
    price: String = "3kr",
    cartItem: CartItem,
    onRemoveButtonClick: (CartItem) -> Unit
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
//            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(2f)) {
                Text(text = title)
                Text(text = price)
                Text(text = frameChoices)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { onRemoveButtonClick(cartItem) },
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
    val viewModel: OrderViewModel = viewModel()

    MyArtShopTheme(darkTheme = false) {
        MyArtShopTheme {
            StartPageScreen(
                viewModel = viewModel,
                shoppingCart = cartItemsExample,
                onArtistButtonClicked = {}, onCategoryButtonClicked = {}, onPayButtonClicked = {})
        }
//        MyArtShopTheme {
//            StartPageScreen(
//                viewModel = viewModel,
//                shoppingCart = viewModel.shoppingCart.value,
//                onArtistButtonClicked = {}, onCategoryButtonClicked = {})
//        }
    }
}

