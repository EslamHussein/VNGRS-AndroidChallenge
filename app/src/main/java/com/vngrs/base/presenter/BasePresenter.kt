package com.vngrs.base.presenter


import com.vngrs.base.view.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

import java.lang.ref.WeakReference

/**
 * Created Eslam Hussein on 5/14/16.
 */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {

    private var viewRef: WeakReference<V>? = null

    private var disposables: CompositeDisposable? = null
    /**
     * @return True if the view this presenter is attached to still exists and not garbage collected
     * since we are holding it through a `WeakReference`
     */
    protected val isViewAttached: Boolean
        get() = viewRef != null && viewRef?.get() != null

    protected val view: V?
        get() = viewRef?.get()

    override fun onAttach(view: V) {
        viewRef = WeakReference(view)
    }

    fun addDisposable(disposable: Disposable) {
        if (disposables == null)
            disposables = CompositeDisposable()

        disposables?.add(disposable)
    }


    override fun onResume() {
        // Not mandatory for all views, if views are interested in receiving this event, they should
        // override this method
    }

    override fun onDetach() {
        if (viewRef != null) {
            viewRef!!.clear()
            viewRef = null
        }
        if (disposables != null)
            disposables?.dispose()

    }

}
