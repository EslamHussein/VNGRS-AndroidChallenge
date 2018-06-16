package com.vngrs.vngrs.twittersearch.repo

import com.vngrs.base.cloud.AppServices
import com.vngrs.base.cloud.BaseCloudRepo
import com.vngrs.base.cloud.CloudConfig.CloudConfig.BASE_URL
import com.vngrs.base.cloud.CloudConfig.CloudConfig.CLIENT_CREDENTIALS
import com.vngrs.base.cloud.CloudConfig.CloudConfig.getBase64Encoded
import com.vngrs.base.cloud.dto.AccessToken
import com.vngrs.base.error.AppException
import com.vngrs.base.error.ErrorManager
import com.vngrs.util.PreferencesHelper
import com.vngrs.util.TextUtils
import com.vngrs.vngrs.R
import com.vngrs.vngrs.twittersearch.repo.dto.SearchResponse
import com.vngrs.vngrs.twittersearch.repo.dto.Status
import io.reactivex.Observable


/**
 * Created by Eslam Hussein on 6/9/18.
 */

interface TwitterCloudRepo {
    fun getAccessToken(): Observable<AccessToken>
    fun searchByKeyWord(keyword: String, maxId: String? = null): Observable<SearchResponse>
    fun getStatusDetails(statusId: String): Observable<Status>

}

class TwitterCloudRepoImpl : BaseCloudRepo(), TwitterCloudRepo {
    override fun getStatusDetails(statusId: String): Observable<Status> {

        return ErrorManager.wrap(create(AppServices::class.java,
                baseUrl = BASE_URL).statusDetails(statusId))
    }

    override fun searchByKeyWord(keyword: String, maxId: String?): Observable<SearchResponse> {

        return ErrorManager.wrap(create(AppServices::class.java,
                baseUrl = BASE_URL).searchInTweeted(keyword, maxId))
                .flatMap {
                    if (it.statuses == null || it.statuses.isEmpty() || it.statuses.size < 0)
                        Observable.error(AppException(AppException.NO_DATA_ERROR, TextUtils.getString(R.string.no_results_found)))
                    else
                        Observable.just(it)
                }

    }

    override fun getAccessToken(): Observable<AccessToken> {

        return ErrorManager.wrap(create(AppServices::class.java, baseUrl = BASE_URL,
                isNeedAuthentication = false).oauth2Token(
                getBase64Encoded(), CLIENT_CREDENTIALS)).doOnNext {
            PreferencesHelper.setTwitterToken(twitterToken = it.getBearer())
        }
    }
}