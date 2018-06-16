package com.vngrs.vngrs.statusdetails.view

import com.vngrs.base.view.MvpView
import com.vngrs.vngrs.twittersearch.repo.dto.Status

/**
 * Created by Eslam Hussein on 6/16/18.
 */
interface StatusDetailsView : MvpView {

    fun showLoading()
    fun hideLoading()
    fun showStatusDetailsSuccess(status: Status)
    fun showStatusDetailsFailure(msg: String)

}