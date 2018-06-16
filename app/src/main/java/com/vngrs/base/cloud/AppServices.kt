package com.vngrs.base.cloud

import com.vngrs.base.cloud.CloudConfig.CloudConfig.SEARCH_URL
import com.vngrs.base.cloud.CloudConfig.CloudConfig.TWITTER_OAUTH
import com.vngrs.base.cloud.CloudConfig.CloudConfig.TWITTER_STATUS_DETAILS
import com.vngrs.base.cloud.dto.AccessToken
import com.vngrs.vngrs.twittersearch.repo.dto.SearchResponse
import com.vngrs.vngrs.twittersearch.repo.dto.Status
import io.reactivex.Observable
import retrofit2.http.*


interface AppServices {

    @FormUrlEncoded
    @POST(TWITTER_OAUTH)
    fun oauth2Token(@Header("Authorization") authorization: String, @Field("grant_type") grantType: String): Observable<AccessToken>

    @GET(SEARCH_URL)
    fun searchInTweeted(@Query("q") searchKeyWord: String,
                        @Query("max_id") maxId: String? = null,
                        @Query("count") count: Int? = null): Observable<SearchResponse>

    @GET(TWITTER_STATUS_DETAILS)
    fun statusDetails(@Query("id") statusId: String): Observable<Status>
}
