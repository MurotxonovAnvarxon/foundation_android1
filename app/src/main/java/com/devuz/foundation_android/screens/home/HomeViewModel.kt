package com.devuz.foundation_android.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devuz.foundation_android.utils.Status
import com.devuz.network.KtorClient
import com.devuz.network.models.response.home.Root
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var ktorClient = KtorClient()

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    var page = 1

    fun getData() {
        viewModelScope.launch {
            _state.update { it.copy(status = Status.Loading) }

            try {
                val result = ktorClient.getCharacters(page).body<Root>().results ?: emptyList()

                _state.update {
                    it.copy(
                        characterList = result,
                        status = Status.Success
                    )
                }


            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        status = Status.Error,
                        errorMessage = e.message.toString()
                    )
                }

            }
        }
    }
}