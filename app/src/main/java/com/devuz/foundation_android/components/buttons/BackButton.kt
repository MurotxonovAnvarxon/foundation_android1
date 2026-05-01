package com.devuz.foundation_android.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devuz.foundation_android.R
import com.devuz.foundation_android.ui.theme.RickAction


@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    image: Int = R.drawable.back,
    contentDescription: String = "Back",
    enabled: Boolean = true,
) {
    IconButton(
        modifier = modifier.size(24.dp),
        onClick = onClick,
        enabled = enabled,
    ) {
        Icon(tint = RickAction, painter = painterResource(image), contentDescription = contentDescription)
    }
}

