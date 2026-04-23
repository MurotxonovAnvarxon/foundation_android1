package com.devuz.foundation_android.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devuz.foundation_android.ui.theme.RickAction

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = RickAction
    )
}