package com.devuz.foundation_android.screens.detail
import DetailResponse
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devuz.foundation_android.utils.Status
import com.devuz.network.KtorClient
import com.devuz.local.dao.CharacterDao
import com.devuz.local.model.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dao: CharacterDao,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    var state: StateFlow<DetailState> = _state.asStateFlow()


    val ktorClient = KtorClient()


    suspend fun saveCharacter(character: DetailResponse) {
        dao.insert(
            characterEntity = CharacterEntity(
                id = character.id,
                characterName = character.name,
                image = character.image
            )
        )

        getCharacters()
    }


    suspend fun getCharacters(){
      val list=  dao.getAllCharacterDetails()
        Log.d("TTT", "getCharacters: $list")
    }


    fun getByIdCharacter(characterId: Long) {
        viewModelScope.launch {
            _state.update { it.copy(status = Status.Loading) }
            try {
                val result = ktorClient.getCharacterById(characterId).body<DetailResponse>()

                _state.update { it.copy(status = Status.Success, character = result) }
            } catch (e: Exception) {
                _state.update { it.copy(status = Status.Error, errorMessage = e.message.toString()) }
            }
        }
    }
}