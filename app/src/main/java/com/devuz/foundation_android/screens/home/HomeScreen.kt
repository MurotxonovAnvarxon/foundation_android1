package com.devuz.foundation_android.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.devuz.foundation_android.components.CharacterGridItem
import com.devuz.foundation_android.ui.theme.RickAction
import com.devuz.foundation_android.ui.theme.RickPrimary
import com.devuz.foundation_android.utils.Status


@Suppress("ParamsComparedByRef")
@Composable
fun HomeScreen(
    onCharacterSelected: (characterId: Long, characterName: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    innerPadding: PaddingValues
) {

    val scrollState = rememberLazyGridState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getData()
    })

    HomeContent(viewModel, scrollState, onCharacterSelected, innerPadding)
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ParamsComparedByRef")
@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    scrollState: LazyGridState,
    onCharacterSelected: (characterId: Long, characterName: String) -> Unit,
    innerPadding: PaddingValues
) {
    Scaffold(
        modifier = Modifier
            .background(color = RickPrimary)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentColor = RickPrimary,
        containerColor = RickPrimary,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = RickPrimary),

                title = { Text(text = "Rick and Morty", color = RickAction) }, actions = {}, navigationIcon = { },
            )
        }
    ) { p ->
        p
        Box(
            modifier = Modifier
                .fillMaxSize().padding(top = 56.dp)
        ) {
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
                                        onClick = {
                                            onCharacterSelected(character.id ?: 1, character.name ?: "")
                                        })
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
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(onCharacterSelected = { a, b ->
    }, innerPadding = PaddingValues(0.dp))
}