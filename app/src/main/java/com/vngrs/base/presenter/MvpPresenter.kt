package com.vngrs.base.presenter


import com.vngrs.base.view.MvpView

/**
 * Created by Eslam Hussein on 5/14/16.
 */
interface MvpPresenter<in P : MvpView> {

    /**
     * Called when an `MvpView` is attached to this presenter.
     *
     * @param view The attached `MvpView`
     */
    fun onAttach(view: P)

    /**
     * Called when the view is resumed according to Android components
     * NOTE: this method will only be called for presenters that override it.
     */
    fun onResume()

    /**
     * Called when an `MvpView` is detached from this presenter.
     */
    fun onDetach()

}
