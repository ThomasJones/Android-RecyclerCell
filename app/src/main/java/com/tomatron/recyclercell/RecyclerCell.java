package com.tomatron.recyclercell;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Base 'cell' for populating to a recycler view in arbitrary manner. The cells removes the complexity
 * of different view types out of the adapter and into individual cells, making it easy to add more
 * view types to a recycler view without having to change the adapter.
 */
public abstract class RecyclerCell {

    //region PUBLIC CLASS METHODS ------------------------------------------------------------------

    public final int getViewType() {
        return this.getClass().hashCode();
    }

    public abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater);

    public abstract void bindTo(RecyclerView.ViewHolder viewHolder);

    public int getSpanSize(int defaultSpan) {
        return defaultSpan;
    }

    //endregion
}