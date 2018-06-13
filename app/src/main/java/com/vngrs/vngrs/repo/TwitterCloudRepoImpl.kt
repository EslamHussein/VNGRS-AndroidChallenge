package com.vngrs.vngrs.repo

import com.vngrs.base.cloud.AppServices
import com.vngrs.base.cloud.BaseCloudRepo
import com.vngrs.base.cloud.CloudConfig
import com.vngrs.base.cloud.dto.AccessToken
import com.vngrs.util.PreferencesHelper
import io.reactivex.Observable

/**
 * Created by Eslam Hussein on 6/9/18.
 */

interface TwitterCloudRepo {
    fun getAccessToken(): Observable<AccessToken>

    fun searchByKeyWord(keyword: String): Observable<String>

}

class TwitterCloudRepoImpl : BaseCloudRepo(), TwitterCloudRepo {
    override fun searchByKeyWord(keyword: String): Observable<String> {
        return create(AppServices::class.java, baseUrl = CloudConfig.BASE_URL).searchInTweeted(keyword)

    }

    override fun getAccessToken(): Observable<AccessToken> {
        return create(AppServices::class.java, baseUrl = CloudConfig.BASE_URL,
                isNeedAuthentication = false).oauth2Token(CloudConfig.CLIENT_CREDENTIALS).doOnNext {
            PreferencesHelper.setTwitterToken(twitterToken = it.getBearer())
        }
    }
}