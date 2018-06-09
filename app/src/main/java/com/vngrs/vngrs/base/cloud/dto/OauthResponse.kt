package com.vngrs.vngrs.base.cloud.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Eslam Hussein on 6/9/18.
 */
data class OauthResponse(
        @SerializedName("token_type")
        private var tokenType: String?,
        @SerializedName("access_token")
        private var accessToken: String?)