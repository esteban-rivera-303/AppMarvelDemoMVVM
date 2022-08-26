package com.estebanrivera.samplemovies.data.remote

import com.estebanrivera.samplemovies.BuildConfig

object APIConstants {

    const val BASE_API_URL = "https://gateway.marvel.com/v1/public/"

    const val GET_ALL_CHARACTERS = "characters"
    const val GET_DETAILS_CHARACTER = "characters/{id}"

    const val PUBLIC_KEY = "4212fa6697aaa3ea248b31378a2dca65"
    const val PRIVATE_KEY = BuildConfig.PRIVATE_KEY

    //Base Response Server
    const val KEY_CODE = "code"
    const val KEY_STATUS = "status"
    const val KEY_COPYRIGHT = "copyright"
    const val KEY_ATTRIBUTION_TEXT = "attributionText"
    const val KEY_ATTRIBUTION_HTML = "attributionHTML"
    const val KEY_ETAG = "etag"
    const val KEY_DATA = "data"

    // Character
    const val KEY_CHARACTER = "Character"
    const val KEY_ID = "id"
    const val KEY_NAME = "name"
    const val KEY_IMAGE = "image"
    const val KEY_DESCRIPTION = "description"
    const val KEY_RESOURCE_URI = "resourceURI"
    const val KEY_THUMBNAIL = "thumbnail"
    const val KEY_COMICS = "comics"
    const val KEY_STORIES = "stories"
    const val KEY_EVENTS = "events"
    const val KEY_SERIES = "series"


    // IMAGE
    const val KEY_IMAGE_EXTENSION = "extension"
    const val KEY_IMAGE_PATH = "path"


    const val KEY_AVAILABLE = "available"
    const val KEY_COLLECTIONS_URI = "collectionURI"
    const val KEY_RETURNED = "returned"
    const val KEY_ITEMS = "items"
}
