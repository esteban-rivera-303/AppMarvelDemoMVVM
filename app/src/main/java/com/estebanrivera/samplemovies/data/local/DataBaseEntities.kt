package com.estebanrivera.samplemovies.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey @ColumnInfo( name = "character_id") val id: Int,
    @ColumnInfo( name = "character_name") val name: String?,
    @ColumnInfo( name = "character_description") val description: String?,
    @ColumnInfo( name = "character_thumbnail") val thumbnail: String?,
)
