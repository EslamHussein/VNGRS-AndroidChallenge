package com.vngrs.vngrs.twittersearch.repo.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Eslam Hussein on 6/16/18.
 */
@Parcelize
data class User(val name: String?,
                @SerializedName("screenName") val screen_name: String?,
                @SerializedName("profile_background_image_url_https") val profileBackgroundImage: String?,
                @SerializedName("profile_image_url_https") val profileImage: String?,
                @SerializedName("profile_banner_url") val profileBannerUrl: String?,
                @SerializedName("profile_link_color") val profileLinkColor: String?,
                @SerializedName("followers_count") val followersCount: Int?,
                @SerializedName("statuses_count") val statusesCount: Int?
) : Parcelable
