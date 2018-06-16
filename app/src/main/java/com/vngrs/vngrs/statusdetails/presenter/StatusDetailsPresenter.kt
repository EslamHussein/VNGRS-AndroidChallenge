package com.vngrs.vngrs.statusdetails.presenter

import com.vngrs.base.error.AppException
import com.vngrs.base.presenter.BasePresenter
import com.vngrs.util.TextUtils
import com.vngrs.vngrs.R
import com.vngrs.vngrs.statusdetails.view.StatusDetailsView
import com.vngrs.vngrs.twittersearch.repo.TwitterCloudRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by Eslam Hussein on 6/14/18.
 */
abstract class StatusDetailsPresenter : BasePresenter<StatusDetailsView>() {
    abstract fun getStatusDetails(statusId: String?)
}


class StatusDetailsPresenterImpl(private val cloudRepo: TwitterCloudRepo) : StatusDetailsPresenter() {
    override fun getStatusDetails(statusId: String?) {


        if (statusId == null || statusId.isEmpty()) {
            view?.showStatusDetailsFailure(TextUtils.getString(R.string.something_went_wrong))
            return
        }

        view?.showLoading()
        addDisposable(cloudRepo.getStatusDetails(statusId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribeBy(
                        onNext = {
                            view?.showStatusDetailsSuccess(it)

                        },
                        onError = {
                            if (it is AppException)
                                view?.showStatusDetailsFailure(it.errorMessage)
                            view?.hideLoading()

                        },
                        onComplete = {

                            view?.hideLoading()
                        }

                ))

    }


}