package me.brisson.stock.core.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.design_system.theme.orange100
import me.brisson.stock.core.design_system.theme.orange300
import me.brisson.stock.core.design_system.theme.orange500
import me.brisson.stock.core.design_system.theme.orange900
import me.brisson.stock.core.design_system.theme.red100
import me.brisson.stock.core.design_system.theme.red300
import me.brisson.stock.core.design_system.theme.red600
import me.brisson.stock.core.design_system.theme.red900

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    primaryColor: Color,
    text: String,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
            color = primaryColor,
        )
    }
}

@Composable
fun EmptyStockTag(modifier: Modifier = Modifier) {
    Tag(
        modifier = modifier,
        backgroundColor = if (isSystemInDarkTheme()) red900 else red100,
        primaryColor = if (isSystemInDarkTheme()) red300 else red600,
        text = "Sem estoque",
    )
}

@Composable
fun ExpiringTag(modifier: Modifier = Modifier) {
    Tag(
        modifier = modifier,
        backgroundColor = if (isSystemInDarkTheme()) orange900 else orange100,
        primaryColor = if (isSystemInDarkTheme()) orange300 else orange500,
        text = "Vencimento próximo",
    )
}

@Preview
@Composable
private fun PreviewTag() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Tag(
                backgroundColor = red100,
                primaryColor = red600,
                text = "Sem estoque",
            )
        }
    }
}
