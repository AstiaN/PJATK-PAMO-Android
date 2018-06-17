package com.example.astian.pjatk_pamo_project.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.astian.pjatk_pamo_project.R;

public class DeleteClientDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_client_dialog_message)
                .setPositiveButton(R.string.delete_confirmation, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {
                        getTargetFragment().onActivityResult(getTargetRequestCode(),
                                Activity.RESULT_OK, getActivity().getIntent());
                    }
                })
                .setNegativeButton(R.string.cancel, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getTargetFragment().onActivityResult(getTargetRequestCode(),
                                        Activity.RESULT_CANCELED, getActivity().getIntent());
                            }
                        });

        return builder.create();
    }
}
