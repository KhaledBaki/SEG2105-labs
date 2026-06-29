package com.example.lab6;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivityTestRule = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
    private LoginActivity loginActivity = null;

    private TextView text;


    @Before
    public void setUp() throws Exception {
        loginActivityTestRule.getScenario().onActivity(activity -> {
            loginActivity = activity;
        });
    }

    @Test
    @UiThreadTest
    public void checkFirstName() throws Exception {
        assertNotNull(loginActivity.findViewById(R.id.edtUsername));
        text = loginActivity.findViewById(R.id.edtUsername);
        text.setText("user1");
        String name = text.getText().toString();
        assertNotEquals("user", name);
    }
}