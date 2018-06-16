package com.vngrs.vngrs.twittersearch.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vngrs.vngrs.R
import com.vngrs.vngrs.twittersearch.repo.dto.Status
import kotlinx.android.synthetic.main.search_item_view.view.*

/**
 * Created by Eslam Hussein on 6/15/18.
 */
class TwitterSearchAdapter(private var statuses: ArrayList<Status> =
                                   arrayListOf<Status>(), var onItemClicked: ItemClicked)
    : RecyclerView.Adapter<TwitterSearchAdapter.StatusViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        return StatusViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_item_view, parent, false))


    }

    override fun getItemCount(): Int {
        return statuses.size

    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val status = statuses[position]
        holder.statusTextView.text = status.text
        Picasso.get().load(status.entities?.media?.get(0)?.getThumb())
                .placeholder(R.drawable.default_image_thumbnail).into(holder.statusImageView)

        holder.statusItemViewContainer.setOnClickListener {
            onItemClicked.onItemClicked(status)
        }
    }


    fun addItems(newPhotos: List<Status>) {
        val startPos = statuses.size
        statuses.addAll(newPhotos)
        notifyItemRangeInserted(startPos, newPhotos.size)
    }

    class StatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val statusTextView = view.statuesSearchTextView
        val statusImageView = view.statuesSearchImageView
        val statusItemViewContainer = view.statusItemViewContainer


    }

    fun getItems(): ArrayList<Status> = statuses


    interface ItemClicked {
        fun onItemClicked(status: Status)
    }
}