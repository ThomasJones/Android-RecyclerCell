package com.level11.adcommonlib.recycler

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

/**
 * Generic adapter for binding cells to the view.
 */
open class RecyclerCellAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected val cells = ArrayList<RecyclerCell>()
    private val viewHolderCreators = SparseArray<RecyclerCell>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return viewHolderCreators.get(viewType).createViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        cells[position].bindTo(holder)
    }

    override fun getItemCount(): Int {
        return cells.size
    }

    override fun getItemViewType(position: Int): Int {
        return cells[position].viewType
    }

    fun createSpanSizeLookUp(defaultSpan: Int): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return cells[position].getSpanSize(defaultSpan)
            }
        }
    }

    fun setCells(cells: List<RecyclerCell>) {
        this.cells.clear()
        this.cells.addAll(cells)

        viewHolderCreators.clear()
        for (cell in cells) {
            viewHolderCreators.put(cell.viewType, cell)
        }
    }
}
