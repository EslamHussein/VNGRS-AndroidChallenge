package com.vngrs.vngrs.twittersearch.view

import com.vngrs.base.view.MvpView
import com.vngrs.vngrs.twittersearch.repo.dto.SearchMetadata
import com.vngrs.vngrs.twittersearch.repo.dto.Status

/**
 * Created by Eslam Hussein on 6/15/18.
 */
interface TwitterSearchView : MvpView {

    fun showLoading()
    fun hideLoading()
    fun showSearchResultSuccess(statuses: ArrayList<Status>, searchMetadata: SearchMetadata, loadMore: Boolean)
    fun showSearchResultFailure(msg: String)
    fun showEmptyKeyword()
}