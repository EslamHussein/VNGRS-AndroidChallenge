package com.vngrs.util

import android.content.Context
import android.content.SharedPreferences
import com.vngrs.App

/**
 * Created by Eslam Hussein on 6/11/18.
 */
object PreferencesHelper {


    private const val TWITTER_TOKEN_KEY = "TWITTER_TOKEN"

    private const val APP_SHARD = "COM.VNGRS.TWITTER"


    /**
     * Get SharedPreferences
     */
    private fun getSharedPreferences(context: Context = App.get()): SharedPreferences {
        return context.getSharedPreferences(APP_SHARD, Context.MODE_PRIVATE)
    }

    fun setTwitterToken(context: Context = App.get(), twitterToken: String) {
        getSharedPreferences(context).edit().putString(TWITTER_TOKEN_KEY, twitterToken).apply()
    }

    fun getTwitterToken(context: Context = App.get()): String {
        return getSharedPreferences(context).getString(TWITTER_TOKEN_KEY, "")
    }
}