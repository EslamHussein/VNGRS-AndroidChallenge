package com.vngrs.vngrs.splash.presenter

import com.vngrs.base.presenter.BasePresenter
import com.vngrs.vngrs.splash.view.activities.SplashView
import com.vngrs.vngrs.twittersearch.repo.TwitterCloudRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by Eslam Hussein on 6/14/18.
 */
abstract class SplashPresenter : BasePresenter<SplashView>() {
    abstract fun getAccessToken()
}


class SplashPresenterImpl(private val cloudRepo: TwitterCloudRepo) : SplashPresenter() {
    override fun getAccessToken() {
        view?.showProgressBar()
        addDisposable(cloudRepo.getAccessToken().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribeBy(
                        onNext = {
                            view?.navigateUserToSearchScreen()
                        },
                        onError = {
                            view?.getTokenFailure(it.localizedMessage)
                        },
                        onComplete = {
                            view?.hideProgressBar()
                        }
                )
        )
    }
}