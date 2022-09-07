package com.estebanrivera.samplemovies.domain

import android.os.Parcelable
import com.estebanrivera.samplemovies.data.remote.StoriesServer
import com.estebanrivera.samplemovies.data.remote.toItemsDomain


import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbNail: String,
) : Parcelable


data class CharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val thumbNail: String,
    var comics: Comics? = Comics(),
    var series: Series? = Series(),
    var stories: Stories? = Stories(),
    var events: Events? = Events()

)

data class ColorCharacter(
    val id: Int,
    val color: Int,
)

data class Image(
    val path: String,
    val extension: String
)

data class Items(
    var resourceURI: String? = null,
    var name: String? = null

)

data class Comics(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: List<Items> = arrayListOf(),
    var returned: Int? = null

)

data class Series(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: List<Items> = arrayListOf(),
    var returned: Int? = null
)

data class Stories(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: List<Items> = arrayListOf(),
    var returned: Int? = null

)

data class Events(
    var available: Int? = null,
    var collectionURI: String? = null,
    var items: List<Items> = arrayListOf(),
    var returned: Int? = null
)


