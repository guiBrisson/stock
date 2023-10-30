package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.brisson.stock.feature.product.screen.product_detail.ProductDetailUiState
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Date
import java.util.Locale

@Composable
fun ProductDetails(
    modifier: Modifier = Modifier,
    productDetailUiState: ProductDetailUiState.Success,
) {
    val itemsWithPrice = productDetailUiState.stockItems.filter { it.price != null }
    val priceSum = itemsWithPrice.map { it.price!! }.sum()
    val priceAverage = priceSum / itemsWithPrice.size

    val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.maximumFractionDigits = 2
    numberFormat.currency = Currency.getInstance("BRL")

    val formattedAveragePrice = if (itemsWithPrice.isEmpty()) "-" else numberFormat.format(priceAverage)

    var closestDate: Date? = null
    productDetailUiState.stockItems.forEach { item ->
        if (closestDate == null) closestDate = item.expirationDate

        if (item.expirationDate < closestDate) {
            closestDate = item.expirationDate
        }
    }
    val locale: Locale = Locale.getDefault()
    val dateFormat = SimpleDateFormat("MM/yyyy", locale)
    val formattedDate = if (closestDate == null) "-" else dateFormat.format(closestDate!!)


    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        DetailItem(
            title = "Próximo vencimento",
            value = formattedDate,
        )

        DetailItem(
            title = "Preço médio",
            value = formattedAveragePrice,
        )

        DetailItem(
            title = "Medida",
            value = productDetailUiState.product.measurementUnit.sampleName(),
        )
    }
}

@Composable
private fun DetailItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String?,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        Text(
            text = value ?: "-",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}