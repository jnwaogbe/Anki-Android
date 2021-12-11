package com.ichi2.anki;

import androidx.preference.PreferenceFragmentCompat;

/**
    Preferences Search Options
 */
public class PreferencesSearchOptions {
    /**
     * Screen title
     */
    private String screenTitle;
    /**
     * Search string
     */
    private String searchString;
    /**
     * Preference Fragment
     */
    private PreferenceFragmentCompat fragment;
    /**
     * Preferences key
     */
    private String preferencesKey;


    /**
     *
     * @param searchString Preferences search suggestion string
     * @param screenTitle Name of settings screen associated with search string
     * @param fragment Preferences fragment associated with search string
     *
     */
    public PreferencesSearchOptions(final String searchString, final String screenTitle, final PreferenceFragmentCompat fragment) {
        this.screenTitle = screenTitle;
        this.fragment = fragment;
        this.searchString = searchString;
    }


    /**
     *
     * @param searchString Preferences search suggestion string
     * @param screenTitle Name of settings screen associated with search string
     * @param fragment Preferences fragment associated with search string
     * @param preferencesKey Preferences key associated with search string
     */
    public PreferencesSearchOptions(final String searchString, final String screenTitle, final PreferenceFragmentCompat fragment, final String preferencesKey) {
        this.screenTitle = screenTitle;
        this.fragment = fragment;
        this.searchString = searchString;
        this.preferencesKey = preferencesKey;
    }


    /**
     * Empty constructor for setting search string options programmatically
     */
    public PreferencesSearchOptions() {
        // Default constructor
    }

    public void setFragment(final PreferenceFragmentCompat fragment) {
        this.fragment = fragment;
    }

    public PreferenceFragmentCompat getFragment() {
        return fragment;
    }

    public void setSearchString(final String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setScreenTitle(final String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public String getScreenTitle() {
        return screenTitle;
    }


    public String getPreferencesKey() {
        return preferencesKey;
    }


    public void setPreferencesKey(final String preferencesKey) {
        this.preferencesKey = preferencesKey;
    }
}
