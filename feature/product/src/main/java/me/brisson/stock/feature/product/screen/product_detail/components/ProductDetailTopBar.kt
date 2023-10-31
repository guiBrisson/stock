package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import me.brisson.stock.core.design_system.components.MyDropdown
import me.brisson.stock.core.design_system.theme.StockTheme


@Composable
fun ProductDetailTopBar(
    modifier: Modifier = Modifier,
    titleVisible: Boolean,
    productName: String,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    Box {
        var showMoreOptionsDropdown by remember { mutableStateOf(false) }
        Row(
            modifier = modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }

            AnimatedVisibility(
                visible = titleVisible,
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top),
            ) {
                Text(text = productName, style = MaterialTheme.typography.titleSmall)
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { showMoreOptionsDropdown = true }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        }

        MoreOptionsDropdown(
            expanded = showMoreOptionsDropdown,
            onDismissRequest = { showMoreOptionsDropdown = false },
            onEdit = onEdit,
            onDelete = onDelete,
        )
    }
}

@Composable
private fun MoreOptionsDropdown(
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

@Preview
@Composable
private fun PreviewProductDetailTopBar() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ProductDetailTopBar(
                modifier = Modifier.fillMaxWidth(),
                titleVisible = true,
                productName = "Feijão",
                onBack = { },
                onEdit = { },
                onDelete = { },
            )
        }
    }
}