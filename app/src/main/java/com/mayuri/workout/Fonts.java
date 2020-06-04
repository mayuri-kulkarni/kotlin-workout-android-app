package com.mayuri.workout;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Created by danishmchowdhry on 05/02/18.
 */

public class Fonts {

    private static Typeface LATO_BOLD; //BOLD
    private static Typeface LATO_LIGHT; //MEDIUM
    private static Typeface LATO_NORMAL; //SEMI-BOLD

    public static void initFonts(Context context) {
        LATO_BOLD = getLatoBold(context);
        LATO_NORMAL = getLatoRegular(context);
        LATO_LIGHT = getLatoLight(context);
    }

    public static Typeface getLatoBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Bold.ttf");
    }

    public static Typeface getLatoLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Medium.ttf");
    }


    public static Typeface getLatoRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-SemiBold.ttf");
    }


    public static void setFonts(ViewGroup viewGroup) {
        try {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = viewGroup.getChildAt(i);

                if (view instanceof TextInputLayout) {
                    TextInputLayout v = (TextInputLayout) view;

                    if (v.getTypeface().getStyle() == Typeface.NORMAL)
                        v.setTypeface(LATO_NORMAL);
                    else if (v.getTypeface().getStyle() == Typeface.BOLD)
                        v.setTypeface(LATO_BOLD);
                    else
                        v.setTypeface(LATO_LIGHT);


                    EditText editText = ((TextInputLayout) view).getEditText();
                    if (editText.getTypeface().getStyle() == Typeface.NORMAL)
                        editText.setTypeface(LATO_NORMAL);
                    else if (editText.getTypeface().getStyle() == Typeface.BOLD)
                        editText.setTypeface(LATO_BOLD);
                    else
                        editText.setTypeface(LATO_LIGHT);



                } else if (view instanceof TextView) {
                    TextView v = (TextView) view;
                    if (v.getTypeface().getStyle() == Typeface.NORMAL)
                        v.setTypeface(LATO_NORMAL);
                    else if (v.getTypeface().getStyle() == Typeface.BOLD)
                        v.setTypeface(LATO_BOLD);
                    else
                        v.setTypeface(LATO_LIGHT);


                } else if (view instanceof EditText) {
                    EditText v = (EditText) view;
                    if (v.getTypeface().getStyle() == Typeface.NORMAL)
                        v.setTypeface(LATO_NORMAL);
                    else if (v.getTypeface().getStyle() == Typeface.BOLD)
                        v.setTypeface(LATO_BOLD);
                    else
                        v.setTypeface(LATO_LIGHT);

                } else if (view instanceof CheckBox) {
                    CheckBox v = (CheckBox) view;
                    if (v.getTypeface().getStyle() == Typeface.NORMAL)
                        v.setTypeface(LATO_NORMAL);
                    else if (v.getTypeface().getStyle() == Typeface.BOLD)
                        v.setTypeface(LATO_BOLD);
                    else
                        v.setTypeface(LATO_LIGHT);

                } else if (view instanceof ViewGroup) {
                    setFonts((ViewGroup) view);
                }
            }
        } catch (Exception e) {
        }
    }


}
