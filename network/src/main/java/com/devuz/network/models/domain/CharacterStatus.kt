package com.devuz.network.models.domain

import kotlinx.serialization.Serializable

@Serializable
sealed class CharacterStatus(val displayName: String) {
    object Alive: CharacterStatus("Alive")
    object Dead: CharacterStatus("Dead")
    object Unknown: CharacterStatus("Unknown")
}