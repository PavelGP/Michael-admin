package by.of.servicebook.myapplication.fragments.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.fragments.activitycommunicator.ActivityCommunicator;
import by.of.servicebook.myapplication.fragments.dialogs.NoticeDialogFragment;


public class BaseFragment extends Fragment {
    protected StringBuilder errorMsgBuilder;
    protected Context context;
    protected ActivityCommunicator activityCommunicator;
    protected ProgressDialog dialog;
    protected boolean isShowDialog = true;
    protected boolean inProgress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        context = inflater.getContext();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getFragmentActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ActivityCommunicator)
            activityCommunicator = (ActivityCommunicator) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityCommunicator = null;
    }

    protected void showDialogFragment(String title, String body, NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        DialogFragment newFragment = NoticeDialogFragment
                .newInstance(title, body, noticeDialogListener);
        newFragment.show(getActivity().getSupportFragmentManager(), null);
    }

    protected void setBackHomeAsEnabledTrue(){
        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    protected View findViewById(int idResourse) {
        return getActivity().findViewById(idResourse);
    }

    public FragmentActivity getFragmentActivity() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            return parentFragment.getActivity();
        }
        return getActivity();
    }

    protected void showProgressCancel() {

        if (!inProgress && isShowDialog) {
            inProgress = true;
            dialog = new ProgressDialog(getActivity());
            dialog.setProgressStyle(ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
            dialog.setMessage(getResources().getString(R.string.in_process));
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    protected void hideProgress(){
        inProgress = false;
        isShowDialog = true;
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
