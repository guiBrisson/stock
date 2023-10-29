package me.brisson.stock.feature.product.screen.new_product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.brisson.stock.core.design_system.theme.StockTheme

@Composable
fun NewProductRoute(
    modifier: Modifier = Modifier,
    viewModel: NewProductViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    NewProductScreen(
        modifier = modifier.fillMaxSize(),
        onBack = onBack,
    )
}

@Composable
internal fun NewProductScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    Column(modifier = modifier) {
        IconButton(modifier = Modifier.padding(horizontal = 4.dp ), onClick = onBack) {
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
    }
}

@Preview
@Composable
private fun PreviewNewProductScreen() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            NewProductScreen(modifier = Modifier.fillMaxSize(), onBack = { })
        }
    }
}
