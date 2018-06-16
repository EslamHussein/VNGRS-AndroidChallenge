package com.vngrs.vngrs.twittersearch.presenter

import com.vngrs.base.error.AppException
import com.vngrs.base.presenter.BasePresenter
import com.vngrs.vngrs.twittersearch.repo.TwitterCloudRepo
import com.vngrs.vngrs.twittersearch.view.TwitterSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


/**
 * Created by Eslam Hussein on 6/14/18.
 */
abstract class TwitterSearchPresenter : BasePresenter<TwitterSearchView>() {
    abstract fun search(keyword: String?, loadMore: Boolean = false, maxId: String? = null)
}


class TwitterSearchPresenterImpl(private val cloudRepo: TwitterCloudRepo) : TwitterSearchPresenter() {
    override fun search(keyword: String?, loadMore: Boolean, maxId: String?) {

        if (keyword == null || keyword.isEmpty()) {
            view?.showEmptyKeyword()
            return
        }

        view?.showLoading()
        addDisposable(cloudRepo.searchByKeyWord(keyword).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribeBy(
                        onNext = {
                            view?.showSearchResultSuccess(it.statuses!!, it.searchMetadata, loadMore)

                        },
                        onError = {
                            if (it is AppException) {
                                view?.showSearchResultFailure(it.errorMessage)
                            }

                            view?.hideLoading()

                        },
                        onComplete = {

                            view?.hideLoading()
                        }

                ))


    }

}