package com.example.myartshop.ui.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.myartshop.R

/**
 * Composable that displays formatted [price] that will be formatted and displayed on screen
 * Code copied from cupcake app
 */
@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}