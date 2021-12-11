package com.ichi2.anki;

import androidx.preference.PreferenceFragmentCompat;

/**
    Preferences Search Options
 */
// CS427 https://github.com/ankidroid/Anki-Android/issues/8342
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


    /**
     * Sets PreferenceSearchOptions with given fragment
     * @param fragment
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public void setFragment(final PreferenceFragmentCompat fragment) {
        this.fragment = fragment;
    }


    /**
     * Gets PreferenceFragmentCompat fragment
     * @return
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public PreferenceFragmentCompat getFragment() {
        return fragment;
    }

    /**
     * Sets PreferenceSearchOptions with search string
     * @param searchString String to use in search suggestions
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public void setSearchString(final String searchString) {
        this.searchString = searchString;
    }

    /**
     * Gets search string
     * @return
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public String getSearchString() {
        return searchString;
    }


    /**
     * Sets screen title given string
     * @param screenTitle
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public void setScreenTitle(final String screenTitle) {
        this.screenTitle = screenTitle;
    }


    /**
     * Gets screen title
     * @return
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public String getScreenTitle() {
        return screenTitle;
    }


    /**
     * Sets preference key
     * @param preferencesKey
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public void setPreferencesKey(final String preferencesKey) {
        this.preferencesKey = preferencesKey;
    }

    /**
     * Gets preference key
     * @return
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public String getPreferencesKey() {
        return preferencesKey;
    }
}
