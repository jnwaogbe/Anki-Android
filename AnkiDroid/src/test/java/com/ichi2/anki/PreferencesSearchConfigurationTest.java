package com.ichi2.anki;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PreferencesSearchConfigurationTest {
    @Test
    public void getSearchOptions() {
        PreferencesSearchConfiguration config = new PreferencesSearchConfiguration(getApplicationContext());
        List<PreferencesSearchOptions> options = config.getSearchOptions();

        List<Fragment> expectedFragments = Arrays.asList(
                new Preferences.GeneralSettingsFragment(),
                new Preferences.ReviewingSettingsFragment(),
                new Preferences.GesturesSettingsFragment(),
                new Preferences.ControlsSettingsFragment(),
                new Preferences.AdvancedSettingsFragment()
        );

        for (PreferencesSearchOptions option : options) {
            assertTrue(expectedFragments.contains(option.getFragment()));
            assertTrue(!option.getSearchString().isEmpty());
        }
    }

}
