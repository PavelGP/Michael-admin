package by.of.servicebook.myapplication.fragments.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import by.of.servicebook.myapplication.R;

public abstract class BaseListFragment <T> extends BaseFragment {
    protected abstract ArrayAdapter<T> createAdapter(Context context, List<T> results);
    protected abstract void onItemClick(T item);
    //protected abstract void requestedService(Context context); //add parameters

    protected ListView list;
    protected ArrayAdapter<T> adapter;

    protected ProgressBar progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        context = inflater.getContext();
        progress = (ProgressBar) view.findViewById(R.id.progress);
        closeProgress();
        list = (ListView) view.findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                T item = adapter.getItem(position);
                BaseListFragment.this.onItemClick(item);
            }
        });
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        return view;
    }

    protected void closeProgress() {
        if (progress!=null)
            progress.setVisibility(View.GONE);
    }

    protected void openProgress() {
        if (progress!=null)
            progress.setVisibility(View.VISIBLE);
    }

    protected void setEmptyView(List<T> results) {
        if (results == null || results.isEmpty()) {
            TextView emptyView = new TextView(context);
            emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            emptyView.setText(getTextForEmptyView());
            emptyView.setTextSize(20);
            emptyView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            ((ViewGroup) list.getParent()).addView(emptyView);
            closeProgress();
            list.setEmptyView(emptyView);
        } else {
//            list.getEmptyView().setVisibility(View.GONE);
        }
    }

    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    protected String getTextForEmptyView() {
        return getString(R.string.empty);
    }
}
