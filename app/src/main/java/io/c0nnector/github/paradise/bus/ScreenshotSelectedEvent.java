package io.c0nnector.github.paradise.bus;

import android.widget.ImageView;

import io.c0nnector.github.paradise.api.model.Screenshot;


public class ScreenshotSelectedEvent {

    int position;

    Screenshot screenshot;

    ImageView imgScreenshot;

    public ScreenshotSelectedEvent(int position, Screenshot screenshot) {

        this.position = position;
        this.screenshot = screenshot;
    }

    public ScreenshotSelectedEvent(int position, Screenshot screenshot, ImageView imgScreenshot) {

        this.position = position;
        this.screenshot = screenshot;
        this.imgScreenshot = imgScreenshot;
    }

    public int getPosition() {

        return position;
    }

    public Screenshot getScreenshot() {

        return screenshot;
    }

    public ImageView getImgScreenshot() {

        return imgScreenshot;
    }
}
