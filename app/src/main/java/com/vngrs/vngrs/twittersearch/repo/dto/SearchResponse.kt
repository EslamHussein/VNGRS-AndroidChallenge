package com.vngrs.vngrs.twittersearch.repo.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Eslam Hussein on 6/15/18.
 */

@Parcelize
data class SearchResponse(@SerializedName("statuses") val statuses: ArrayList<Status>?,
                          @SerializedName("search_metadata") val searchMetadata: SearchMetadata) : Parcelable

@Parcelize
data class Entities(var media: List<Media>?) : Parcelable

@Parcelize
data class Media(@SerializedName("media_url") var mediaUrl: String?) : Parcelable {
    fun getThumb(): String? {
        return "$mediaUrl:thumb"
    }

    fun getMedium(): String? {
        return "$mediaUrl:medium"
    }

}
