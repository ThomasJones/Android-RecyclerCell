# RecyclerCell

RecyclerCell is a very simple wrapper around Android's built-in RecyclerView system. It inverts the responsibility for view creation, binding, and grid spanning into individual cells, allowing the adapter to be very simple and allowing the addition of new cell types to require no change to the adapter.

  - Simple, reusable RecyclerView adapter
  - Small, isolated view cell classes
  - Arbitrary cell types with no change to the adapter
  - Cell types can support different spanning in a grid layout
  - Respects the Single Responsibility and Open/Closed principles

# Problem
Creating RecyclerViewAdapters is tedious and full of boiler-plate code. It can be difficult to add new view types and results in ever-growing adapters that need to keep changing as new view types are added. RecyclerView adapters also typically handle all the view holder management and binding manually. In short, naive implementations of RecyclerViewAdapters are awkward and have too many responsibilities.

[Example Here]

# Solution
RecyclerCell inverts the responsibility for view creating and binding out of adapter and into individual cells. Now the adapter has no knowledge of different view types - it delegates to cells. This makes it easy to add new cells that are supported by the existing adapter. Cells are responsible for the view creation and view holder and binding management just for iteself.

# Example
[Screenshots here]

Cell types in the example:
- Header
- Content Item (Can be grid tile or row)
- Divider
- Footer

View binding mechanisms in the example:
- ButterKnife
- Android Data-Binding