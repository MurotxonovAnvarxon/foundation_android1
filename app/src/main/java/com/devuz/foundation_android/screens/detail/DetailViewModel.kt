package com.devuz.foundation_android.screens.detail

import DetailResponse
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devuz.foundation_android.screens.home.HomeState
import com.devuz.foundation_android.utils.Status
import com.devuz.network.KtorClient
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

//    private val _state = MutableStateFlow(DetailState())
//    var state = _state.asStateFlow().value

    var state by mutableStateOf(DetailState())
        private set
    val ktorClient = KtorClient()


    fun getByIdCharacter(characterId: Long) {
        viewModelScope.launch {
            state = state.copy(status = Status.Loading)
            try {
                val result = ktorClient.getCharacterById(characterId).body<DetailResponse>()

                state = state.copy(status = Status.Success, character = result)
                Log.d("TTT", "getByIdCharacter: ${state.character}")
            } catch (e: Exception) {
                state = state.copy(status = Status.Error, errorMessage = e.message.toString())
            }
        }
    }
}