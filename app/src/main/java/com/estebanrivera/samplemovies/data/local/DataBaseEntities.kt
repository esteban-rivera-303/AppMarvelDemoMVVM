package com.estebanrivera.samplemovies.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.estebanrivera.samplemovies.data.remote.StoriesServer
import com.estebanrivera.samplemovies.data.remote.toItemsDomain
import com.estebanrivera.samplemovies.domain.ColorCharacter
import com.estebanrivera.samplemovies.domain.Stories


@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey @ColumnInfo(name = "character_id") val id: Int,
    @ColumnInfo(name = "character_name") val name: String?,
    @ColumnInfo(name = "character_description") val description: String?,
    @ColumnInfo(name = "character_thumbnail") val thumbnail: String?,
)

@Entity(tableName = "color_character")
data class ColorCharacterEntity(
    @PrimaryKey @ColumnInfo(name = "character_id") val id: Int,
    @ColumnInfo(name = "color") val color: Int?,
)


fun ColorCharacterEntity.toColorCharacterDomain(): ColorCharacter {
    return ColorCharacter(
        id,
        color!!
    )
}
