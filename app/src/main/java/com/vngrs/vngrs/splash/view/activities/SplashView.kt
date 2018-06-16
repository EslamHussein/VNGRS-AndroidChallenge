package com.vngrs.vngrs.splash.view.activities

import com.vngrs.base.view.MvpView

/**
 * Created by Eslam Hussein on 6/14/18.
 */
interface SplashView : MvpView {

    fun navigateUserToSearchScreen()
    fun getTokenFailure(errorMsg: String)
    fun showProgressBar()
    fun hideProgressBar()

}