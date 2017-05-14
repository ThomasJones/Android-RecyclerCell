package com.tomatron.example.cells;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomatron.example.viewmodel.FooterViewModel;
import com.tomatron.recyclercell.R;
import com.tomatron.recyclercell.RecyclerCell;
import com.tomatron.recyclercell.databinding.FooterCellBinding;

/**
 * More complex cell type containing multiple fields implemented using Android Data Binding.
 * There are two ways a cell could be implemented with Data Binding:
 *
 *   1) The cell itself can act as the view model.
 *
 *   2) A separate view model could be provided to the cell and it simply binds the viewmodel to the
 *   cell.
 */
public class FooterCell extends RecyclerCell {

    private final FooterViewModel mViewModel;

    public FooterCell(FooterViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new ViewHolder(inflater.inflate(R.layout.footer_cell, parent, false));
    }

    @Override
    public void bindTo(RecyclerView.ViewHolder viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.bind(mViewModel);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        FooterCellBinding mBinding;

        ViewHolder(View itemView) {
            super(itemView);
            mBinding = FooterCellBinding.bind(itemView);
        }

        void bind(FooterViewModel cell) {
            mBinding.setCell(cell);
            mBinding.executePendingBindings();
        }
    }
}
