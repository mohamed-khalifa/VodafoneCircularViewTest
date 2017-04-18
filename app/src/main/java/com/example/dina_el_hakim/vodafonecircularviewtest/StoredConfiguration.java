package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * This is a basic configuration holder, it provides a common pattern to standardize shared preferences access
 *
 * @author Matteo Giaccone
 */
public abstract class StoredConfiguration {

    static final String TAG = StoredConfiguration.class.getSimpleName();

    private Context mContext;
    private SharedPreferences mPrefs;

    protected StoredConfiguration(Context context, SharedPreferences prefs) {
        this.mContext = context;
        this.mPrefs = prefs;
        readConfiguration();
    }

    protected abstract void onReadConfiguration(SharedPreferences prefs);

    protected abstract void onSaveConfiguration(Editor editor);

    protected Context getContext() {
        return mContext;
    }

    public final void readConfiguration() {
        SharedPreferences prefs = getSharedPreferences();
        onReadConfiguration(prefs);

        // If this is the first time we load the preference save what we have as defaults
        if (prefs.getAll() == null || prefs.getAll().isEmpty()) {
            saveConfiguration();
        }
    }

    public void saveConfiguration() {
        Editor editor = getSharedPreferences().edit();
        onSaveConfiguration(editor);
        editor.apply();
    }

    public final void clearConfiguration() {
        getSharedPreferences()
                .edit()
                .clear()
                .commit();

        // Read the configuration again to reset variables
        readConfiguration();
    }

    protected SharedPreferences getSharedPreferences() {
        return mPrefs;
    }

}
