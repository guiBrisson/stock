package me.brisson.stock.core.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import me.brisson.stock.core.design_system.theme.neutral200
import me.brisson.stock.core.design_system.theme.neutral600

@Composable
fun MyDropdown(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    strokeColor: Color = if (isSystemInDarkTheme()) neutral600 else neutral200,
    shape: Shape = RoundedCornerShape(4.dp),
    alignment: Alignment = Alignment.TopCenter,
    offset: IntOffset = IntOffset(0, 0),
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (expanded) {
        Popup(
            onDismissRequest = onDismissRequest,
            alignment = alignment,
            offset = offset,
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = 20.dp)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.background)
                    .border(1.dp, color = strokeColor, shape = shape),
                content = content
            )
        }
    }
}