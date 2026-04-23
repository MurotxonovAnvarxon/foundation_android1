package com.devuz.foundation_android.screens.home

import com.devuz.foundation_android.utils.Status
import com.devuz.network.models.domain.RootResultCharacter

data class HomeState(
    val status: Status = Status.Loading,
    val characterList: List<RootResultCharacter> = emptyList(),
    val errorMessage: String = ""
)


sealed interface HomeEvent {
    data class ClickItemEvent(val id: Int) : HomeEvent
    class LoadEvent : HomeEvent
    data class NextLoadEvent(val page: Int) : HomeEvent
}