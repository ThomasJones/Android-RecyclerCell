package com.tomatron.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tomatron.example.cells.ContentCell;
import com.tomatron.example.cells.DividerCell;
import com.tomatron.example.cells.FooterCell;
import com.tomatron.example.cells.HeaderCell;
import com.tomatron.example.viewmodel.FooterViewModel;
import com.tomatron.recyclercell.R;
import com.tomatron.recyclercell.RecyclerCell;
import com.tomatron.recyclercell.RecyclerCellAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Example activity showing the creation of recycler cells and binding to the view holder through
 * the adapter.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerCellAdapter mRecyclerCellAdapter;
    private ContentCell.ClickListener mContentClickListener = new ContentCellListener();
    private FooterViewModel.ButtonListener mButtonListener = new FooterButtonListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        mRecyclerCellAdapter = new RecyclerCellAdapter();
        int spanCount = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        layoutManager.setSpanSizeLookup(mRecyclerCellAdapter.createSpanSizeLookUp(spanCount));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerCellAdapter);
        mRecyclerCellAdapter.setCells(createCells());
    }

    private List<RecyclerCell> createCells() {
        final String rowDescription = "Full row content item";
        final String tileDescription = "Grid tile content";

        return Arrays.asList(
                // Section 1 - Linear content
                new HeaderCell("Linear Content"),
                ContentCell.createRow(mContentClickListener, "Row 1", rowDescription),
                ContentCell.createRow(mContentClickListener, "Row 2", rowDescription),
                ContentCell.createRow(mContentClickListener, "Row 3", rowDescription),
                new FooterCell(new FooterViewModel(mButtonListener, "Linear Section Footer",
                        "This section showed content cells that span the whole view.")),
                new DividerCell(),

                // Section 2 - Grid content
                new HeaderCell("Grid Content"),
                ContentCell.createGridTile(mContentClickListener, "Grid 1", tileDescription),
                ContentCell.createGridTile(mContentClickListener, "Grid 2", tileDescription),
                ContentCell.createGridTile(mContentClickListener, "Grid 3", tileDescription),
                ContentCell.createGridTile(mContentClickListener, "Grid 4", tileDescription),
                ContentCell.createGridTile(mContentClickListener, "Grid 5", tileDescription),
                new FooterCell(new FooterViewModel(mButtonListener, "Grid Section Footer",
                        "This section showed content cells that are in a grid.")),
                new DividerCell(),

                // Section 3 - Mixed grid and linear
                new HeaderCell("Mixed Grid and Linear Content"),
                ContentCell.createGridTile(mContentClickListener, "Mixed 1", tileDescription),
                ContentCell.createGridTile(mContentClickListener, "Mixed 2", tileDescription),
                ContentCell.createRow(mContentClickListener, "Mixed 3", rowDescription),
                ContentCell.createRow(mContentClickListener, "Mixed 4", rowDescription),
                ContentCell.createGridTile(mContentClickListener, "Mixed 5", tileDescription),
                ContentCell.createGridTile(mContentClickListener, "Mixed 6", tileDescription),
                new FooterCell(new FooterViewModel(mButtonListener, "Mixed Section Footer",
                        "This section showed content cells that were a mix of grid tile and full row."))
        );
    }

    /**
     * Handles the footer's click events.
     */
    private class FooterButtonListener implements FooterViewModel.ButtonListener {
        @Override
        public void onButtonPressed(FooterViewModel viewModel) {
            Toast.makeText(MainActivity.this,
                    "Pressed button in footer : " + viewModel.getTitle(),
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Handle the clicking of a content cell.
     */
    private class ContentCellListener implements ContentCell.ClickListener {
        @Override
        public void onClick(ContentCell cell) {
            Toast.makeText(MainActivity.this,
                    "Pressed content item : " + cell.getTitle(),
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
