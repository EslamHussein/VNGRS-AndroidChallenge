package com.vngrs.base.cloud

import com.vngrs.util.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response

class TwitterAuthenticateRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {

        val original = chain!!.request()

        val request = original.newBuilder()
                .header("Authorization", PreferencesHelper.getTwitterToken())
                .method(original.method(), original.body())
                .build()

        return chain.proceed(request)!!


    }
}




