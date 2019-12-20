package com.example.winecellarsensor.view;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.winecellarsensor.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ContactTest extends MainTests{

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void contactTest() {
        goToMainWindow();

        ViewInteraction bottomNavigationItemView = onView(withId(R.id.nav_contact));
        bottomNavigationItemView.perform(click());

        ViewInteraction messageView = onView(withId(R.id.contactMessage));
        messageView.check(matches(isDisplayed()));

        ViewInteraction contactEmailView = onView(withId(R.id.contactEmail));
        contactEmailView.check(matches(isDisplayed()));

        ViewInteraction contactPhoneView = onView(withId(R.id.contactPhone));
        contactPhoneView.check(matches(isDisplayed()));

    }

}
