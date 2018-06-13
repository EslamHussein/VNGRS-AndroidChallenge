package com.vngrs.base.view


import android.os.Bundle
import android.support.v4.app.Fragment
import com.vngrs.base.presenter.MvpPresenter

abstract class BaseFragment<in V : MvpView, P : MvpPresenter<V>> : Fragment(), MvpView {


    private var presenter: P? = null

    protected fun getPresenter(): P {
        if (presenter == null)
            presenter = createPresenter()
        if (presenter == null)
            throw IllegalStateException("createPresenter() implementation returns null!")
        return presenter as P
    }

    protected abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().onAttach(view = this as V)
    }

    override fun onResume() {
        super.onResume()
        getPresenter().onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter().onDetach()
    }


}
