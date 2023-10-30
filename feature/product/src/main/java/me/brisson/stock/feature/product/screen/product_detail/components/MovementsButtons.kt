package me.brisson.stock.feature.product.screen.product_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.brisson.stock.core.design_system.theme.green600
import me.brisson.stock.core.design_system.theme.neutral200
import me.brisson.stock.core.design_system.theme.neutral600
import me.brisson.stock.core.design_system.theme.red700

@Composable
fun MovementsButtons(
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