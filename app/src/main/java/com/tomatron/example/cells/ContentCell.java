package com.tomatron.example.cells;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tomatron.recyclercell.R;
import com.tomatron.recyclercell.RecyclerCell;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Simple cell containing several view elements to bind, implemented with ButterKnife.
 * This cell has 2 possible states:
 *   1) As a grid tile, respecting the GridLayoutManager's grid spanning
 *
 *   2) As a full row, bypassing the grid's spanning
 */
public class ContentCell extends RecyclerCell {

    public interface ClickListener {
        void onClick(ContentCell cell);
    }

    private enum SpanType {
        GRID_TILE,
        ROW
    }

    private final SpanType mSpanType;
    private final ClickListener mClickListener;
    private final String mTitle;
    private final String mDescription;

    /**
     * Create a content cell that is a tile in the grid layout manager.
     */
    public static ContentCell createGridTile(ClickListener clickListener, String title, String description) {
        return new ContentCell(SpanType.GRID_TILE, clickListener, title, description);
    }

    /**
     * Create a content cell that spans an entire row.
     */
    public static ContentCell createRow(ClickListener clickListener, String title, String description) {
        return new ContentCell(SpanType.ROW, clickListener, title, description);
    }

    private ContentCell(SpanType spanType, ClickListener clickListener, String title, String description) {
        mSpanType = spanType;
        mClickListener = clickListener;
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new ViewHolder(inflater.inflate(R.layout.content_cell, parent, false));
    }

    @Override
    public void bindTo(RecyclerView.ViewHolder viewHolder) {
        ViewHolder holder = (ViewHolder)viewHolder;
        holder.bind(this);
    }

    /**
     * Overridden to allow this cell to be either a grid tile or full row based on the desired type
     * it was instantiated with.
     *
     * @param defaultSpan default span of the containing layout manager.
     * @return grid layout span based on its type.
     */
    @Override
    public int getSpanSize(int defaultSpan) {
        return mSpanType == SpanType.ROW ? defaultSpan : 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_cell_title)
        TextView mTitle;

        @BindView(R.id.content_cell_description)
        TextView mDescription;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final ContentCell cell) {
            mTitle.setText(cell.mTitle);
            mDescription.setText(cell.mDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cell.mClickListener.onClick(cell);
                }
            });
        }
    }
}
