package com.example.winecellarsensor.view;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.winecellarsensor.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WarningsListTest extends MainTests{

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void warningsListTest() {
        goToMainWindow();

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.nav_notifications), withContentDescription("Warnings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_warning),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.container),
                        childAtPosition(
                                allOf(withId(R.id.rv_warning),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                0)),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));


        try
        {
            ViewInteraction textView = onView(
                    allOf(withId(R.id.warning_room_name), withText("Basement"),
                            childAtPosition(
                                    allOf(withId(R.id.container),
                                            childAtPosition(
                                                    withId(R.id.rv_warning),
                                                    0)),
                                    0),
                            isDisplayed()));
            textView.check(matches(withText("Basement")));
        } catch (AmbiguousViewMatcherException e)
        {
            Log.i(TAG, "warningsListTest: There are ambiguous view. Meaning more than 1 items in recycler view has same view");
        }

        try
        {
            ViewInteraction textView2 = onView(
                    allOf(withId(R.id.date_time_tv), withText("Date and time:"),
                            childAtPosition(
                                    childAtPosition(
                                            IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                            0),
                                    0),
                            isDisplayed()));
            textView2.check(matches(withText("Date and time:")));
        } catch (AmbiguousViewMatcherException e)
        {
            Log.i(TAG, "warningsListTest: There are ambiguous view. Meaning more than 1 items in recycler view has same view");
        }

        try
        {
            ViewInteraction textView3 = onView(
                    allOf(withId(R.id.sensor_id_tv), withText("Sensor id:"),
                            childAtPosition(
                                    childAtPosition(
                                            IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                            1),
                                    0),
                            isDisplayed()));
            textView3.check(matches(withText("Sensor id:")));
        } catch (AmbiguousViewMatcherException e)
        {
            Log.i(TAG, "warningsListTest: There are ambiguous view. Meaning more than 1 items in recycler view has same view");
        }

        try
        {
            ViewInteraction textView4 = onView(
                    allOf(withId(R.id.value_tv), withText("Value:"),
                            childAtPosition(
                                    childAtPosition(
                                            IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                            2),
                                    0),
                            isDisplayed()));
            textView4.check(matches(withText("Value:")));
        } catch (AmbiguousViewMatcherException e)
        {
            Log.i(TAG, "warningsListTest: There are ambiguous view. Meaning more than 1 items in recycler view has same view");
        }

        try
        {
            ViewInteraction textView5 = onView(
                    allOf(withId(R.id.min_value_tv), withText("Min value:"),
                            childAtPosition(
                                    childAtPosition(
                                            IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                            3),
                                    0),
                            isDisplayed()));
            textView5.check(matches(withText("Min value:")));
        } catch (AmbiguousViewMatcherException e)
        {
            Log.i(TAG, "warningsListTest: There are ambiguous view. Meaning more than 1 items in recycler view has same view");
        }

        try
        {
            ViewInteraction textView6 = onView(
                    allOf(withId(R.id.max_value_tv), withText("Max value:"),
                            childAtPosition(
                                    childAtPosition(
                                            IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                            4),
                                    0),
                            isDisplayed()));
            textView6.check(matches(withText("Max value:")));
        } catch (AmbiguousViewMatcherException e)
        {
            Log.i(TAG, "warningsListTest: There are ambiguous view. Meaning more than 1 items in recycler view has same view");
        }
    }
}
