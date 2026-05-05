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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devuz.foundation_android.components.CharacterGridItem
import com.devuz.foundation_android.components.buttons.BackButton
import com.devuz.foundation_android.screens.saved.SavedViewModel
import com.devuz.foundation_android.screens.saved.components.CharacterGridItemSaved
import com.devuz.foundation_android.ui.theme.RickAction
import com.devuz.foundation_android.ui.theme.RickPrimary
import com.devuz.foundation_android.utils.Status


@Suppress("ParamsComparedByRef")
@Composable
fun SavedScreen(
    onCharacterSelected: (characterId: Long) -> Unit,
    innerPadding: PaddingValues,
    onBackClicked: () -> Unit,
    viewModel: SavedViewModel = hiltViewModel(

    )
) {

    val scrollState = rememberLazyGridState()


    LaunchedEffect(key1 = Unit, block = {
        viewModel.getCharacters()
    })

    SavedScreenContent(scrollState, onCharacterSelected, innerPadding, onBackClicked,viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ParamsComparedByRef")
@Composable
fun SavedScreenContent(
    scrollState: LazyGridState,
    onCharacterSelected: (characterId: Long) -> Unit,
    innerPadding: PaddingValues,
    onBackClicked: () -> Unit,
    viewModel: SavedViewModel
    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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

                title = { Text(text = "Favorites", color = RickAction) },
                actions = {},
                navigationIcon = { BackButton(onBackClicked) },


                )
        }
    ) { p ->
        p
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
            ) {
                when (state.status) {
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
                                    items = state.characterList,
                                ) { character ->
                                    CharacterGridItemSaved(
                                        modifier = Modifier.clickable {},
                                        character = character!!,
                                        onClick = {
                                            onCharacterSelected(character.id ?: 1,)
                                        })
                                }
                            }
                        )
                    }

                    Status.Error -> {
                        Text(state.errorMessage)
                    }
                    Status.Empty -> {
                        Box (modifier = Modifier.align(alignment = Alignment.Center)){
                            Text("Empty", color = Color.White)
                        }
                    }

                    else -> {}
                }

            }
        }
    }
}

@Preview
@Composable
fun SavedScreenPreview() {
    SavedScreen(onCharacterSelected = { a ->
    }, innerPadding = PaddingValues(0.dp), onBackClicked = {})
}