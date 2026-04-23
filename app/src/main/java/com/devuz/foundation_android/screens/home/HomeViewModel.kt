package com.devuz.foundation_android.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.devuz.foundation_android.utils.Status
import com.devuz.network.KtorClient
import com.devuz.network.models.domain.Root
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var ktorClient = KtorClient()
    var uiState by mutableStateOf(HomeState(status = Status.Loading, characterList = emptyList(), errorMessage = ""))

    var page = 1


    suspend fun getData() {
        uiState.status = Status.Loading
        try {
            uiState.characterList = ktorClient.getCharacters(page).body<Root>().results ?: emptyList()
            uiState.status = Status.Success

        } catch (e: Exception) {
            uiState.status = Status.Error
            uiState.errorMessage = e.message.toString()
        }
    }

}