package com.example.dreamorganizer

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.dreamorganizer.presentation.HomeFragment
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)


    @Test
    fun myTest(){
        onView(withId(R.id.bt_ui_test)).perform(click())
        onView(withId(R.id.tv_iu_test)).check(matches(isDisplayed()))
    }

}