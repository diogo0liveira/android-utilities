package com.dao.recycler

import android.util.Log
import androidx.recyclerview.widget.RecyclerView

abstract class Recycler {
    abstract class Adapter<T, V : RecyclerView.ViewHolder> :
        RecyclerView.Adapter<V>(), AdapterBindView<T, V> {

        private val adapterObserver: AdapterDataObserver by lazy {
            AdapterDataObserver(this@Adapter::getItemCount) {
                changedListener?.onCollectionChanged(it)
            }
        }

        override var changedListener: OnAdapterDataChangedListener? = null

        private var items: List<T> = listOf()

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: V, position: Int) {
            onBindViewHolder(holder, items[position], position)
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

        fun setDataList(list: List<T>) {
            this.items = list
            notifyDataSetChanged()
        }
    }
}