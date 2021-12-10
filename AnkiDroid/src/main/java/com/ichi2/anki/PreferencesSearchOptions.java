package com.ichi2.anki;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import timber.log.Timber;

public class PreferencesSearchOptions {
    private String screenTitle;
    private String searchString;
    private PreferenceFragmentCompat fragment;
    private String preferencesKey;

    public PreferencesSearchOptions(String searchString, String screenTitle, PreferenceFragmentCompat fragment) {
        this.screenTitle = screenTitle;
        this.fragment = fragment;
        this.searchString = searchString;
    }

    public PreferencesSearchOptions(String searchString, String screenTitle, PreferenceFragmentCompat fragment, String preferencesKey) {
        this.screenTitle = screenTitle;
        this.fragment = fragment;
        this.searchString = searchString;
        this.preferencesKey = preferencesKey;
    }

    public PreferencesSearchOptions() {

    }

    public void setFragment(PreferenceFragmentCompat fragment) {
        this.fragment = fragment;
    }

    public PreferenceFragmentCompat getFragment() {
        return fragment;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public String getScreenTitle() {
        return screenTitle;
    }


    public String getPreferencesKey() {
        return preferencesKey;
    }


    public void setPreferencesKey(String preferencesKey) {
        this.preferencesKey = preferencesKey;
    }
}
