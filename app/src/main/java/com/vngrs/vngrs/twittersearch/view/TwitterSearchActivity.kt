package com.vngrs.vngrs.twittersearch.view

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vngrs.vngrs.R
import com.vngrs.vngrs.statusdetails.view.ARG_PARAM_STATUS_ID
import com.vngrs.vngrs.statusdetails.view.STATUS_DETAILS_FRAGMENT_TAG
import com.vngrs.vngrs.statusdetails.view.StatusDetailsActivity
import com.vngrs.vngrs.twittersearch.repo.dto.Status
import kotlinx.android.synthetic.main.activity_main.*


private const val TWITTER_SEARCH_FRAGMENT_TAG = "TWITTER_SEARCH"
private const val LAST_KEYWORD_TAG = "LAST_KEYWORD"


class TwitterSearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, TwitterSearchAdapter.ItemClicked {
    private var fragment: Fragment? = null

    private var lastQuarrySearch: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        twitterSearchView.setOnQueryTextListener(this)


        if (savedInstanceState != null) {
            fragment = supportFragmentManager.getFragment(savedInstanceState, STATUS_DETAILS_FRAGMENT_TAG)
            lastQuarrySearch = savedInstanceState.getString(LAST_KEYWORD_TAG)
            twitterSearchView.setQuery(lastQuarrySearch, false)

        } else {
            fragment = supportFragmentManager.findFragmentById(R.id.twitterSearchFragment)

        }


    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_KEYWORD_TAG, lastQuarrySearch)

        if (fragment != null)
            supportFragmentManager.putFragment(outState, TWITTER_SEARCH_FRAGMENT_TAG, fragment!!)


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        notifyFragmentWithQuarry(query)

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun notifyFragmentWithQuarry(quarry: String?) {
        lastQuarrySearch = quarry

        if (fragment != null && fragment is TwitterSearchFragment)
            (fragment as TwitterSearchFragment).updateQuarry(quarry)


    }


    override fun onResume() {
        super.onResume()
        lastQuarrySearch.apply {
            notifyFragmentWithQuarry(this)

        }
    }

    override fun onItemClicked(status: Status) {


        val intent = Intent(this, StatusDetailsActivity::class.java)
        intent.putExtra(ARG_PARAM_STATUS_ID, status.id)
        startActivity(intent)

    }

}

