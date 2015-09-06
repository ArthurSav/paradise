package io.c0nnector.github.paradise.ui.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.util.IntentSocial;
import io.c0nnector.github.paradise.util.UtilTextView;

/**
 * Send feedback dialog
 */
public class FeedbackDialog {

    /**
     * Shows a dialog with startups(light)
     *
     * @param context
     * @return
     */
    public static AlertDialog show(final Context context){

        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_feeback, null);
        final EditText editText = (EditText) dialogView.findViewById(R.id.editText);

        //show dialog
        return  new AlertDialog.Builder(context)
                .setView(dialogView)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String message = UtilTextView.with(editText).text();

                        //open email chooser
                        IntentSocial.openEmail(context, message);
                    }
                })
                .show();
    }
}
