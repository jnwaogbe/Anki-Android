package com.ichi2.anki;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.appcompat.widget.SearchView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PreferencesTest {
    private static final ViewMatchers.Visibility VISIBLE = ViewMatchers.Visibility.VISIBLE;

    @Rule
    public ActivityScenarioRule<Preferences> activityScenarioRule =
            new ActivityScenarioRule<Preferences>(Preferences.class);

    @Test
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public void settingsContainersDisplayedOnLaunch() {
        onView(withId(R.id.settings_container));
    }

    @Test
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public void searchIconExistsInToolbar() {
        onView(withId(R.id.preferences_search));
    }

    @Test
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public void searchFragmentDisplayedOnClickOfSearchIcon() {
        onView(withId(R.id.preferences_search))
                .perform(click());
        onView(allOf(withId(R.id.preferences_search), withEffectiveVisibility(VISIBLE))).perform(typeSearchViewText("Test 1"));
    }


    /**
     * Types text into SearchView within the emulator
     * @param text text to type
     * @return ViewAction
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public static ViewAction typeSearchViewText(final String text){
        return new ViewAction(){
            @Override
            public Matcher<View> getConstraints() {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView.class));
            }

            @Override
            public String getDescription() {
                return "Change view text";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((SearchView) view).setQuery(text,false);
            }
        };
    }
}