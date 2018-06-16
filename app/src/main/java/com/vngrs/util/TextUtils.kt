package com.vngrs.util


import androidx.annotation.StringRes
import com.vngrs.App


object TextUtils {

    private const val EMPTY_STRING_PATTERN = "^$|\\s+"

    fun getString(@StringRes resId: Int): String {
        return App.get().getString(resId)
    }


    fun isEmptyString(str: String?): Boolean {
        return str == null || str.isEmpty() ||
                str.matches(EMPTY_STRING_PATTERN.toRegex())
    }
}
