package com.vngrs.base.cloud

import android.util.Base64


class CloudConfig {


    companion object CloudConfig {

        const val BASE_URL = "https://api.twitter.com"
        const val SEARCH_URL = "/1.1/search/tweets.json"
        const val TWITTER_OAUTH = "/oauth2/token"
        const val TWITTER_INVALIDATE_TOKEN = "oauth2/invalidate_token"
        const val TWITTER_STATUS_DETAILS = "/1.1/statuses/show.json"

//        https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672

        const val CONSUMER_KEY = "vvsKQxy6uDwvBOmP4ohLHcMVY"
        const val CONSUMER_SECRET = "LHTa1ubtFh6NsuRlmcAtn1v1pRSULFKiJuuT23BJRloqifzxe0"

        const val CLIENT_CREDENTIALS = "client_credentials"

        fun getBase64Encoded(): String {
            val combined = "$CONSUMER_KEY:$CONSUMER_SECRET"
            return "Basic ${Base64.encodeToString(combined.toByteArray(), Base64.NO_WRAP)}"

        }


    }

}
