# RecyclerCell

RecyclerCell is a very simple wrapper around Android's built-in RecyclerView system. It inverts the responsibility for view creation, binding, and grid spanning into individual cells, allowing the adapter to be very simple and allowing the addition of new cell types to require no change to the adapter.

  - Simple, reusable RecyclerView adapter
  - Small, isolated view cell classes
  - Arbitrary cell types with no change to the adapter
  - Cell types can support different spanning in a grid layout
  - Declarative cell creation
  - Respects the Single Responsibility and Open/Closed principles

## Motivation
Creating RecyclerViewAdapters is tedious and full of boiler-plate code. It can be difficult to add new view types and results in ever-growing adapters that need to keep changing as new view types are added. RecyclerView adapters also typically handle all the view holder management and binding manually. In short, naive implementations of RecyclerViewAdapters are awkward, tedious, difficult to test, increasingly complex, and have too many responsibilities. A typical example may look like:

```java
public class NaiveRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int VIEW_TYPE_HEADER = 0;
    static final int VIEW_TYPE_CONTENT_ITEM = 1;
    static final int VIEW_TYPE_DIVIDER = 2;
    static final int VIEW_TYPE_FOOTER= 3;
    ...

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER :
                return new HeaderViewHolder(....
            case VIEW_TYPE_CONTENT_ITEM :
                return new ContentItemViewHodler(...
            ...
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_HEADER :
                ((HeaderViewHolder)holder)...
                break;
            case VIEW_TYPE_CONTENT_ITEM :
                ((ContentItemViewHodler)holder)...
                break;
            ...
        }
    }
    ...
    class HeaderViewHolder extends RecyclerView.ViewHolder { ... }
    class ContentItemViewHodler extends RecyclerView.ViewHolder { ... }
    ...
}
```
The biggest problem with this is that adding more view types requires changing several methods in the adapter. It also typically involves adding a new inner-class view holder and in general just adding a lot more code to the adapter. Surely there's a better way...

## Solution
RecyclerCell inverts the responsibility for view creating and binding out of adapter and into individual cells. There are only two parts to this:
- RecyclerCell - abstract base class that your cells will inherit from
- RecyclerCellAdapter - a RecyclerViewAdapter implementation that acts on a list of RecyclerCells

Now the adapter has no knowledge of different view types - it delegates to cells. This makes it easy to add new cells that are supported by the existing adapter. Cells are responsible for the view creation and view holder and binding management just for iteself.

At a minimum, for a cell, you define a view holder and implement two methods:
```java
public class MyCell extends RecyclerCell {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {...}

    @Override
    public void bindTo(RecyclerView.ViewHolder viewHolder) {...}

    class MyViewHolder extends RecyclerView.ViewHolder {...}
}
```
It's very much like the code you'd see in a RecyclerViewAdapter, but the code is extracted out for each cell type, making the code simpler to reason about and simpler to extend, as opposed to an ever-growing adapter. Then you use the RecyclerCellAdapter, rather than creating your own adapter, in your Activity or Fragment. This adapter takes a list of cells. 

```java
...
public class MyActivity extends Activity {
    ...
    private RecyclerCellAdapter mRecyclerCellAdapter;
    ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ...
        mRecyclerCellAdapter = new RecyclerCellAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerCellAdapter);
        mRecyclerCellAdapter.setCells(createCells());
    }
    
    private List<RecyclerCell> createCells() {...}
    ...
```
All your RecyclerViews can use this same adapter, allowing you to focus on creating the specific cell implementations rather than more adapter boiler-plate!

## Grid Layout Support
The RecyclerCell and RecyclerCellAdapter provide a mechanism for allowing the cell to dictate the span within a grid layout. This makes it trivial to have cells that are a tile in a grid or span an entire row, all within the same RecyclerView.

```java
class MyCell extends RecyclerCell {
    private boolean mSpanEntireRow;
    ...
    @Override
    public int getSpanSize(int defaultSpan) {
        return mSpanEntireRow ? defaultSpan : 1;
    }
    ...
}
...
public class MyActivity extends Activity {
    ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ...
        mRecyclerCellAdapter = new RecyclerCellAdapter();
        int numColumns = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(this, numColumns);
        layoutManager.setSpanSizeLookup(mRecyclerCellAdapter.createSpanSizeLookUp(numColumns));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerCellAdapter);
        mRecyclerCellAdapter.setCells(createCells());
    }
    ...
```

## Example
The sample project is an example of the usage of RecyclerCells. 

Cell types in the example:
- Header
- Content Item (Can be grid tile or row)
- Divider
- Footer

View binding mechanisms in the example:
- ButterKnife
- Android Data-Binding

<img src="https://github.com/ThomasJones/Android-RecyclerCell/blob/readme/screenshots/LinearContent.png" width="300"/>
<img src="https://github.com/ThomasJones/Android-RecyclerCell/blob/readme/screenshots/GridContent.png" width="300"/>
<img src="https://github.com/ThomasJones/Android-RecyclerCell/blob/readme/screenshots/MixedContent.png" width="300"/>

## Declarative Cell Creation
A consequence of separating out the cells into their own classes is a more declarative way of defining the contents of a RecyclerView. Using similar cell types as the example above, you could create the cells like this:
```java
private List<RecyclerCell> createCells() {
        return Arrays.asList(
                new HeaderCell("Header"),
                new DividerCell(),
                new ContentCell("Content item 1"),
                new ContentCell("Content item 2"),
                new ContentCell("Content item 3"),
                new DividerCell(),
                new FooterCell("Footer label"));
    }
```
## Summary
RecyclerCells helps keeps your RecyclerViewAdapter boilerplate code to a minimum by offloading the view holding and binding mechanism to separate, distinct cell subclasses. This makes it much easier to add new view types to existing recycler views in your app.

Thanks for reading!
