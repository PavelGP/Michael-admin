package by.of.servicebook.myapplication.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.activities.BaseActivity;
import by.of.servicebook.myapplication.activities.MainActivity;
import by.of.servicebook.myapplication.fragments.base.BaseFragment;
import by.of.servicebook.myapplication.utils.AppConst;

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

            if (email.isEmpty()){
                Toast.makeText(getActivity(), getString(R.string.check_email),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            //start ListFragment with results
            Bundle args = new Bundle();
            args.putString(UserListFragment.USER_EMAIL_REQ_KEY, email);
            activityCommunicator.startActivityWithFragment(UserListFragment.class.getName(), args, BaseActivity.class);

        }
    }
}
