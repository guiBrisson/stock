package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.design_system.theme.green500
import me.brisson.stock.core.design_system.theme.green600
import me.brisson.stock.core.design_system.theme.red600
import me.brisson.stock.core.design_system.theme.red700
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.StockMovement
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ProductMovementItem(
    modifier: Modifier = Modifier,
    item: StockMovement,
    measurementUnit: MeasurementUnit,
) {
    val locale = Locale.getDefault()
    val dateFormat = SimpleDateFormat("MM/yyyy", locale)
    val formattedDate = dateFormat.format(item.date)
    val formattedExpirationDate = dateFormat.format(item.expirationDate)

    val icon = if (item.isEntry) {
        Icons.Default.KeyboardArrowUp
    } else {
        Icons.Default.KeyboardArrowDown
    }

    val entryColor = if (isSystemInDarkTheme()) green500 else green600
    val writeOffColor = if (isSystemInDarkTheme()) red700 else red600

    val iconColor = if (item.isEntry) entryColor else writeOffColor

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(HEADER_INDEX_SIZE),
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
        )

        Text(
            modifier = Modifier.weight(1f),
            text = item.itemBatch,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        //Todo: format date "time ago"
        Text(modifier = Modifier.weight(1f), text = formattedDate, textAlign = TextAlign.Center)

        Text(modifier = Modifier.weight(1f), text = formattedExpirationDate, textAlign = TextAlign.Center)

        Text(
            modifier = Modifier.weight(1f),
            text = "${item.quantity} ${measurementUnit.abbreviation}",
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun PreviewProductMovementItem() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val movement = StockMovement.mockForPreview()

            ProductMovementItem(
                modifier = Modifier.fillMaxWidth(),
                item = movement,
                measurementUnit = MeasurementUnit.UNIT,
            )
        }
    }
}

