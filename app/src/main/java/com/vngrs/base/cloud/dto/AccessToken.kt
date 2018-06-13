package com.vngrs.base.cloud.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Eslam Hussein on 6/9/18.
 */
data class AccessToken(
        @SerializedName("token_type")
        private var tokenType: String?,
        @SerializedName("access_token")
        private var accessToken: String?) {

    fun getBearer(): String {
        return "Bearer $accessToken"
    }
}