package com.tomatron.example.cells;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomatron.recyclercell.R;
import com.tomatron.recyclercell.RecyclerCell;

/**
 * Simple cell just for displaying a divider between other cells.
 */
public class DividerCell extends RecyclerCell {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new ViewHolder(inflater.inflate(R.layout.divider_cell, parent, false));
    }

    @Override
    public void bindTo(RecyclerView.ViewHolder viewHolder) {

    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
