package com.devuz.network.models.response.home

import kotlinx.serialization.Serializable

@Serializable
data class Root(
    val info: Info?,
    val results: List<RootResultCharacter>?,
)


@Serializable
data class Info(
    val count: Int?,
    val pages: Int?,
    val next: String?,
    val prev: String?,
)

@Serializable
data class RootResultCharacter(
    val id: Long?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val origin: Origin?,
    val location: Location?,
    val image: String?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,
)

@Serializable
data class Origin(
    val name: String?,
    val url: String?,
)

@Serializable
data class Location(
    val name: String?,
    val url: String?,
)
