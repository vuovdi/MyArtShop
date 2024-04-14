package com.example.myartshop.ui.ui
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myartshop.R
import com.example.myartshop.data.DataSource
import com.example.myartshop.data.DataSource.cartItems
import com.example.myartshop.data.OrderUiState
import com.example.myartshop.ui.OrderViewModel
import com.example.myartshop.ui.ui.theme.MyArtShopTheme

@Composable
fun SummaryScreen(
    viewModel: OrderViewModel,
    onPay: () -> Unit,
    modifier: Modifier) {
    var price = viewModel.sumPrice()
    var photoNames = ""
    for (cartItem in cartItems) {
        val photoName = cartItem.photo.title
        photoNames += "$photoName "
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Summary:",
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        SummaryItem("Picture:", photoNames, modifier)
        // TODO: Implement dynamic frame quality
        SummaryItem("Frame quality:", "Good", modifier)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Price including TAX:",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Total price: $price",
        )
        Spacer(modifier = Modifier.weight(1f))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Payment:",
            modifier = Modifier.padding(vertical = 8.dp)
        )
//        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        SummaryItem("Card number:", "123345671", modifier)
        SummaryItem("Expiry date:", "4/25", modifier)
        SummaryItem("CVS number:", "233", modifier)

        Button(
            modifier = Modifier,
            onClick = onPay
        ) {
            Text(stringResource(R.string.payment))
        }
    }

}

@Composable
fun SummaryItem(label: String, value: String, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f).align(Alignment.Top)
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    val viewModel: OrderViewModel = viewModel()
    val modifier = Modifier // Example modifier

    MyArtShopTheme {
        SummaryScreen(
            viewModel = viewModel,
            onPay  = {},
            modifier = modifier
        )
    }
}

