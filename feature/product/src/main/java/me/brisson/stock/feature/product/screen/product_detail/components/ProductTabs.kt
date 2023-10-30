package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.design_system.theme.neutral200
import me.brisson.stock.core.design_system.theme.neutral600

@Composable
fun ProductTabs(
    modifier: Modifier = Modifier,
    selectedTab: ProductDetailTab,
    onTabSelected: (ProductDetailTab) -> Unit,
) {
    Row(modifier = modifier.height(IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        enumValues<ProductDetailTab>().forEach { tab ->
            ProductTabItem(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                selected = (selectedTab == tab),
                label = tab.label,
                onClick = { onTabSelected(tab) }
            )
        }
    }
}

@Composable
private fun ProductTabItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    shape: Shape = RoundedCornerShape(4.dp),
    label: String,
    onClick: () -> Unit,
) {
    val borderColor = if (isSystemInDarkTheme()) neutral600 else neutral200

    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.background
    }

    val contentColor = if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onBackground
    }

    Row(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .border(width = 1.dp, shape = shape, color = borderColor)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge, color = contentColor)
    }
}

enum class ProductDetailTab(val label: String) {
    STOCK(label = "Estoque"),
    MOVEMENT(label = "Movimentação"),
}

@Preview
@Composable
private fun PreviewProductTabs() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ProductTabs(
                modifier = Modifier.fillMaxWidth().height(40.dp),
                selectedTab = ProductDetailTab.STOCK,
                onTabSelected = { },
            )
        }
    }
}
