package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.brisson.stock.feature.product.screen.product_detail.ProductDetailUiState

val HEADER_INDEX_SIZE = 24.dp

@Composable
fun ProductStockHeader(
    modifier: Modifier = Modifier,
    tab: ProductDetailTab,
    uiState: ProductDetailUiState.Success,
) {
    if (
        (tab == ProductDetailTab.STOCK && uiState.stockItems.isNotEmpty()) ||
        (tab == ProductDetailTab.MOVEMENT && uiState.stockMovements.isNotEmpty())
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.defaultMinSize(minWidth = HEADER_INDEX_SIZE))
            Text(
                modifier = Modifier.weight(1f),
                text = "Lote",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (tab == ProductDetailTab.MOVEMENT) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Data",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center
                )
            }

            Text(
                modifier = Modifier.weight(1f),
                text = "Validade",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.weight(1f),
                text = "Qnt",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )

            if (tab == ProductDetailTab.STOCK) {
                Box(modifier = Modifier.defaultMinSize(minWidth = HEADER_INDEX_SIZE))
            }
        }
    }
}
