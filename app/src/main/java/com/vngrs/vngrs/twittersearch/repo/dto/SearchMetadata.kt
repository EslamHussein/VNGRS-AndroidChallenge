package com.vngrs.vngrs.twittersearch.repo.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Eslam Hussein on 6/15/18.
 */
@Parcelize
data class SearchMetadata(
        @SerializedName("max_id_str") val maxIdStr: String,
        @SerializedName("next_results") val nextResults: String?,
        @SerializedName("query") val query: String,
        @SerializedName("refresh_url") val refreshUrl: String,
        @SerializedName("count") val count: Int) : Parcelable {

    fun getNextMaxId(): String? {

        val map = nextResults?.removeSuffix("?")?.split("&")?.associate {
            val (left, right) = it.split("=")
            left to right
        }

        return map?.get("max_id")
    }
}