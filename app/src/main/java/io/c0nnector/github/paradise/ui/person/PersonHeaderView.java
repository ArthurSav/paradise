package io.c0nnector.github.paradise.ui.person;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transitionseverywhere.TransitionManager;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.ui.views.BaseLinearLayout;
import io.c0nnector.github.paradise.ui.views.UrlImageView;
import io.c0nnector.github.paradise.util.Strings;
import io.c0nnector.github.paradise.util.UtilMeasure;
import io.c0nnector.github.paradise.util.UtilView;

/**
 * Part of the PersonView
 */
public class PersonHeaderView extends BaseLinearLayout {

    @Bind(R.id.imgAvatar)
    UrlImageView urlImageView;

    @Bind(R.id.txtBio)
    TextView txtBio;

    @Bind(R.id.txtUsername)
    TextView txtName;

    @Bind(R.id.txtLocation)
    TextView txtLocation;

    @Bind(R.id.txtLabel)
    TextView txtLabel;

    @Bind(R.id.vSecondaryInfo)
    View vSecondaryInfo;


    public PersonHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * User basic info
     *
     * @param avatarUrl user avatar url
     * @param name user name
     * @param bio user bio
     */
    public void setupBasicInfo(String avatarUrl, String name, String bio){

        TransitionManager.beginDelayedTransition(this);


        urlImageView.loadAvatarImage(avatarUrl);

        txtName.setText(name);

        if (UtilView.makeInvisible(txtBio, Strings.isNotBlank(bio))) txtBio.setText(bio);

        else adjustBioMargins();
    }

    /**
     * Secondary user info
     *
     * @param location a city name
     * @param label e.g investor, founder etc..
     */
    public void setupSecondaryInfo(String location, String label){

        TransitionManager.beginDelayedTransition(this);


        if (UtilView.show(txtLocation, Strings.isNotBlank(location))) txtLocation.setText(location);

        if (UtilView.show(txtLabel, Strings.isNotBlank(label))) txtLabel.setText(label);

        //show container view. Default state is hidden
        if (Strings.isNotBlank(location) || Strings.isNotBlank(label)) UtilView.show(vSecondaryInfo);
    }

    /**
     * When the user bio is empty we adjust the view margins for better view alignment
     */
    private void adjustBioMargins(){

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) txtBio.getLayoutParams();
        params.setMargins(0, UtilMeasure.dpToPx(10),0,0);
        txtBio.setLayoutParams(params);
    }
}
