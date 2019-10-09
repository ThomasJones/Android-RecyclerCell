package com.level11.adcommonlib.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Base 'cell' for populating to a recycler view in arbitrary manner. The cells removes the complexity
 * of different view types out of the adapter and into individual cells, making it easy to add more
 * view types to a recycler view without having to change the adapter.
 */
abstract class RecyclerCell {

    val viewType: Int
        get() = this.javaClass.hashCode()

    abstract fun createViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder

    abstract fun bindTo(viewHolder: RecyclerView.ViewHolder)

    fun getSpanSize(defaultSpan: Int): Int {
        return defaultSpan
    }
}