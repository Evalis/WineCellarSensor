package com.example.winecellarsensor.view;

import com.example.winecellarsensor.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> welcome =
            new ActivityTestRule<WelcomeActivity>(WelcomeActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.welcome)).check(matches(isDisplayed()));
    }
}