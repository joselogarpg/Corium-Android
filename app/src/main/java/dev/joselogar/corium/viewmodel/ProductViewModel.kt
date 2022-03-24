package dev.joselogar.corium.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.joselogar.corium.model.ProductModel

@Composable
fun ProductViewModel(products: List<ProductModel>) {
    LazyColumn(
        /* → ↓ */
        contentPadding = PaddingValues(horizontal = 16.dp, 32.dp),
        /* ↑ ↓ */
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) {
            product -> ProductView(product = product)
        }
    }
}