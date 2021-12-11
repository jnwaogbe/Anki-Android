package com.ichi2.anki;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.preference.PreferenceFragmentCompat;
import timber.log.Timber;

import com.ichi2.anki.Preferences.GeneralSettingsFragment;
import com.ichi2.anki.Preferences.ReviewingSettingsFragment;
import com.ichi2.anki.Preferences.AppearanceSettingsFragment;
import com.ichi2.anki.Preferences.GesturesSettingsFragment;
import com.ichi2.anki.Preferences.ControlsSettingsFragment;
import com.ichi2.anki.Preferences.AdvancedSettingsFragment;

/**
 * Preferences Search Configuration for generating search suggestions.
 */
// CS427 https://github.com/ankidroid/Anki-Android/issues/8342
public class PreferencesSearchConfiguration {
    private final List<PreferencesSearchOptions> searchOptions;
    private final static String PREFERENCE_SCREEN_TAG = "PreferenceScreen";

    public PreferencesSearchConfiguration(final Context context) {
        searchOptions = new ArrayList<>();
        searchOptions.addAll(
                readPreferencesXML(
                        new GeneralSettingsFragment(),
                        R.xml.preferences_general,
                        context
                ));

        searchOptions.addAll(
                readPreferencesXML(
                        new ReviewingSettingsFragment(),
                        R.xml.preferences_reviewing,
                        context
                ));

        searchOptions.addAll(
                readPreferencesXML(
                        new AppearanceSettingsFragment(),
                        R.xml.preferences_appearance,
                        context));

        searchOptions.addAll(
                readPreferencesXML(
                        new GesturesSettingsFragment(),
                        R.xml.preferences_gestures,
                        context));

        searchOptions.addAll(
                readPreferencesXML(
                        new ControlsSettingsFragment(),
                        R.xml.preferences_controls,
                        context));

        searchOptions.addAll(
                readPreferencesXML(
                        new AdvancedSettingsFragment(),
                        R.xml.preferences_controls,
                        context));
    }


    /**
     * Returns list of PreferenceSearchOptions to use for Preferences search functionality
     * @return PreferenceSearchOptions list
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public List<PreferencesSearchOptions> getSearchOptions() {
        return searchOptions;
    }


    private List<PreferencesSearchOptions> readPreferencesXML(final PreferenceFragmentCompat fragment, int xmlResource, final Context context) {
        final List<PreferencesSearchOptions> options = new ArrayList<>();
        final Resources resources = context.getResources();
        final XmlResourceParser parser = resources.getXml(xmlResource);

        try {
            int eventType = parser.getEventType();
            String screenTitle = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    final String namespace = "http://schemas.android.com/apk/res/android";
                    final String searchString = getAttributeStringValue(parser, context, namespace, "title");
                    final String key = getAttributeStringValue(parser, context, namespace, "key");
                    final PreferencesSearchOptions option = new PreferencesSearchOptions();

                    if (searchString != null) {
                        if (parser.getName().equals(PREFERENCE_SCREEN_TAG)) {
                            screenTitle = searchString;
                            option.setSearchString(searchString);
                            option.setFragment(fragment);
                        } else {
                            option.setScreenTitle(screenTitle);
                            option.setSearchString(searchString);
                            option.setFragment(fragment);
                        }

                        if (key != null) {
                            option.setPreferencesKey(key);
                        }

                        options.add(option);
                    }
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            Timber.e(e);
        } finally {
            parser.close();
        }
        return options;
    }


    private String getAttributeStringValue(final AttributeSet attrs, final Context context, final String namespace,
                                           final String name) {

        String value;

        final int resId = attrs.getAttributeResourceValue(namespace, name, 0);

        if (resId == 0) {
            value = attrs.getAttributeValue(namespace, name);
        } else {
            value = context.getResources().getString(resId);
        }

        return value;
    }

}

