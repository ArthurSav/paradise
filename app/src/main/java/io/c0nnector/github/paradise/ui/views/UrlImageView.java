package io.c0nnector.github.paradise.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.util.UtilMeasure;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Loads url images with picasso
 */
public class UrlImageView extends ImageView {

    /**
     * Circle transformation
     */
    private CropCircleTransformation cropCircleTransformation;

    private ColorDrawable defaultBackground;


    @Inject
    Picasso picasso;



    public UrlImageView(Context context) {
        super(context);

        init();
    }

    public UrlImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public UrlImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    public void init(){

        if (isInEditMode()) return;


        //inject dagger
        Application.get(getContext()).inject(this);


        //default image background
        this.defaultBackground = new ColorDrawable(Color.LTGRAY);

        //circle image transformation
        this.cropCircleTransformation = new CropCircleTransformation();

        setAdjustViewBounds(true);
    }


    /**
     * Will show the image in a square view
     * @param url
     * @return
     */
    public UrlImageView loadGalleryImage(String url){

        int screenWidth = UtilMeasure.getScreenWidth(getContext());

        picasso.load(url)
                .resize(screenWidth, screenWidth)
                .placeholder(defaultBackground)
                .centerInside()
                .into(this);

        return this;
    }

    public void loadAvatarImage(String url){

        picasso.load(url)
                .transform(cropCircleTransformation)
                .placeholder(R.drawable.ic_account_circle_grey_500_48dp)
                .into(this);
    }

    public void loadCompanyLogo(String url){

        picasso.load(url)
                .placeholder(R.drawable.ic_business_grey_400_48dp)
                .transform(new RoundedCornersTransformation(UtilMeasure.dpToPx(5), 0))
                .into(this);
    }




    public Picasso getPicasso(){
        return picasso;
    }
}
