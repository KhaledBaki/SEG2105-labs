package com.example.lab6;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest2 {
    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void loginIsInvalid() {
        onView(withId(R.id.edtUsername)).perform(typeText("user"));
        onView(withId(R.id.edtPassword)).perform(typeText("test"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.loginTxt))
                .check(matches(withText("Invalid login!")))
                .check(matches(isDisplayed()));
    }
}
