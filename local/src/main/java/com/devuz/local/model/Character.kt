package com.devuz.local.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Long,
    @SerializedName("character_name")
    val characterName: String,
    @SerializedName("image")
    val image: String
)