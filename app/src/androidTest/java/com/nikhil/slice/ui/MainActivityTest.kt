package com.nikhil.slice.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nikhil.slice.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun test_isActivityDisplayed() {

        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.container))
            .check(matches(isDisplayed()))

        onView(withId(R.id.home_nav_host_fragment))
            .check(matches(isDisplayed()))

    }

}