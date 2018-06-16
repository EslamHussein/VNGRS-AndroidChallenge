package com.vngrs

import android.app.Application
import com.squareup.leakcanary.BuildConfig
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Eslam Hussein on 6/11/18.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        initLeakCanary()


    }

    private fun initLeakCanary() {

        if (BuildConfig.DEBUG && LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

    }

    companion object {

        private var instance: App? = null

        fun get(): App {
            if (instance == null)
                throw IllegalStateException("Something went horribly wrong!!, no application attached!")
            return instance as App
        }
    }
}