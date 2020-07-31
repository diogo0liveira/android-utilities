package com.dao.recycler

import android.util.Log
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PagedRecycler {
    abstract class Adapter<T : Any, V : RecyclerView.ViewHolder>
    constructor(comparator: DiffUtil.ItemCallback<T>) :
        PagingDataAdapter<T, V>(comparator), AdapterBindView<T?, V> {

        private val adapterObserver: AdapterDataObserver by lazy {
            AdapterDataObserver(this@Adapter::getItemCount) {
                changedListener?.onCollectionChanged(it)
            }
        }

        override var changedListener: OnAdapterDataChangedListener? = null

        override fun onBindViewHolder(holder: V, position: Int) {
            onBindViewHolder(holder, getItem(position), position)
        }

        override fun registerAdapterObserver() {
            try {
                registerAdapterDataObserver(adapterObserver)
            } catch (e: RuntimeException) {
                Log.i(this.javaClass.simpleName, e.toString())
            }
        }

        override fun unregisterAdapterObserver() {
            try {
                unregisterAdapterDataObserver(adapterObserver)
            } catch (e: RuntimeException) {
                Log.e(this.javaClass.simpleName, e.toString())
            }
        }
    }
}