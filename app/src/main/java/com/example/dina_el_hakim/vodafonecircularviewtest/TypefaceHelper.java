package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class TypefaceHelper {

    public enum CustomFont {
        VODAFONE_REGULAR("fonts/vodafone-regular.ttf"),
        VODAFONE_BOLD("fonts/vodafone-bold.ttf");

        public final String fileName;

        private CustomFont(String name) {
            fileName = name;
        }
    }

    private static TypefaceHelper sInstance;

    private Map<CustomFont, Typeface> mTypefaceMap;

    public static TypefaceHelper get(Context context) {
        if (sInstance == null) {
            // Make sure we reference the application context here
            sInstance = new TypefaceHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private TypefaceHelper(Context context) {
        if (mTypefaceMap == null) {
            mTypefaceMap = new HashMap<CustomFont, Typeface>();
        }

        for (CustomFont font : CustomFont.values()) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), font.fileName);
            mTypefaceMap.put(font, typeface);
        }
    }

    public Typeface getTypeface(CustomFont font) {
        return mTypefaceMap.get(font);
    }

    public void applyTypeface(TextView tv, CustomFont font) {
        tv.setTypeface(getTypeface(font));
    }

    public static void setupTextViewWidget(TextView textView, AttributeSet attrSet, int[] attrs,
                                           int typefaceAttr, int dynamicTextAttr) {
        if (textView.isInEditMode()) {
            return;
        }

        Context context = textView.getContext();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrSet, attrs, 0, 0);

        try {
            int customTypeface = typedArray.getInt(typefaceAttr, TypefaceHelper.CustomFont.VODAFONE_REGULAR.ordinal());
            int dynamicTextId = typedArray.getResourceId(dynamicTextAttr, -1);

            get(context).applyTypeface(textView, CustomFont.values()[customTypeface]);

            if (!textView.isInEditMode() && dynamicTextId != -1) {
                textView.setText(DynamicText.textFromId(dynamicTextId));//DynamicText.textFromId(dynamicTextId)
            }
        } finally {
            typedArray.recycle();
        }
    }

}
