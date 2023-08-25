package org.thechance.common.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.beepbeep.designSystem.ui.theme.Theme
import org.thechance.common.LocalDimensions

@Composable
fun BpDropdownMenuItem(
    onClick: () -> Unit,
    text: String,
    leadingIconPath: String? = null,
    modifier: Modifier = Modifier,
    showBottomDivider: Boolean = true,
    isSecondary: Boolean = false,
) {
    Column(modifier = modifier) {
        DropdownMenuItem(
            onClick = onClick,
            contentPadding = PaddingValues(horizontal = LocalDimensions.current.space8),
            text = {
                Text(
                    text = text,
                    style = Theme.typography.body,
                    color = if (isSecondary) Theme.colors.contentSecondary else Theme.colors.contentPrimary,
                )
            },
            leadingIcon = if (leadingIconPath != null) {
                {
                    Icon(
                        painter = painterResource(leadingIconPath),
                        contentDescription = null,
                        tint = if (isSecondary) Theme.colors.contentSecondary else Theme.colors.contentPrimary,
                        modifier = Modifier.size(LocalDimensions.current.space16)
                    )
                }
            } else null,
        )
        if (showBottomDivider) {
            Divider(
                thickness = LocalDimensions.current.space1,
                color = Theme.colors.divider,
                modifier = Modifier.padding(horizontal = LocalDimensions.current.space8)
            )
        }
    }
}