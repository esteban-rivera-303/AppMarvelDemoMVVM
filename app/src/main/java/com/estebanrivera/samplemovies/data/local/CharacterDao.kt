package com.estebanrivera.samplemovies.data.local

import androidx.room.*

@Dao
interface CharacterDao {

    @Query("SELECT * FROM Character WHERE character_id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characterEntity: CharacterEntity)

    @Delete
    suspend fun deleteCharacter(characterEntity: CharacterEntity)

    // Color
    @Query("SELECT * FROM color_character WHERE character_id = :id")
    suspend fun getColorCharacterById(id: Int): ColorCharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(colorCharacterEntity: ColorCharacterEntity)
}