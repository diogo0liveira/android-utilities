package com.dao.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

internal interface AdapterBindView<T, V> : LifecycleObserver {

    var changedListener: OnAdapterDataChangedListener?

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unregisterObserver() {
        unregisterAdapterObserver()
        changedListener = null
    }

    fun registerAdapterObserver()

    fun unregisterAdapterObserver()

    fun onBindViewHolder(holder: V, item: T) {
    }

    fun onBindViewHolder(holder: V, item: T, position: Int) {
        onBindViewHolder(holder, item)
    }

    fun inflate(parent: ViewGroup, @LayoutRes layout: Int): View {
        return LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
    }

    fun setOnAdapterDataChangedListener(listener: OnAdapterDataChangedListener) {
        changedListener = listener
        registerAdapterObserver()
    }
}