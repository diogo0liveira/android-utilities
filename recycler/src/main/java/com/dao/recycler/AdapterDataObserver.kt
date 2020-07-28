package com.dao.recycler

import androidx.recyclerview.widget.RecyclerView

internal class AdapterDataObserver constructor(
    private val itemCount: () -> Int,
    private val adapterDataChanged: (isEmpty: Boolean) -> Unit
) : RecyclerView.AdapterDataObserver() {

    override fun onChanged() {
        super.onChanged()
        collectionChanged(itemCount() == 0)
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)
        collectionChanged(itemCount == 0)
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        super.onItemRangeRemoved(positionStart, itemCount)
        collectionChanged(itemCount() == itemCount)
    }

    private fun collectionChanged(isEmpty: Boolean) {
        adapterDataChanged.invoke(isEmpty)
    }
}