@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package com.devuz.foundation_android.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.devuz.foundation_android.components.CharacterGridItem
import com.devuz.foundation_android.utils.Status


@Suppress("ParamsComparedByRef")
@Composable
fun HomeScreen(onCharacterSelected: (characterId: Long) -> Unit, viewModel: HomeViewModel = hiltViewModel()) {

    val scrollState = rememberLazyGridState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getData()
        Log.d("TTT", "HomeScreen: ${viewModel.uiState.characterList}")
    })

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
        ) {

            when (viewModel.uiState.status) {
                Status.Loading -> {
                    CircularProgressIndicator()
                }
                Status.Success -> {

                    LazyVerticalGrid(
                        state = scrollState,
                        contentPadding = PaddingValues(all = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        columns = GridCells.Fixed(2),
                        content = {
                            items(
                                items = viewModel.uiState.characterList,
                            ) { character ->
                                CharacterGridItem(
                                    modifier = Modifier.clickable {},
                                    character = character,
                                    onClick = { onCharacterSelected(character.id ?: 1) })
                            }
                        }
                    )
                }
                Status.Error -> {
                    Text(viewModel.uiState.errorMessage)
                }
                else -> {}
            }
        }
    }
}


@Preview
@Composable
fun HomePreview() {
    HomeScreen(onCharacterSelected = {})
}