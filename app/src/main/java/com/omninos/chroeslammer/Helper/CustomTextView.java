package com.omninos.chroeslammer.Helper;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by sandeep on 15-05-2017.
 */

public class CustomTextView extends TextView {


    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf"));
    }
}
