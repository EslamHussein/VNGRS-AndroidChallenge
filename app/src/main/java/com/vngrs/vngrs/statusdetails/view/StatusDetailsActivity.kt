package com.vngrs.vngrs.statusdetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vngrs.vngrs.R
import kotlinx.android.synthetic.main.activity_status_details.*


const val STATUS_DETAILS_FRAGMENT_TAG = "StatusDetailsFragment"

class StatusDetailsActivity : AppCompatActivity() {
    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_details)
        setSupportActionBar(statusDetailsToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val statusId: String = intent.getStringExtra(ARG_PARAM_STATUS_ID)

        fragment = if (savedInstanceState != null) {
            supportFragmentManager.getFragment(savedInstanceState, STATUS_DETAILS_FRAGMENT_TAG)
        } else {
            supportFragmentManager.findFragmentById(R.id.statusDetailsFragment)

        }
        if (fragment != null && fragment is StatusDetailsFragment
                && savedInstanceState == null)
            (fragment as StatusDetailsFragment).updateFragment(statusId)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        supportFragmentManager.putFragment(outState, STATUS_DETAILS_FRAGMENT_TAG, fragment!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
