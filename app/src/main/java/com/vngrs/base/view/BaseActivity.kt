package com.vngrs.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vngrs.base.presenter.MvpPresenter

abstract class BaseActivity<in V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(), MvpView {

    private var presenter: P? = null

    protected fun getPresenter(): P {
        if (presenter == null)
            presenter = createPresenter()
        if (presenter == null)
            throw IllegalStateException("createPresenter() implementation returns null!")
        return presenter as P
    }

    protected abstract fun createPresenter(): P

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().onAttach(view = this as V)
    }

    public override fun onResume() {
        super.onResume()
        getPresenter().onResume()
    }

    public override fun onDestroy() {
        super.onDestroy()
        getPresenter().onDetach()
    }
}