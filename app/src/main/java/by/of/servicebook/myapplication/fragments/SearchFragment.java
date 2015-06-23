package by.of.servicebook.myapplication.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.activities.MainActivity;
import by.of.servicebook.myapplication.fragments.base.BaseFragment;
import by.of.servicebook.myapplication.utils.AppConst;
import by.of.servicebook.myapplication.utils.AppUtils;

/**
 * Created by Pavel on 21.04.2015.
 */
public class SearchFragment extends BaseFragment {

    private Activity activity;
    private ViewHolder viewHolder;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();

        return fragment;
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_SEARCH);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        viewHolder = new ViewHolder(v);

        return v;
    }

    class ViewHolder{
        @InjectView(R.id.etEmailSearch)
        EditText etEmail;
        @InjectView(R.id.btnStartEmailSearch)
        Button btnEmailSearch;

        public ViewHolder(View v){
            ButterKnife.inject(this, v);
        }

        @OnClick(R.id.btnStartEmailSearch)
        public void startEmailSearch(Button btn){
            String email = etEmail.getText().toString();
            if (!AppUtils.isValidEmail(email)){
                Toast.makeText(getActivity(), getString(R.string.check_email),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            ParseQuery<ParseUser> query = ParseQuery.getQuery(ParseUser.class);
            query.whereContains("username", email);
            query.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> list, ParseException e) {
                    if (e == null){
                        Log.d("TAG", "success! find " + list.size() + " results");
                    } else {
                        Log.d("TAG", "request failed!");
                    }
                }
            });
        }
    }
}
