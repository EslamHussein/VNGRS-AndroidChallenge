package com.vngrs.base.cloud

import android.util.Base64


class CloudConfig {


    companion object {

        const val BASE_URL = "https://api.twitter.com"
        const val SEARCH_URL = "/1.1/search/tweets.json"
        const val TWITTER_OAUTH = "/oauth2/token"
        const val TWITTER_INVALIDATE_TOKEN = "oauth2/invalidate_token"

        const val CONSUMER_KEY = "LgDstqTs4J6hu74DTTRFUjHTK"
        const val CONSUMER_SECRET = "HozJRpkCB6jn519hlJwwDtjqJ0kHJESGbWoqHAQLuUdxZKxsSg"
        const val ACCESS_TOKEN = "200080371-3sUeKPWShmPLeGUZleag03eoBiVfhCb8ZuF3rutt"
        const val ACCESS_TOKEN_SECRET = "x3hqmZMouP2kk0nFzzvI1JL6Vu3FFVkyBPiLFStPpRMTV"

        const val CLIENT_CREDENTIALS = "client_credentials"

        val combined = CloudConfig.CONSUMER_KEY + ":" + CloudConfig.CONSUMER_SECRET
        val base64Encoded = "Basic  ${Base64.encodeToString(combined.toByteArray(), Base64.NO_WRAP)}"

        val info = "This is info"
        fun getMoreInfo(): String {
            return "This is more fun"
        }
    }

}
