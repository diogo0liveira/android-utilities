package com.dao.utilities.app.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dao.recycler.PagedRecycler
import com.dao.utilities.app.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_row_item.*

class ItemPagedAdapter : PagedRecycler.Adapter<String, ItemPagedAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.view_row_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: String?) {
        item?.let { holder.bind(it) }
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: String) {
            row.text = item
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) =
                (oldItem == newItem)

            override fun areContentsTheSame(oldItem: String, newItem: String) =
                (oldItem == newItem)
        }
    }
}