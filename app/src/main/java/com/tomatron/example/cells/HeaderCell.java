package com.tomatron.example.cells;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tomatron.recyclercell.R;
import com.tomatron.recyclercell.RecyclerCell;

/**
 * Simple header containing a label demonstrating the simplest possible cell.
 */
public class HeaderCell extends RecyclerCell {

    private final String mLabel;

    public HeaderCell(String label) {
        mLabel = label;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new ViewHolder(inflater.inflate(R.layout.header_cell, parent, false));
    }

    @Override
    public void bindTo(RecyclerView.ViewHolder viewHolder) {
        ((ViewHolder)viewHolder).mTextView.setText(mLabel);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView;
        }
    }
}
