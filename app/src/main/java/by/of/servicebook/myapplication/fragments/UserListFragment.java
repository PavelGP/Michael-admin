package by.of.servicebook.myapplication.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.adapters.UsersAdapter;
import by.of.servicebook.myapplication.fragments.base.BaseListFragment;

public class UserListFragment extends BaseListFragment<ParseUser> {
    public static final String USER_EMAIL_REQ_KEY = "user_email_request_key";

    private String userEmailReq;
    private List<ParseUser> mResults;

    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userEmailReq = getArguments().getString(USER_EMAIL_REQ_KEY);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doRequest();
    }

    @Override
    protected ArrayAdapter<ParseUser> createAdapter(Context context, List<ParseUser> results) {
        return new UsersAdapter(context, R.layout.item_user, results);
    }

    @Override
    protected void onItemClick(ParseUser item) {

    }

    private void doRequest(){
        //do request to parce.com
        showProgressCancel();
        ParseQuery<ParseUser> query = ParseQuery.getQuery(ParseUser.class);
        query.whereContains("username", userEmailReq);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> results, ParseException e) {
                hideProgress();
                if (e == null){
                    adapter = createAdapter(getFragmentActivity(), results);
                    list.setAdapter(adapter);
                    Log.d("TAG", "success! find " + results.size() + " results");
                } else {
                    Log.d("TAG", "request failed!");
                }
            }
        });
    }

}
