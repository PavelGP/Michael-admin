package by.of.servicebook.myapplication.fragments.dialogs;

import android.support.v4.app.DialogFragment;

/**
 * Created by s.ankuda on 5/4/2015.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    protected NoticeDialogListener mListener;

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(android.support.v4.app.DialogFragment dialog);
        public void onDialogNegativeClick(android.support.v4.app.DialogFragment dialog);
    }

    public interface CustomNoticeDialogListener extends NoticeDialogListener{
        public void onDialogNeutralClick(android.support.v4.app.DialogFragment dialog);
    }

    public void setmListener(NoticeDialogListener mListener) {
        this.mListener = mListener;
    }
}

