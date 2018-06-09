package com.vngrs.vngrs.base.cloud

import com.vngrs.vngrs.base.cloud.dto.OauthResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface AppServices {

    @GET(CloudConfig.TWITTER_OAUTH)
    fun oauth2Token(): Observable<OauthResponse>

}
