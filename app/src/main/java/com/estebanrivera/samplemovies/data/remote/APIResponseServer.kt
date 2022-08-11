package com.estebanrivera.samplemovies.data.remote

import android.os.Parcelable
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ATTRIBUTION_HTML
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ATTRIBUTION_TEXT
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_AVAILABLE
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_CHARACTER
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_CODE
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_COLLECTIONS_URI
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_COMICS
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_COPYRIGHT
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_DATA
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_DESCRIPTION
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ETAG
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_EVENTS
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ID
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_IMAGE
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_IMAGE_EXTENSION
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_IMAGE_PATH
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ITEMS
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_NAME
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_RESOURCE_URI
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_RETURNED
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_SERIES
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_STATUS
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_STORIES
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_THUMBNAIL
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class BaseResponse<out T>(
    @SerializedName(KEY_CODE) val code: Int,
    @SerializedName(KEY_STATUS) val status: String,
    @SerializedName(KEY_COPYRIGHT) val copyRight: String,
    @SerializedName(KEY_ATTRIBUTION_TEXT) val attributionText: String,
    @SerializedName(KEY_ATTRIBUTION_HTML) val attributionHTML: String,
    @SerializedName(KEY_ETAG) val etag: String,
    @SerializedName(KEY_DATA) val data: DataResponse<T>
)

data class DataResponse<out T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: T
)


@Parcelize
data class CharacterServer(
    @SerializedName(KEY_ID) val id: Int,
    @SerializedName(KEY_NAME) val name: String?,
    @SerializedName(KEY_DESCRIPTION) val description: String?,
    @SerializedName(KEY_THUMBNAIL) val thumbnail: ImageServer,
    @SerializedName(KEY_IMAGE) val images: List<ImageServer>,
    @SerializedName(KEY_COMICS) var comics: ComicsServer? = ComicsServer(),
    @SerializedName(KEY_SERIES) var series: SeriesServer? = SeriesServer(),
    @SerializedName(KEY_STORIES) var stories: StoriesServer? = StoriesServer(),
    @SerializedName(KEY_EVENTS) var events: EventsServer? = EventsServer()
) : Parcelable


@Parcelize
data class ImageServer(
    @SerializedName(KEY_IMAGE_PATH) val path: String,
    @SerializedName(KEY_IMAGE_EXTENSION) val extension: String
) : Parcelable


@Parcelize
data class ItemsServer(
    @SerializedName(KEY_RESOURCE_URI) var resourceURI: String? = null,
    @SerializedName(KEY_NAME) var name: String? = null

): Parcelable

@Parcelize
data class ComicsServer(
    @SerializedName(KEY_AVAILABLE) var available: Int? = null,
    @SerializedName(KEY_COLLECTIONS_URI) var collectionURI: String? = null,
    @SerializedName(KEY_ITEMS) var items: ArrayList<ItemsServer> = arrayListOf(),
    @SerializedName(KEY_RETURNED) var returned: Int? = null

): Parcelable

@Parcelize
data class SeriesServer(
    @SerializedName(KEY_AVAILABLE) var available: Int? = null,
    @SerializedName(KEY_COLLECTIONS_URI) var collectionURI: String? = null,
    @SerializedName(KEY_ITEMS) var items: ArrayList<ItemsServer> = arrayListOf(),
    @SerializedName(KEY_RETURNED) var returned: Int? = null
): Parcelable

@Parcelize
data class StoriesServer(
    @SerializedName(KEY_AVAILABLE) var available: Int? = null,
    @SerializedName(KEY_COLLECTIONS_URI) var collectionURI: String? = null,
    @SerializedName(KEY_ITEMS) var items: ArrayList<ItemsServer> = arrayListOf(),
    @SerializedName(KEY_RETURNED) var returned: Int? = null

): Parcelable

@Parcelize
data class EventsServer(
    @SerializedName(KEY_AVAILABLE) var available: Int? = null,
    @SerializedName(KEY_COLLECTIONS_URI) var collectionURI: String? = null,
    @SerializedName(KEY_ITEMS) var items: ArrayList<ItemsServer> = arrayListOf(),
    @SerializedName(KEY_RETURNED) var returned: Int? = null
): Parcelable

