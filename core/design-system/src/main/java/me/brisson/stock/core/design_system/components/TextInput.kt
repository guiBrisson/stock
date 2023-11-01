package me.brisson.stock.core.design_system.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import me.brisson.stock.core.design_system.theme.neutral200
import me.brisson.stock.core.design_system.theme.neutral600


//Todo: make clear for the user when the component is not enabled
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusedColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = RoundedCornerShape(4.dp),
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
    trayContent: (@Composable () -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    BasicTextField(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min)
            .clickable { onClick?.let { it() } },
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        textStyle = textStyle,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
        decorationBox = { innerTextField ->
            val strokeColor: Color = if (isSystemInDarkTheme()) neutral600 else neutral200
            val borderColor by animateColorAsState(
                targetValue = if (isFocused) focusedColor else strokeColor,
                label = "border color",
            )
            val borderWidth by animateDpAsState(
                targetValue = if (isFocused) 1.5.dp else 0.8.dp,
                label = "border width",
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .border(width = borderWidth, color = borderColor, shape = shape)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                leadingContent?.let { it() }

                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty() && !hintText.isNullOrEmpty()) {
                        Text(
                            text = hintText,
                            style = textStyle.copy(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f))
                        )
                    }
                    innerTextField()
                }

                trayContent?.let { it() }
            }
        }
    )
}
