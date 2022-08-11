package com.estebanrivera.samplemovies.data.remote

import android.os.Parcelable
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ATTRIBUTION_HTML
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ATTRIBUTION_TEXT
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_CHARACTER
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_CODE
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_COPYRIGHT
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_DATA
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_DESCRIPTION
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ETAG
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_ID
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_IMAGE_EXTENSION
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_IMAGE_PATH
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_NAME
import com.estebanrivera.samplemovies.data.remote.APIConstants.KEY_STATUS
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

/*data class CharacterResponseServer(
    @SerializedName(KEY_CHARACTER) val results: List<CharacterServer>
)*/

@Parcelize
data class CharacterServer(
    @SerializedName(KEY_ID) val id: Int,
    @SerializedName(KEY_NAME) val name: String?,
    @SerializedName(KEY_DESCRIPTION) val description: String?,
    @SerializedName(KEY_THUMBNAIL) val thumbnail: Image,
    val images: List<Image>

    ) : Parcelable

@Parcelize
data class Image(
    @SerializedName(KEY_IMAGE_PATH) val path: String,
    @SerializedName(KEY_IMAGE_EXTENSION) val extension: String
) : Parcelable

