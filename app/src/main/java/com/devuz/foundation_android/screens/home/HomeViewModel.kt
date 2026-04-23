package com.devuz.foundation_android.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devuz.foundation_android.utils.Status
import com.devuz.network.KtorClient
import com.devuz.network.models.domain.Root
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var ktorClient = KtorClient()

    var uiState by mutableStateOf(HomeState())
        private set

    var page = 1

    fun getData() {
        viewModelScope.launch {
            uiState = uiState.copy(status = Status.Loading)

            try {
                val result = ktorClient.getCharacters(page).body<Root>().results ?: emptyList()

                uiState = uiState.copy(
                    characterList = result,
                    status = Status.Success
                )

            } catch (e: Exception) {
                uiState = uiState.copy(
                    status = Status.Error,
                    errorMessage = e.message.toString()
                )
            }
        }
    }
}