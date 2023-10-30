package me.brisson.stock.feature.product.screen.product_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.stock.core.design_system.components.MyDropdown
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.design_system.theme.green600
import me.brisson.stock.core.design_system.theme.neutral200
import me.brisson.stock.core.design_system.theme.neutral600
import me.brisson.stock.core.design_system.theme.red700
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.Product

@Composable
fun ProductDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val product by viewModel.product.collectAsStateWithLifecycle()

    ProductDetailScreen(modifier = modifier.fillMaxSize(), product = product, onBack = onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    product: Product?,
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Box {
                var showMoreOptionsDropdown by remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }

                    IconButton(onClick = { showMoreOptionsDropdown = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }
                }

                ProductDetailMoreOptions(
                    expanded = showMoreOptionsDropdown,
                    onDismissRequest = { showMoreOptionsDropdown = false },
                    onEdit = { /*TODO*/ },
                    onDelete = { /*TODO*/ },
                )
            }
        }
    ) { scaffoldPadding ->
        Column(modifier = Modifier.padding(scaffoldPadding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                product?.let { product ->
                    item {
                        Row(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            product.observation?.let {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                                )
                            }

                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.titleLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top,
                        ) {
                            DetailItem(
                                title = "Próximo vencimento",
                                value = "12/2023",
                            )

                            DetailItem(
                                title = "Preço médio",
                                value = "R\$ 13,50",
                            )

                            DetailItem(
                                title = "Medida",
                                value = product.measurementUnit.sampleName(),
                            )
                        }
                    }
                }
            }

            MovementsButtons(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                onWriteOff = { /*TODO*/ },
                onNewEntry = { /*TODO*/ },
            )

        }
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

@Composable
private fun ProductDetailMoreOptions(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    MyDropdown(
        modifier = modifier.width(IntrinsicSize.Min),
        alignment = Alignment.TopEnd,
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = IntOffset(y = 150, x = 0),
    ) {
        DropdownMenuItem(text = { Text(text = "Editar") }, onClick = onEdit)
        DropdownMenuItem(text = { Text(text = "Excluir") }, onClick = onDelete)
    }
}

@Composable
private fun MovementsButtons(
    modifier: Modifier = Modifier,
    onWriteOff: () -> Unit,
    onNewEntry: () -> Unit,
) {
    val borderColor = if (isSystemInDarkTheme()) neutral600 else neutral200

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        TextButton(
            modifier = Modifier.weight(1f),
            onClick = onWriteOff,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, borderColor),
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
            ),
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(20.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = red700,
            )
            Text("Dar baixa", style = MaterialTheme.typography.labelSmall)
        }
        TextButton(
            modifier = Modifier.weight(1f),
            onClick = onNewEntry,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, borderColor),
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
            ),
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(20.dp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = green600,
            )
            Text("Nova entrada", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview
@Composable
private fun PreviewProductDetailScreen() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val product = Product(
                name = "Arroz branco",
                measurementUnit = MeasurementUnit.MASS,
                expirationDay = null,
                observation = "this is an observation!",
            )

            ProductDetailScreen(modifier = Modifier.fillMaxSize(), product = product, onBack = { })
        }
    }
}
