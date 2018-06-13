package com.vngrs.base.cloud

import com.vngrs.base.cloud.dto.AccessToken
import io.reactivex.Observable
import retrofit2.http.*


interface AppServices {

    @FormUrlEncoded
    @POST(CloudConfig.TWITTER_OAUTH)
    fun oauth2Token(@Field("grant_type") grantType: String): Observable<AccessToken>

    @GET(CloudConfig.SEARCH_URL)
    fun searchInTweeted(@Query("q") searchKeyWord: String,
                        @Query("max_id") maxId: String? = null,
                        @Query("count") count: Int? = null): Observable<String>
}
