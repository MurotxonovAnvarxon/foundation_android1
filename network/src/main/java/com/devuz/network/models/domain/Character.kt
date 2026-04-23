package com.devuz.network.models.domain


data class Character(
    val created: String?,
    val episodeIds: List<Int?>,
//    val gender: CharacterGender,
    val id: Int?,
    val imageUrl: String?,
    val location: Location?,
    val name: String?,
    val origin: Origin?,
    val species: String?,
    val type: String?
) {

    data class Location(
        val name: String?,
        val url: String?
    )

    data class Origin(
        val name: String?,
        val url: String?
    )
}