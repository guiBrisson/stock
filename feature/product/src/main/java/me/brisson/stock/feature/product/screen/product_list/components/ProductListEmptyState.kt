package me.brisson.stock.feature.product.screen.product_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.design_system.R

@Composable
fun ProductListEmptyState(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {


        Image(painter = painterResource(id = R.drawable.empty_state), contentDescription = null)
        Text(text = "Sem produtos", style = MaterialTheme.typography.titleLarge)
        Text(
            text = "Clique no botão Adicionar Novo acima para adicionar um novo produto.",
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
        )
    }
}

@Preview
@Composable
private fun PreviewProductListEmptyState() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ProductListEmptyState()
        }
    }
}
