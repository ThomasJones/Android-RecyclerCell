package com.tomatron.recyclercell;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic adapter for binding cells to the view.
 */
public class RecyclerCellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region CLASS VARIABLES -----------------------------------------------------------------------

    private final List<RecyclerCell> mCells = new ArrayList<>();
    private final SparseArray<RecyclerCell> mViewHolderCreators = new SparseArray<>();

    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion

    //region LIFECYCLE METHODS ---------------------------------------------------------------------

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return mViewHolderCreators.get(viewType).createViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mCells.get(position).bindTo(holder);
    }

    @Override
    public int getItemCount() {
        return mCells.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mCells.get(position).getViewType();
    }

    public GridLayoutManager.SpanSizeLookup createSpanSizeLookUp(final int defaultSpan) {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mCells.get(position).getSpanSize(defaultSpan);
            }
        };
    }

    //endregion

    //region PUBLIC CLASS METHODS ------------------------------------------------------------------

    public void setCells(List<RecyclerCell> cells) {
        mCells.clear();
        mCells.addAll(cells);

        mViewHolderCreators.clear();
        for (RecyclerCell cell : cells) {
            mViewHolderCreators.put(cell.getViewType(), cell);
        }
    }

    //endregion

    //region PRIVATE METHODS -----------------------------------------------------------------------
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion

}
