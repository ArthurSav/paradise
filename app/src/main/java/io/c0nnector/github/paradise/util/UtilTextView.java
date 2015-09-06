package io.c0nnector.github.paradise.util;

import android.widget.TextView;

/**
 * Util class for TextView
 */
public class UtilTextView {

    TextView textView;

    public UtilTextView(TextView textView) {
        this.textView = textView;
    }

    public static UtilTextView with(TextView textView){
        return new UtilTextView(textView);
    }

    public TextView getTextView() {
        return textView;
    }


    /*****************************************************
     * ---------------- * Operators * --------------------
     *
     *
     *
     ****************************************************/

    public String text(){
        return getTextView().getText().toString();
    }

    public boolean hasText(){
        return text().length() > 0;
    }


    public void setEmpty(){
        getTextView().setText("");
    }

    /**
     * Used in validation cases.
     * If a bool operation is true, then we empty the text and proceed with some other operation
     *
     * e.g if(setEmpty(x>2)) {
     *     //do something
     * }
     *
     * @param setEmpty
     * @return
     */
    public boolean setEmpty(boolean setEmpty){

        if (setEmpty) setEmpty();

        return setEmpty;
    }

    /**
     * Will try to clear the textview text.
     * @return true when we clear non empty text
     */
    public boolean clear(){
        return setEmpty(hasText());
    }
}
