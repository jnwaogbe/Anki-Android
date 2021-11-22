package com.ichi2.anki;

import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PreferencesTest {
    @Rule
    public ActivityScenarioRule<Preferences> activityScenarioRule =
            new ActivityScenarioRule<Preferences>(Preferences.class);


    @Test
    public void settingsContainersDisplayedOnLaunch() {
        onView(withId(R.id.settings_container));
    }

    @Test
    public void searchIconExistsInToolbar() {
        onView(withId(R.id.preferences_search));
    }

    @Test
    public void searchFragmentDisplayedOnClickOfSearchIcon() {
        onView(withId(R.id.preferences_search))
                .perform(click());

        onView(withId(R.id.prefs_search_recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void noItemsDisplayedInSearchResults() {

    }

    @Test
    public void atLeastOneItemDisplayedInSearchResults() {

    }

    @Test
    public void navigateToPreferencesOnClickOfSearchResults() {


    }




}


//
///**
// * Test class showcasing some {@link RecyclerViewActions} from Espresso.
// */
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class RecyclerViewSampleTest {
//
//    private static final int ITEM_BELOW_THE_FOLD = 40;
//
//    /**
//     * Use {@link ActivityScenario} to create and launch the activity under test. This is a
//     * replacement for {@link androidx.test.rule.ActivityTestRule}.
//     */
//    @Rule
//    public ActivityScenarioRule<MainActivity> activityScenarioRule =
//            new ActivityScenarioRule<MainActivity>(MainActivity.class);
//
//    @Test(expected = PerformException.class)
//    public void itemWithText_doesNotExist() {
//        // Attempt to scroll to an item that contains the special text.
//        onView(ViewMatchers.withId(R.id.recyclerView))
//                // scrollTo will fail the test if no item matches.
//                .perform(RecyclerViewActions.scrollTo(
//                        hasDescendant(withText("not in the list"))
//                ));
//    }
//
//    @Test
//    public void scrollToItemBelowFold_checkItsText() {
//        // First scroll to the position that needs to be matched and click on it.
//        onView(ViewMatchers.withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));
//
//        // Match the text in an item below the fold and check that it's displayed.
//        String itemElementText = getApplicationContext().getResources().getString(
//                R.string.item_element_text) + String.valueOf(ITEM_BELOW_THE_FOLD);
//        onView(withText(itemElementText)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void itemInMiddleOfList_hasSpecialText() {
//        // First, scroll to the view holder using the isInTheMiddle matcher.
//        onView(ViewMatchers.withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));
//
//        // Check that the item has the special text.
//        String middleElementText =
//                getApplicationContext().getResources().getString(R.string.middle);
//        onView(withText(middleElementText)).check(matches(isDisplayed()));
//    }
//
//    /**
//     * Matches the {@link CustomAdapter.ViewHolder}s in the middle of the list.
//     */
//    private static Matcher<CustomAdapter.ViewHolder> isInTheMiddle() {
//        return new TypeSafeMatcher<CustomAdapter.ViewHolder>() {
//            @Override
//            protected boolean matchesSafely(CustomAdapter.ViewHolder customHolder) {
//                return customHolder.getIsInTheMiddle();
//            }
//
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("item in the middle");
//            }
//        };
//    }
//}
