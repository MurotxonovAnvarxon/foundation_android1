package com.devuz.foundation_android.screens.saved

import com.devuz.foundation_android.utils.Status
import com.devuz.local.model.CharacterEntity
import com.devuz.network.models.response.home.RootResultCharacter

data class SavedState (
    val status: Status = Status.Loading,
    val characterList: List<CharacterEntity?> = emptyList(),
    val errorMessage: String =""
)