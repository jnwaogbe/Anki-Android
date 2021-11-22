package com.ichi2.anki;

import android.Manifest;

import com.ichi2.anki.tests.InstrumentedTest;
import com.ichi2.anki.testutil.ThreadUtils;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.GrantPermissionRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ichi2.anki.TestUtils.clickChildViewWithId;
import static com.ichi2.anki.TestUtils.getActivityInstance;
import static com.ichi2.anki.TestUtils.isScreenSw600dp;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;



public class PreferencesTest {
    @Test
    public void TestPreferencesSearchRecyclerAdapter() {
        onView(withId(R.id.preferences_search)).perform(click());
        onView(withId(R.id.prefs_search_recycler_view)).check(matches(isDisplayed()));
    }
}
