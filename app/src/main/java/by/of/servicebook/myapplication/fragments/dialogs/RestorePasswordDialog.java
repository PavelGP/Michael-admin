package by.of.servicebook.myapplication.fragments.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;

import com.byochannel.byo.Constants;

/**
 * Created by s.ankuda on 5/8/2015.
 */
public class RestorePasswordDialog extends NoticeDialogFragment implements Constants {

    public static DialogFragment newInstance(String messageTitle, String messageContent,
                                             NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {


        BaseDialogFragment restorePasswordDialog = new  RestorePasswordDialog();
        restorePasswordDialog.setmListener(noticeDialogListener);

        Bundle args = new Bundle();
        args.putString(MESSAGE_TITLE, messageTitle);
        args.putString(MESSAGE_CONTENT, messageContent);
        restorePasswordDialog.setArguments(args);

        return restorePasswordDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                    //This is the filter
                    if (event.getAction() != KeyEvent.ACTION_DOWN){
                        mListener.onDialogPositiveClick(RestorePasswordDialog.this);
                        dialog.dismiss();
                        return true;
                    }
                    else {
                        //Hide your keyboard here!!!!!!
                        return true; // pretend we've processed it
                    }
                } else
                    return false; // pass on to be processed as normal
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}


