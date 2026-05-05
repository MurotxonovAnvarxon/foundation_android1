package com.devuz.foundation_android.screens.saved

import androidx.lifecycle.ViewModel
import com.devuz.foundation_android.utils.Status
import com.devuz.local.dao.CharacterDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SavedViewModel @Inject constructor(

    private val dao: CharacterDao,

    ) : ViewModel() {


    private val _state = MutableStateFlow(SavedState())
    val state = _state.asStateFlow()


    suspend fun getCharacters() {
        _state.update { it.copy(status = Status.Loading) }

        try {
            val result = dao.getAllCharacterDetails()

            if (result.isEmpty()) {
                _state.update { it.copy(status = Status.Empty) }
            } else {
                _state.update { it.copy(status = Status.Success, characterList = result) }
            }

        } catch (e: Exception) {
            _state.update { it.copy(status = Status.Error, errorMessage = e.message.toString()) }
        }
    }

}