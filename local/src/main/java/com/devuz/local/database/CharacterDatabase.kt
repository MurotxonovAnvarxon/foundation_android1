package com.devuz.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.devuz.local.dao.CharacterDao
import com.devuz.local.model.CharacterEntity


@Database(version = 1, entities = [CharacterEntity::class], exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun getFavoriteCharacter(): CharacterDao
}