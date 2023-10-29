package me.brisson.stock.feature.product.screen.new_product

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.stock.core.design_system.components.MyDropdown
import me.brisson.stock.core.design_system.components.TextInput
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.Product

@Composable
fun NewProductRoute(
    modifier: Modifier = Modifier,
    viewModel: NewProductViewModel = hiltViewModel(),
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val newProductUiState by viewModel.newProductUiState.collectAsStateWithLifecycle()

    NewProductScreen(
        modifier = modifier.fillMaxSize(),
        newProductUiState = newProductUiState,
        onBack = onBack,
        onNewProduct = viewModel::newProduct,
    )

    LaunchedEffect(newProductUiState) {
        if (newProductUiState.isSuccess()) {
            //Todo: Replace toast with snackbar
            Toast.makeText(context, "Novo produto criado", Toast.LENGTH_SHORT).show()
            onBack()
        }
    }

}

@Composable
internal fun NewProductScreen(
    modifier: Modifier = Modifier,
    newProductUiState: NewProductUiState,
    onBack: () -> Unit,
    onNewProduct: (Product) -> Unit,
) {
    var showMeasurementUnitDropdown by remember { mutableStateOf(false) }
    var showExpiringDropdown by remember { mutableStateOf(false) }

    var name by rememberSaveable { mutableStateOf("") }
    var measurementUnit by rememberSaveable { mutableStateOf(MeasurementUnit.UNKNOWN) }
    var expiring by rememberSaveable { mutableStateOf("") }
    var observation by rememberSaveable { mutableStateOf("") }

    val isButtonEnabled: Boolean =
        (name.isNotEmpty() && measurementUnit != MeasurementUnit.UNKNOWN && !newProductUiState.isLoading())




    Column(modifier = modifier) {
        IconButton(modifier = Modifier.padding(horizontal = 4.dp), onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 12.dp),
            text = "Novo produto",
            style = MaterialTheme.typography.titleLarge,
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            text = "Por favor, preencha o formulário para cadastrar um novo produto.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Campos com * são obrigatórios",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 32.dp, bottom = 8.dp),
            text = "Name*",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        TextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = name,
            onValueChange = { name = it },
            hintText = "Arroz branco"
        )

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 8.dp),
            text = "Unidade de medida*",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        Column {
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = measurementUnit.sampleName(),
                onValueChange = { },
                hintText = "Peso (Kg)",
                enabled = false,
                trayContent = {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                },
                onClick = { showMeasurementUnitDropdown = true },
            )

            UnitMeasurementDropdown(
                expanded = showMeasurementUnitDropdown,
                onSelect = { measurementUnit = it },
                onDismissRequest = { showMeasurementUnitDropdown = false },
                offset = IntOffset(y = 130, x = 0)
            )
        }

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 8.dp),
            text = "Notificação de vencimento",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        Column {
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = expiring,
                onValueChange = { },
                hintText = "3 meses",
                enabled = false,
                trayContent = {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                },
                onClick = { showExpiringDropdown = true },
            )

            ExpiringDateDropdown(
                expanded = showExpiringDropdown,
                onSelect = { expiring = it },
                onDismissRequest = { showExpiringDropdown = false },
                offset = IntOffset(y = 130, x = 0)
            )
        }

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 8.dp),
            text = "Observação",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        )

        TextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = observation,
            onValueChange = { observation = it },
            hintText = "ex. Guardar o arroz branco em garráfas pet seladas sem oxigênio."
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            onClick = {
                val product = Product(
                    name = name,
                    measurementUnit = measurementUnit,
                    expirationDate = null, // Todo: make it right
                    observation = observation,
                )

                onNewProduct(product)
            },
            shape = RoundedCornerShape(4.dp),
            enabled = isButtonEnabled,
        ) {
            Text(text = "Concluir", style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun UnitMeasurementDropdown(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    offset: IntOffset = IntOffset(0, 0),
    onSelect: (MeasurementUnit) -> Unit,
    onDismissRequest: () -> Unit,
) {
    MyDropdown(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = offset,
    ) {
        enumValues<MeasurementUnit>()
            .filter { it.baseUnitName.isNotEmpty() }
            .forEach { measurementUnit ->
                DropdownMenuItem(
                    text = {
                        Text(text = measurementUnit.sampleName())
                    },
                    onClick = { onSelect(measurementUnit); onDismissRequest() },
                )
            }
    }
}

@Composable
fun ExpiringDateDropdown(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    offset: IntOffset = IntOffset(0, 0),
    onSelect: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    MyDropdown(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = offset,
    ) {
        listOf("2 meses", "3 meses", "4 meses", "5 meses", "6 meses").forEach { months ->
            DropdownMenuItem(
                text = {
                    Text(text = months)
                },
                onClick = { onSelect(months); onDismissRequest() },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNewProductScreen() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val newProductUiState = NewProductUiState.Idle

            NewProductScreen(
                modifier = Modifier.fillMaxSize(),
                newProductUiState = newProductUiState,
                onBack = { },
                onNewProduct = { },
            )
        }
    }
}
