package com.vngrs.vngrs.twittersearch.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vngrs.base.view.BaseFragment
import com.vngrs.util.EndlessRecyclerViewScrollListener
import com.vngrs.vngrs.R
import com.vngrs.vngrs.twittersearch.presenter.TwitterSearchPresenter
import com.vngrs.vngrs.twittersearch.presenter.TwitterSearchPresenterImpl
import com.vngrs.vngrs.twittersearch.repo.TwitterCloudRepoImpl
import com.vngrs.vngrs.twittersearch.repo.dto.SearchMetadata
import com.vngrs.vngrs.twittersearch.repo.dto.Status
import kotlinx.android.synthetic.main.fragment_twitter_search.*

private const val STATUS_LIST_TAG = "STATUS_LIST"
private const val META_DATA_TAG = "META_DATA"


class TwitterSearchFragment : BaseFragment<TwitterSearchView, TwitterSearchPresenter>(), TwitterSearchView, TwitterSearchAdapter.ItemClicked {


    private var viewAdapter: TwitterSearchAdapter? = null
    private lateinit var viewManager: LinearLayoutManager
    private var lastSearchMetadata: SearchMetadata? = null
    private var onItemClicked: TwitterSearchAdapter.ItemClicked? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_twitter_search, container, false)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onItemClicked = activity as? TwitterSearchAdapter.ItemClicked

    }

    override fun onDetach() {
        super.onDetach()
        onItemClicked = null
    }

    override fun createPresenter(): TwitterSearchPresenter {

        return TwitterSearchPresenterImpl(TwitterCloudRepoImpl())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        swipeRefreshTwitterSearch.isEnabled = false



        statusesSearchRecyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(viewManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                lastSearchMetadata?.nextResults.apply {
                    getPresenter().search(lastSearchMetadata?.query,
                            loadMore = true, maxId = lastSearchMetadata?.getNextMaxId())
                }
            }

        })

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {

            val savedList = savedInstanceState.getParcelableArrayList<Status>(STATUS_LIST_TAG)

            val saveMetadata = savedInstanceState.getParcelable<SearchMetadata>(META_DATA_TAG)


            showSearchResultSuccess(savedList, saveMetadata, false)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(STATUS_LIST_TAG, viewAdapter?.getItems())
        outState.putParcelable(META_DATA_TAG, lastSearchMetadata)
        super.onSaveInstanceState(outState)

    }

    override fun showLoading() {
        swipeRefreshTwitterSearch.isRefreshing = true
        if (viewAdapter?.itemCount ?: 0 <= 0) {
            loadingStatusTextView.text = getString(R.string.loading)
        } else {
            loadingStatusTextView.visibility = View.GONE
        }
    }

    override fun hideLoading() {
        swipeRefreshTwitterSearch.isRefreshing = false


    }

    override fun showSearchResultSuccess(statuses: ArrayList<Status>, searchMetadata: SearchMetadata, loadMore: Boolean) {
        lastSearchMetadata = searchMetadata
        loadingStatusTextView.visibility = View.GONE

        if (!loadMore) {
            viewAdapter = TwitterSearchAdapter(onItemClicked = this)

            statusesSearchRecyclerView.apply {
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }


        viewAdapter?.addItems(statuses)
    }

    override fun showSearchResultFailure(msg: String) {
        if (viewAdapter?.itemCount ?: 0 <= 0) {
            loadingStatusTextView.text = msg
            loadingStatusTextView.visibility = View.VISIBLE

        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()

        }
    }

    override fun showEmptyKeyword() {
        Toast.makeText(context, getString(R.string.please_enter_keyword), Toast.LENGTH_LONG).show()

    }

    fun updateQuarry(mQuarry: String?) {

        getPresenter().search(mQuarry)
    }

    override fun onItemClicked(status: Status) {

        onItemClicked?.onItemClicked(status)


    }


}
