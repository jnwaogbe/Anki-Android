package com.ichi2.anki;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import timber.log.Timber;

import static com.ichi2.anki.Preferences.HeaderFragment;
import static com.ichi2.anki.Preferences.GeneralSettingsFragment;
import static com.ichi2.anki.Preferences.ReviewingSettingsFragment;
import static com.ichi2.anki.Preferences.AppearanceSettingsFragment;
import static com.ichi2.anki.Preferences.GesturesSettingsFragment;
import static com.ichi2.anki.Preferences.ControlsSettingsFragment;
import static com.ichi2.anki.Preferences.AdvancedSettingsFragment;

public class PreferencesSearchConfiguration {
    private List<PreferencesSearchOptions> searchOptions = new ArrayList<>();

    public PreferencesSearchConfiguration(Context context) {
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


    public List<PreferencesSearchOptions> getSearchOptions() {
        return searchOptions;
    }


    private List<PreferencesSearchOptions> readPreferencesXML(PreferenceFragmentCompat fragment, int xmlResource, Context context) {
        List<PreferencesSearchOptions> options = new ArrayList<>();

        XmlResourceParser parser = context.getResources().getXml(xmlResource);

        try {
            int eventType = parser.getEventType();
            String screenTitle = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String namespace = "http://schemas.android.com/apk/res/android";
                        String searchString = getAttributeStringValue(parser, context, namespace, "title");
                        String key = getAttributeStringValue(parser, context, namespace, "key");
                        PreferencesSearchOptions option;

                        if (searchString != null) {
                            if (parser.getName() == "PreferenceScreen") {
                                option = new PreferencesSearchOptions(searchString, null, fragment);
                                screenTitle = searchString;
                            } else {
                                option = new PreferencesSearchOptions(searchString, screenTitle, fragment);
                            }

                            if (key != null) {
                                option.setPreferencesKey(key);
                                Timber.e("Add %s", key);
                            }

                            options.add(option);
                        }
                        break;
                    default:
                        Timber.e("Unexpected eventType = %s", eventType);
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return options;
    }


    private String getAttributeStringValue(AttributeSet attrs, Context context, String namespace,
                                           String name) {

        String value = null;
        int resId = attrs.getAttributeResourceValue(namespace, name, 0);

        if (resId == 0) {
            value = attrs.getAttributeValue(namespace, name);
        } else {
            value = context.getResources().getString(resId);
        }

        return value;
    }

}

