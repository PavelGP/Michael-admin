package by.of.servicebook.myapplication.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import by.of.servicebook.myapplication.R;


/**
 * Created by p.gulevich on 21.05.2015.
 */
public class ChangeTrackNameDialog extends NoticeDialogFragment {
    private static final String MESSAGE_TRACK_NAME = "message_track_name_key";

    private EditText etTrackName;

    public static DialogFragment newInstance(String messageTitle, String trackName,
                                             NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {


        BaseDialogFragment changeTrackNameDialog = new  ChangeTrackNameDialog();
        changeTrackNameDialog.setmListener(noticeDialogListener);

        Bundle args = new Bundle();
        args.putString(MESSAGE_TITLE, messageTitle);
        args.putString(MESSAGE_TRACK_NAME, trackName);
        changeTrackNameDialog.setArguments(args);

        return changeTrackNameDialog;
    }

    public String getTrackName(){
        return etTrackName.getText().toString();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String trackName = getArguments().getString(MESSAGE_TRACK_NAME);
        String messageTitle = getArguments().getString(MESSAGE_TITLE);
        String messageContent = (getArguments().getString(MESSAGE_CONTENT)== null)? "" : getArguments().getString(MESSAGE_CONTENT) ;

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_item_edit_text, null);
        etTrackName = (EditText) view.findViewById(R.id.etTrackName);

        etTrackName.setText(trackName);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(messageTitle);
        builder.setMessage(messageContent)
                .setView(view)
                .setPositiveButton(android.R.string.ok , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (mListener != null) mListener.onDialogPositiveClick(ChangeTrackNameDialog.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (mListener != null) mListener.onDialogNegativeClick(ChangeTrackNameDialog.this);
                    }
                });

        // Create the AlertDialog object and return it
        final AlertDialog dialog = builder.create();

        etTrackName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                    //This is the filter
                    if (event.getAction() != KeyEvent.ACTION_DOWN){
                        mListener.onDialogNegativeClick(ChangeTrackNameDialog.this);
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
