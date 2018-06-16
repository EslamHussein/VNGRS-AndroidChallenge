package com.vngrs.base.error

import androidx.annotation.IntDef
import com.vngrs.util.TextUtils
import com.vngrs.vngrs.R
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class AppException @JvmOverloads constructor(@param:ErrorCode @get:ErrorCode
                                             val errorCode: Int, val errorMessage: String, original: Throwable? = null) : Exception(original) {

    @IntDef(NETWORK_ERROR, NO_DATA_ERROR, UNKNOWN_ERROR)
    @kotlin.annotation.Retention(value = AnnotationRetention.SOURCE)
    annotation class ErrorCode

    companion object {

        const val NETWORK_ERROR = 1
        const val NO_DATA_ERROR = 2
        const val UNKNOWN_ERROR = 3


        fun adapt(t: Throwable): Throwable {
            return if (t is UnknownHostException || t is SocketException || t is SocketTimeoutException) {
                AppException(NETWORK_ERROR, TextUtils.getString(R.string.no_internet_connection), t)
            } else {
                AppException(UNKNOWN_ERROR, TextUtils.getString(R.string.something_went_wrong), t)
            }
        }
    }

}
