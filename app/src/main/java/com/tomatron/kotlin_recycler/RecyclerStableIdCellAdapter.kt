package com.level11.adcommonlib.recycler

/**
 * Version of the recycler cell that exposes a lambda for an app-specified stable id.
 */
class RecyclerStableIdCellAdapter(private val getId: (cell: RecyclerCell) -> Long)
    : RecyclerCellAdapter() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getId(cells[position])
    }
}