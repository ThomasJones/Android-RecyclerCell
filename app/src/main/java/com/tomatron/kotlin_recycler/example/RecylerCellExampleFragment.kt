package com.level11.adcommonlib.recycler.example

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.level11.adcommonlib.R
import com.level11.adcommonlib.fragment.BaseRoutableFragment
import com.level11.adcommonlib.recycler.RecyclerCell
import com.level11.adcommonlib.recycler.RecyclerCellAdapter


class RecyclerCellExampleFragment : BaseRoutableFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = RecyclerView(activity)
        val adapter = RecyclerCellAdapter()
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.layoutManager = layoutManager
        view.adapter = adapter
        adapter.setCells(listOf(
            ExampleCell("Red Cell", Color.rgb(255, 0, 0)),
            ExampleCell("Green Cell", Color.rgb(0, 255, 0)),
            NestedRecyclerCell(listOf(
                NestedCell(Color.rgb(0, 0, 0)),
                NestedCell(Color.rgb(100, 0, 0)),
                NestedCell(Color.rgb(0, 100, 0)),
                NestedCell(Color.rgb(0, 0, 100)),
                NestedCell(Color.rgb(0, 100, 100)),
                NestedCell(Color.rgb(100, 100, 0)),
                NestedCell(Color.rgb(100, 0, 100))
            )),
            ExampleCell("Blue Cell", Color.rgb(0, 0, 155)),
            NestedRecyclerCell(listOf(
                NestedCell(Color.rgb(50, 50, 50)),
                NestedCell(Color.rgb(100, 50, 50)),
                NestedCell(Color.rgb(50, 100, 50)),
                NestedCell(Color.rgb(50, 50, 100))
            )),
            ExampleCell("Magenta Cell", Color.rgb(255, 0, 255)),
            ExampleCell("Black Cell", Color.rgb(0, 0, 0)),
            NestedRecyclerCell(listOf(
                NestedCell(Color.rgb(127, 127, 127)),
                NestedCell(Color.rgb(200, 127, 127)),
                NestedCell(Color.rgb(127, 200, 127)),
                NestedCell(Color.rgb(127, 127, 200))
            ))

        ))
        return view
    }

    inner class ExampleCell(private val label: String,
                            private val color: Int) : RecyclerCell() {
        override fun createViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder {
            return ExampleCellViewHolder(inflater.inflate(R.layout.recycler_example_cell, parent, false))
        }

        override fun bindTo(viewHolder: RecyclerView.ViewHolder) {
            viewHolder.itemView?.setBackgroundColor(color)
            (viewHolder as ExampleCellViewHolder).label.text = label
        }
    }

    class ExampleCellViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val label = itemView?.findViewById<View>(R.id.recycler_example_cell_label) as TextView
    }

    inner class NestedCell(private val color: Int) : RecyclerCell() {
        override fun createViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder {
            return NestedCellViewHolder(inflater.inflate(R.layout.recycler_nested_example_cell, parent, false))
        }

        override fun bindTo(viewHolder: RecyclerView.ViewHolder) {
            viewHolder.itemView?.setBackgroundColor(color)
        }
    }

    class NestedCellViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    inner class NestedRecyclerCell(private val cells: List<NestedCell>) : RecyclerCell() {
        override fun createViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder {
            return NestedRecyclerViewHolder(inflater.inflate(R.layout.nested_recycler_example_cell, parent, false))
        }

        override fun bindTo(viewHolder: RecyclerView.ViewHolder) {
            if (viewHolder is NestedRecyclerViewHolder) {
                viewHolder.adapter.setCells(cells)
                viewHolder.adapter.notifyDataSetChanged()
            }
        }
    }

    class NestedRecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val adapter = RecyclerCellAdapter()

        init {
            if (itemView is RecyclerView) {
                itemView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                itemView.adapter = adapter
            }
        }
    }
}