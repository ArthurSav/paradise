package io.c0nnector.github.paradise.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.Person;
import io.c0nnector.github.paradise.api.model.results.Startup;
import io.c0nnector.github.paradise.util.IntentSocial;
import io.c0nnector.github.paradise.util.Strings;
import io.c0nnector.github.paradise.util.UtilView;

/**
 * Social buttons view(twitter, linkedin etc...)
 */
public class SocialButtonsView extends BaseLinearLayout {


    @Bind(R.id.btnLinkedin)
    View btnLinkedin;

    @Bind(R.id.btnFacebook)
    View btnFacebook;

    @Bind(R.id.btnTwitter)
    View btnTwitter;




    public SocialButtonsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * Bind from startup object
     * @see Startup
     * @param startup
     */
    public void bind(Startup startup){
        bind(startup.getLinkedin_url(), startup.getTwitter_url(), startup.getFacebook_url());
    }

    /**
     * Bind from person object\
     * @see Person
     * @param person
     */
    public void bind(Person person){
        bind(person.getLinkedin_url(), person.getTwitter_url(), person.getFacebook_url());
    }

    /**
     * Hides an icon if the url is empty. Click action will open an intent
     *
     * @param linkedinUrl
     * @param twitterUrl
     * @param facebookUrl
     */
    private void bind(final String linkedinUrl, final String twitterUrl, final String facebookUrl){

        //no link available, hide the whole view
        if (Strings.isBlank(linkedinUrl) && Strings.isBlank(twitterUrl) && Strings.isBlank(facebookUrl)) UtilView.hide(this);

        else {

            //linkedin
            UtilView.show(btnLinkedin, Strings.isNotBlank(linkedinUrl));

            //twitter
            UtilView.show(btnTwitter, Strings.isNotBlank(twitterUrl));

            //facebook
            UtilView.show(btnFacebook, Strings.isNotBlank(facebookUrl));


            btnLinkedin.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    IntentSocial.openUrl(getContext(), linkedinUrl);
                }
            });

            btnTwitter.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    IntentSocial.openUrl(getContext(), twitterUrl);
                }
            });

            btnFacebook.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    IntentSocial.openUrl(getContext(), facebookUrl);
                }
            });
        }
    }

}
