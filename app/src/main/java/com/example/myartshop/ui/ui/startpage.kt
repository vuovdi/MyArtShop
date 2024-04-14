package com.example.myartshop.ui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.runtime.mutableStateOf

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myartshop.R
import com.example.myartshop.data.CartItem
import com.example.myartshop.data.DataSource
//import com.example.myartshop.data.DataSource.cartItemsExample
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

            ShoppingInfo(
                viewModel = viewModel,
                onPayButtonClicked,
                shoppingCart = shoppingCart,
                onRemoveItem = { cartItem -> viewModel.removeFromCart(cartItem) }
            )


        }

    }
}


@Composable
fun ShoppingInfo(
    viewModel: OrderViewModel,
    onPayButtonClicked: () -> Unit,
    shoppingCart: List<CartItem>,
    onRemoveItem: (CartItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val pictures = 0
    val sumPictures by remember { mutableIntStateOf(shoppingCart.size) }



    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            modifier = modifier
//                        .padding(dimensionResource(R.dimen.padding_medium))
                .padding(10.dp)
                .width(180.dp),
            onClick = onPayButtonClicked
        ) {
            Text(
                text = "Payment",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Text(
            text = stringResource(R.string.chosen_photos, sumPictures),
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(5.dp)
        )
        Text(
//            text = stringResource(R.string.total_price, viewModel.sumPrice()) Funksjonen er i viewModel, men funker ikke,
            text = stringResource(R.string.total_price),
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(5.dp)
        )

        LazyColumn(modifier = modifier.fillMaxWidth())
        {
            items(shoppingCart) { cartItem ->
                ShoppingCartItems(
                    imageRes = cartItem.photo.imageResId,
                    title = cartItem.photo.title,
                    price = cartItem.price,
                    frameAdditionalPrice = cartItem.price,
                    cartItem = cartItem
                ) { onRemoveItem(cartItem) } // Send med onRemoveItem-funksjonen

            }
        }
    }
}


@Composable
fun ShoppingCartItems(
    imageRes: Int,
    title: Int,
    price: Float,
    frameAdditionalPrice: Float,
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
                contentDescription = "oki",
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            val totalPrice = price+frameAdditionalPrice
            val frameChoices = "${cartItem.frameType}, ${cartItem.frameWidth}, ${cartItem.photoSize}"
            Column(modifier = Modifier
                .weight(2f)
                .fillMaxSize()) {
                Text(
                    text = stringResource(title),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$totalPrice",
                    fontSize = 12.sp
                )
                Text(
                    text = frameChoices,
                    fontSize = 8.sp
                )
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

/** DETTE FUNKA IKKE  SKJØNNER IKKE HVORFOR,
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
                shoppingCart = DataSource.cartItems,
                onArtistButtonClicked = {}, onCategoryButtonClicked = {}, onPayButtonClicked = {})
        }
    }
}