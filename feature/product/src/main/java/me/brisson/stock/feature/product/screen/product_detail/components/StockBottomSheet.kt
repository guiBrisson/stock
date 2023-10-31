package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.stock.core.design_system.components.BottomSheet
import me.brisson.stock.core.design_system.components.TextInput
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.design_system.theme.green500
import me.brisson.stock.core.design_system.theme.green600
import me.brisson.stock.core.design_system.theme.red600
import me.brisson.stock.core.design_system.theme.red700
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.StockItem
import me.brisson.stock.core.model.StockMovement
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEntryBottomSheet(
    modifier: Modifier = Modifier,
    show: Boolean,
    productId: Int,
    measurementUnit: MeasurementUnit,
    onDismissRequest: () -> Unit,
    onConclude: (StockItem) -> Unit,
) {

    BottomSheet(
        modifier = modifier,
        show = show,
        onDismissRequest = onDismissRequest,
    ) {
        StockItemBottomSheetContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            productId = productId,
            measurementUnit = measurementUnit,
            onConclude = onConclude,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteOffBottomSheet(
    modifier: Modifier = Modifier,
    show: Boolean,
    stockItem: StockItem,
    measurementUnit: MeasurementUnit,
    onDismissRequest: () -> Unit,
    onConclude: (StockMovement) -> Unit,
) {
    BottomSheet(
        modifier = modifier,
        show = show,
        onDismissRequest = onDismissRequest,
    ) {
        StockMovementBottomSheetContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            stockItem = stockItem,
            measurementUnit = measurementUnit,
            onConclude = onConclude,
        )
    }
}

@Composable
private fun StockItemBottomSheetContent(
    modifier: Modifier = Modifier,
    productId: Int,
    measurementUnit: MeasurementUnit,
    onConclude: (StockItem) -> Unit,
) {
    var quantity by remember { mutableIntStateOf(0) }
    var batch by remember { mutableStateOf("") }
    var price by remember { mutableFloatStateOf(0f) }
    var buttonEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(quantity, batch) {
        buttonEnabled = (quantity > 0 && batch.isNotEmpty())
    }

    Column(modifier = modifier.padding(20.dp)) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val lineColor = if (isSystemInDarkTheme()) green500 else green600

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(lineColor)
            )

            Text(text = "Nova Entrada", style = MaterialTheme.typography.titleSmall)
        }

        Text(
            modifier = Modifier.padding(bottom = 8.dp, top = 20.dp),
            text = "Quantidade*",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            value = if (quantity == 0) "" else quantity.toString(),
            onValueChange = { quantity = if (it.isNotEmpty()) it.toInt() else 0 },
            hintText = "0",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            trayContent = {
                Text(measurementUnit.abbreviation)
            }
        )

        Text(
            modifier = Modifier.padding(bottom = 8.dp, top = 20.dp),
            text = "Lote*",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            value = batch,
            onValueChange = { batch = it },
            hintText = "LT12345",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
        )

        Row(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Data de entrada*",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                )

                TextInput(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = { },
                    hintText = "00/0000",
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number,
                    ),
                    trayContent = {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Validade*",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                )

                TextInput(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = { },
                    hintText = "00/0000",
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number,
                    ),
                    trayContent = {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                )
            }
        }

        Text(
            modifier = Modifier.padding(bottom = 8.dp, top = 20.dp),
            text = "Preço",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            value = if (price == 0f) "" else price.toString(),
            onValueChange = { price = it.toFloat() },
            hintText = "0,00",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            leadingContent = {
                Text("R$")
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            enabled = buttonEnabled,
            onClick = {
                //Todo: use the right dates
                val item = StockItem(
                    batch = batch,
                    productId = productId,
                    entryDate = Date(),
                    expirationDate = Date(),
                    price = price,
                    quantity = quantity,
                )

                onConclude(item)
            },
            shape = RoundedCornerShape(4.dp),
        ) {
            Text(text = "Concluir", style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun StockMovementBottomSheetContent(
    modifier: Modifier = Modifier,
    stockItem: StockItem,
    measurementUnit: MeasurementUnit,
    onConclude: (StockMovement) -> Unit,
) {
    var quantity by remember { mutableIntStateOf(0) }
    var isLoss by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(20.dp)) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val lineColor = if (isSystemInDarkTheme()) red600 else red700

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(lineColor)
            )

            Text(text = "Dar baixa", style = MaterialTheme.typography.titleSmall)
        }

        Text(
            modifier = Modifier.padding(bottom = 8.dp, top = 20.dp),
            text = "Lote",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            value = stockItem.batch,
            onValueChange = { },
            enabled = false,
            hintText = "LT12345",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
        )

        Text(
            modifier = Modifier.padding(bottom = 8.dp, top = 20.dp),
            text = "Quantidade*",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            value = if (quantity == 0) "" else quantity.toString(),
            onValueChange = { quantity = if (it.isNotEmpty()) it.toInt() else 0 },
            hintText = "0",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            trayContent = {
                Text(measurementUnit.abbreviation)
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(

                text = "Perda",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            )

            Checkbox(
                modifier = Modifier.offset(x = 12.dp),
                checked = isLoss,
                onCheckedChange = { isLoss = it })
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = (quantity > 0),
            onClick = {
                val movement = StockMovement(
                    itemBatch = stockItem.batch,
                    isEntry = false,
                    isLoss = isLoss,
                    date = Date(),
                    quantity = quantity,
                )

                onConclude(movement)
            },
            shape = RoundedCornerShape(4.dp),
        ) {
            Text(text = "Concluir", style = MaterialTheme.typography.titleSmall)
        }

    }
}

@Preview
@Composable
private fun PreviewStockItemBottomSheetContent() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            StockItemBottomSheetContent(
                modifier = Modifier.fillMaxWidth(),
                productId = 0,
                measurementUnit = MeasurementUnit.MASS,
                onConclude = { },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewStockMovementBottomSheetContent() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            StockMovementBottomSheetContent(
                modifier = Modifier.fillMaxWidth(),
                stockItem = StockItem.mockForPreview(),
                measurementUnit = MeasurementUnit.MASS,
                onConclude = { },
            )
        }
    }
}
