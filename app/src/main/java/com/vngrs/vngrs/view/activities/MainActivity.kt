package com.vngrs.vngrs.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vngrs.vngrs.R
import com.vngrs.vngrs.repo.TwitterCloudRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cloudRepo = TwitterCloudRepoImpl()


        cloudRepo.searchByKeyWord("test").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeBy(

                onError = {
                    Log.d("TAG", it.localizedMessage)
                },
                onNext = {
                    Log.d("TAG", it)
                }
        )
    }
}

