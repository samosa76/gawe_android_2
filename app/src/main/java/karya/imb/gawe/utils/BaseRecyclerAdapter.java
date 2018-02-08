package karya.imb.gawe.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by marcelsantoso.
 * <p/>
 * 7/12/16
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private Context context;
    private int cellLayout, numGrid = 1;
    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM = 1;

    public BaseRecyclerAdapter(Context context, List<?> items, int cellLayout) {
        this.context = context;
        this.items = (List<Object>) items;
        this.cellLayout = cellLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            final View view = LayoutInflater.from(context).inflate(headerLayout(), parent, false);
            return headerHolder(view);
        }

        final View view = LayoutInflater.from(context).inflate(cellLayout(), parent, false);
        return objectHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (showHeader() && position == 0)
            setHeaderView(holder);
        else
            setView(holder, position);
    }


    @Override
    public int getItemCount() {
        if (showHeader())
            return items.size() + 1;

        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (showHeader()) {
            return (position == 0) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
        }

        return VIEW_TYPE_ITEM;
    }

    public abstract RecyclerView.ViewHolder objectHolder(View v);

    public abstract RecyclerView.ViewHolder headerHolder(View v);

    public abstract void setView(RecyclerView.ViewHolder objectHolder, int position);

    public abstract void setHeaderView(RecyclerView.ViewHolder objectHolder);

    public abstract void itemSelected(int position);

    public int headerLayout() {
        return 0;
    }

    public boolean showHeader() {
        return false;
    }

    public int cellLayout() {
        return cellLayout;
    }

    public List<Object> getItems() {
        return items;
    }

    public BaseRecyclerAdapter setItems(List<Object> items) {
        this.items = items;
        return this;
    }

    public Object getItem(int position) {
        if (showHeader()) {
            return items.get(position - 1);
        } else {
            return items.get(position);

        }
    }

    public void removeItem(int position) {
        if (showHeader())
            items.remove(position - 1);
        else
            items.remove(position);

        notifyDataSetChanged();
    }

    public void clearItems() {
        items.clear();
    }

    public Context getContext() {
        return context;
    }

    public int getNumGrid() {
        return numGrid;
    }

    public BaseRecyclerAdapter setNumGrid(int numGrid) {
        this.numGrid = numGrid;
        return this;
    }
}
