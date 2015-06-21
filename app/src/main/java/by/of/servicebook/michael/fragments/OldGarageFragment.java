package by.of.servicebook.michael.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import by.of.servicebook.michael.activities.MainActivity;
import by.of.servicebook.michael.R;
import by.of.servicebook.michael.utils.AppConst;


public class OldGarageFragment extends Fragment implements View.OnClickListener {
    private Activity activity;
    private CheckBox chb1, chb2;
    SharedPreferences sharedPreferences;

    public static OldGarageFragment newInstance() {
        OldGarageFragment fragment = new OldGarageFragment();

        return fragment;
    }

    public OldGarageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_garage, container, false);
        chb1 =(CheckBox) v.findViewById(R.id.chb1);
        chb2 =(CheckBox) v.findViewById(R.id.chb2);
        chb1.setOnClickListener(this);
        chb2.setOnClickListener(this);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int car = sharedPreferences.getInt(AppConst.VEHICLE, -1);
        if (car==-1){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(AppConst.VEHICLE, 1);
            editor.apply();
            car = 1;
        }

        if (car==1){
            chb1.setChecked(true);
            chb2.setChecked(false);
        } else {
            chb1.setChecked(false);
            chb2.setChecked(true);
        }

        LinearLayout ll_1 = (LinearLayout) v.findViewById(R.id.car1);
        LinearLayout ll_2 = (LinearLayout) v.findViewById(R.id.car2);

        ll_1.setOnClickListener(this);
        ll_2.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.chb1:
                if (chb1.isChecked()) {
                    chb1.setChecked(true);
                    chb2.setChecked(false);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(AppConst.VEHICLE, 1);
                    editor.apply();
                } else {
                    chb1.setChecked(true);
                }
                break;
            case R.id.chb2:
                if (chb2.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(AppConst.VEHICLE, 2);
                    editor.apply();
                    chb1.setChecked(false);
                    chb2.setChecked(true);
                } else {
                    chb2.setChecked(true);
                }
                break;
            case R.id.car1:
                break;
            case R.id.car2:
                break;

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_GARAGE);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
