package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.stock.core.design_system.theme.StockTheme

@Composable
fun EntryButton(modifier: Modifier = Modifier, onNewEntry: () -> Unit) {

    Button(
        modifier = modifier,
        onClick = onNewEntry,
        shape = RoundedCornerShape(4.dp),
    ) {
        Text("Nova entrada", style = MaterialTheme.typography.labelSmall)

        Icon(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(20.dp),
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
        )
    }

}

@Preview
@Composable
private fun PreviewEntryButton() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            EntryButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                onNewEntry = { },
            )
        }
    }
}
