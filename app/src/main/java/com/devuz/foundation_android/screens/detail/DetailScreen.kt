package com.devuz.foundation_android.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devuz.foundation_android.R
import com.devuz.foundation_android.components.CharacterImage
import com.devuz.foundation_android.components.buttons.BackButton
import com.devuz.foundation_android.ui.theme.RickAction
import com.devuz.foundation_android.ui.theme.RickPrimary
import com.devuz.foundation_android.utils.Status
import kotlinx.coroutines.launch

@Suppress("ParamsComparedByRef")
@Composable
fun DetailScreen(
    onBackClicked: () -> Unit,
    characterName: String,
    characterId: Long,
    viewModel: DetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getCharacterByIdDao(characterId)
        viewModel.getByIdCharacter(characterId)
    })
    DetailContent(onBackClicked, characterName, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ParamsComparedByRef")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailContent(
    onBackClicked: () -> Unit,
    characterName: String, viewModel: DetailViewModel
) {
    val coroutineState = rememberCoroutineScope()

    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .background(color = RickPrimary)
            .padding(horizontal = 12.dp),
        contentColor = RickPrimary,
        containerColor = RickPrimary,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = RickPrimary),
                title = { Text(text = characterName, color = RickAction) },
                actions = {
                    Box {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    coroutineState.launch {
                                        viewModel.saveButtonOnClick(state.character!!)
                                    }
                                },
                            tint = RickAction,
                            painter = painterResource(if (state.isDao?:false) R.drawable.save_filled else R.drawable.save),
                            contentDescription = "Save",
                            )
                    }
                },
                navigationIcon = { BackButton(onBackClicked) },
            )
        }) {
        Box(
            modifier = Modifier
                .padding(top = 76.dp)
                .fillMaxSize()
        ) {
            when (state.status) {
                Status.Loading -> CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                Status.Success -> Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())

                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CharacterImage(imageUrl = state.character?.image ?: "")
                    }

                    Column(modifier = Modifier.padding(top = 10.dp)) {

                        Row {
                            var color = Color.Yellow

                            when (state.character?.status) {
                                "Alive" -> color = Color.Green
                                "Dead" -> color = Color.Red
                                "Unknown" -> color = Color.Yellow
                            }

                            Text(text = "Status:", color = RickAction)
                            Text(text = state.character?.status ?: "", color = color)
                        }

                        Text(text = "Location: ${state.character?.location?.name ?: ""}", color = RickAction)
                        Text(text = "Species: ${state.character?.species ?: ""}", color = RickAction)
                        Text(text = "Gender: ${state.character?.gender ?: ""}", color = RickAction)
                        Text(text = "Origin: ${state.character?.origin?.name ?: ""}", color = RickAction)
                        Text(text = "Created: ${state.character?.created ?: ""}", color = RickAction)
                    }
                }

                Status.Error -> Text(state.errorMessage)

                else -> {}
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen({}, "Rick Sanchez", 1)
}