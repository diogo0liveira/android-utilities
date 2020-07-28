package com.dao.utilities.app.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dao.recycler.Recycler
import com.dao.utilities.app.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_row_item.*

class ItemAdapter : Recycler.Adapter<String, ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.view_row_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: String) {
        holder.bind(item)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: String) {
            row.text = item
        }
    }
}