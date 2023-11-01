package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.design_system.theme.red600
import me.brisson.stock.core.design_system.theme.red700
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.StockItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProductStockItem(
    modifier: Modifier = Modifier,
    index: Int,
    item: StockItem,
    measurementUnit: MeasurementUnit,
    onWriteOff: () -> Unit,
) {
    val mutableInteractionSource = MutableInteractionSource()
    val locale = Locale.getDefault()
    val dateFormat = SimpleDateFormat("MM/yyyy", locale)
    val formattedDate = dateFormat.format(item.expirationDate)

    Row(
        modifier = modifier.clickable(
            interactionSource = mutableInteractionSource,
            indication = null,
            onClick = onWriteOff,
        ), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.defaultMinSize(minWidth = HEADER_INDEX_SIZE),
            text = index.toString()
        )

        Text(
            modifier = Modifier.weight(1f),
            text = item.batch,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Text(modifier = Modifier.weight(1f), text = formattedDate, textAlign = TextAlign.Center)

        Text(
            modifier = Modifier.weight(1f),
            text = "${item.quantity} ${measurementUnit.abbreviation}",
            textAlign = TextAlign.Center,
        )

        Icon(
            modifier = Modifier.size(HEADER_INDEX_SIZE).padding(2.dp),
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) red600 else red700,
        )
    }
}

@Preview
@Composable
private fun PreviewProductStockItem() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val item = StockItem(
                batch = "123",
                productId = 1,
                entryDate = Date(),
                expirationDate = Date(),
                price = 15f,
                quantity = 12,
            )

            ProductStockItem(
                index = 1,
                item = item,
                measurementUnit = MeasurementUnit.MASS,
                onWriteOff = { },
            )
        }
    }
}
