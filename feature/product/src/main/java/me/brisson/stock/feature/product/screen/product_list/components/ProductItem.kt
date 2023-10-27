package me.brisson.stock.feature.product.screen.product_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.Product

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    total: Int,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = product.name,
            style = MaterialTheme.typography.headlineMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )

        Text(
            text = "Total: ",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = "$total ${product.measurementUnit.abbreviation}",
            style = MaterialTheme.typography.labelSmall,
        )
        
        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductItem() {
    val product = Product(
        name = "Pêssego em caldas",
        measurementUnit = MeasurementUnit.UNIT,
        expirationDate = null,
        observation = null,
    )

    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ProductItem(
                modifier = Modifier.fillMaxWidth(),
                product = product,
                total = 20,
                onClick = { },
            )
        }
    }
}
