package com.vngrs.vngrs.statusdetails.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.vngrs.base.view.BaseFragment
import com.vngrs.util.TextUtils

import com.vngrs.vngrs.R
import com.vngrs.vngrs.statusdetails.presenter.StatusDetailsPresenter
import com.vngrs.vngrs.statusdetails.presenter.StatusDetailsPresenterImpl
import com.vngrs.vngrs.twittersearch.repo.TwitterCloudRepoImpl
import com.vngrs.vngrs.twittersearch.repo.dto.Status
import kotlinx.android.synthetic.main.fragment_status_details.*

const val ARG_PARAM_STATUS_ID = "STATUS_ID"
const val ARG_PARAM_STATUS = "STATUS"

/**
 * A simple [Fragment] subclass.
 * Use the [StatusDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class StatusDetailsFragment : BaseFragment<StatusDetailsView, StatusDetailsPresenter>(), StatusDetailsView {

    private var mStatus: Status? = null

    override fun createPresenter(): StatusDetailsPresenter {
        return StatusDetailsPresenterImpl(TwitterCloudRepoImpl())
    }


    private var mStatusId: String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_status_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {

            showStatusDetailsSuccess(savedInstanceState.getParcelable<Status>(ARG_PARAM_STATUS))
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(ARG_PARAM_STATUS, mStatus)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statusDetailsSwipeRefreshLayout.setOnRefreshListener {
            updateFragment(mStatusId)
        }

    }

    fun updateFragment(statusId: String?) {
        mStatusId = statusId
        getPresenter().getStatusDetails(mStatusId)
    }


    override fun showLoading() {

        statusDetailsSwipeRefreshLayout.isRefreshing = true
        statusDetailsErrorTextView.visibility = View.VISIBLE
        statusDetailsErrorTextView.text = TextUtils.getString(R.string.loading)

    }

    override fun hideLoading() {
        statusDetailsSwipeRefreshLayout.isRefreshing = false

    }

    override fun showStatusDetailsSuccess(status: Status) {
        mStatus = status
        statusDetailsErrorTextView.visibility = View.GONE

        statusDetailsContainer.visibility = View.VISIBLE

        Picasso.get().load(status.user?.profileBackgroundImage)
                .placeholder(R.drawable.default_image_thumbnail).into(coverImageView)

        Picasso.get().load(status.user?.profileImage)
                .placeholder(R.drawable.default_image_thumbnail).into(userProfileImageView)

        statusNumberTextView.text = status.retweetCount.toString()
        followingNumberTextView.text = status.user?.followersCount.toString()

        statusTextView.text = status.text


    }


    override fun showStatusDetailsFailure(msg: String) {

        statusDetailsErrorTextView.visibility = View.VISIBLE
        statusDetailsErrorTextView.text =
                String.format(TextUtils.getString(R.string.something_went_wrong_tap_to_retry), msg)

    }

}
