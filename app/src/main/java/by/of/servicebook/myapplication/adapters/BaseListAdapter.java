package by.of.servicebook.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by p.gulevich on 22.06.2015.
 */
public abstract class BaseListAdapter<T> extends ArrayAdapter<T> {
    public static abstract class ViewHolder<T> {

        protected abstract void hold(T item);
    }

    protected abstract ViewHolder<T> createViewHolder(final View v);
    protected LayoutInflater inflater;
    protected int layoutId;
    protected Context context;

    public BaseListAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
        this(context, resource, textViewResourceId, new ArrayList<T>(Arrays.asList(objects)));
    }

    public BaseListAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, new ArrayList<T>(objects));
    }

    public BaseListAdapter(Context context, int layoutId, List<T> results) {
        super(context, layoutId, 0, results);
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        BaseListAdapter.ViewHolder<T> holder;
        if (view == null) {
            view = inflater.inflate(layoutId, null);
            holder = createViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (BaseListAdapter.ViewHolder<T>) view.getTag();
        }
        T item = getItem(position);
        holder.hold(item);
        return view;
    }
}
