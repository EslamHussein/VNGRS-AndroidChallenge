package com.vngrs.vngrs.splash.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vngrs.base.view.BaseActivity
import com.vngrs.vngrs.R
import com.vngrs.vngrs.splash.presenter.SplashPresenter
import com.vngrs.vngrs.splash.presenter.SplashPresenterImpl
import com.vngrs.vngrs.twittersearch.repo.TwitterCloudRepoImpl
import com.vngrs.vngrs.twittersearch.view.TwitterSearchActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class SplashActivity : BaseActivity<SplashView, SplashPresenter>(), SplashView {


    override fun createPresenter(): SplashPresenter = SplashPresenterImpl(TwitterCloudRepoImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getPresenter().getAccessToken()

    }

    override fun navigateUserToSearchScreen() {
        startActivity(Intent(this, TwitterSearchActivity::class.java))
        finish()
    }

    override fun getTokenFailure(errorMsg: String) {

        alert(errorMsg) {
            isCancelable = false
            yesButton {

                it.dismiss()
                getPresenter().getAccessToken()

            }
            noButton {

                finish()
            }

        }.show()
    }


    override fun showProgressBar() {
        splashProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        splashProgressBar.visibility = View.INVISIBLE
    }
}
