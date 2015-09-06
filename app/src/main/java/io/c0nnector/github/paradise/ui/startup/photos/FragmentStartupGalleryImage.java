package io.c0nnector.github.paradise.ui.startup.photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f2prateek.dart.InjectExtra;

import io.c0nnector.github.paradise.api.model.Screenshot;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.bus.ScreenshotSelectedEvent;
import io.c0nnector.github.paradise.ui.BaseFragment;
import io.c0nnector.github.paradise.ui.views.UrlImageView;
import io.c0nnector.github.paradise.util.UtilApp;


public class FragmentStartupGalleryImage extends BaseFragment implements View.OnClickListener{

    public static final String TRANSITION_NAME_SCREENSHOT = "screenshotTransition";

    UrlImageView urlImageView;


    @InjectExtra
    Screenshot screenshot;

    @InjectExtra
    Integer position;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        urlImageView = new UrlImageView(getActivity());
        urlImageView.setOnClickListener(this);

        //shared transition name
        if (UtilApp.isLollipopAndGreater()) {
            urlImageView.setTransitionName(TRANSITION_NAME_SCREENSHOT + position);
        }

        return urlImageView;
    }



    @Override
    protected void afterViews() {
        super.afterViews();

        //load image
        urlImageView.loadGalleryImage(screenshot.getOriginal());

        //start shared transition
        if (UtilApp.isLollipopAndGreater()) {
            getActivity().startPostponedEnterTransition();
        }
    }



    /**
     * Instance
     * @param screenshot screenshot to show
     * @param position current adapter position
     * @return
     */
    public static FragmentStartupGalleryImage newInstance(Screenshot screenshot, int position) {

        Bundle args = new Bundle();
        args.putParcelable("screenshot", screenshot);
        args.putInt("position", position);

        FragmentStartupGalleryImage fragment = new FragmentStartupGalleryImage();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * Screenshot click
     * @param imgScreenshot
     */
    @Override
    public void onClick(View imgScreenshot) {

        //broadcast event
        Application.post(new ScreenshotSelectedEvent(position, screenshot, (ImageView) imgScreenshot));
    }
}
