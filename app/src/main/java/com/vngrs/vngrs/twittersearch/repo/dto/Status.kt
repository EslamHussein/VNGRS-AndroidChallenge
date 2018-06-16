package com.vngrs.vngrs.twittersearch.repo.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Eslam Hussein on 6/15/18.
 */
@Parcelize
data class Status(@SerializedName("id_str") val id: String,
                  val text: String, val entities: Entities?, val user: User?,
                  @SerializedName("retweet_count") val retweetCount: Int) : Parcelable