package com.vngrs.base.cloud

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Eslam Hussein on 6/9/18.
 */
abstract class BaseCloudRepo {


    fun <T> create(clazz: Class<T>, baseUrl: String, isNeedAuthentication: Boolean = true): T {

        return if (isNeedAuthentication) {
            retrofit(baseUrl, provideOkHTTPClientAuthenticated()).create(clazz)
        } else {
            retrofit(baseUrl, provideOkHTTPClient()).create(clazz)
        }
    }


    private fun loggerInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor

    }

    private fun provideOkHTTPClient(): OkHttpClient {

        return OkHttpClient()
                .newBuilder().addInterceptor(loggerInterceptor()).build()

    }

    private fun provideOkHTTPClientAuthenticated(): OkHttpClient {
        return OkHttpClient().newBuilder()
                .addInterceptor(loggerInterceptor())
                .addInterceptor(TwitterAuthenticateRequestInterceptor()).build()

    }

    private fun retrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(baseUrl).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


}