package com.vngrs

import android.app.Application

/**
 * Created by Eslam Hussein on 6/11/18.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this

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