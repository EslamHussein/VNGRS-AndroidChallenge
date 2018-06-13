package com.vngrs.vngrs.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.vngrs.util.PreferencesHelper
import com.vngrs.vngrs.R
import com.vngrs.vngrs.repo.TwitterCloudRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val cloudRepo = TwitterCloudRepoImpl()

        cloudRepo.getAccessToken().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeBy(

                onError = {
                    Log.d("TAG", it.localizedMessage)
                },
                onNext = {
                    Log.d("TAG", it.getBearer())
                    Log.d("TAG", PreferencesHelper.getTwitterToken())
                }
        )
    }
}
