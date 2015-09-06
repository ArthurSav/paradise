package io.c0nnector.github.paradise.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import io.c0nnector.github.paradise.R;

/**
 * Intents to launch some social apps
 */
public class IntentSocial {


    /**
     * Parses a string and tries to open the link
     *
     * @param context
     * @param url a string url e.g http://www.twitter.com
     */
    public static void openUrl(Context context, String url){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void openEmail(Context context, String message){

        Intent intentEmail = new Intent(Intent.ACTION_SEND);

        intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{context.getResources().getString(R.string.contact_email)});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "hi");
        intentEmail.putExtra(Intent.EXTRA_TEXT, message.isEmpty()?"Hey there!": message);
        intentEmail.setType("message/rfc822");

        context. startActivity(Intent.createChooser(intentEmail, "Choose an email provider :"));
    }
}
