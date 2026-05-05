package com.devuz.local.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devuz.local.model.CharacterEntity


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterEntity: CharacterEntity)

    @Query("SELECT * FROM character")
    suspend fun getAllCharacterDetails(): List<CharacterEntity?>

    @Query("DELETE FROM character WHERE id = :id")
    suspend fun deleteCharacterById(id: Long)

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getByIdCharacter(id:Long):CharacterEntity

}