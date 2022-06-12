package com.example.dreamorganizer

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.dreamorganizer.adapter.DreamAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)


//    @Test
//    fun myTest(){
//        onView(withId(R.id.bt_ui_test)).perform(click())
//        onView(withId(R.id.tv_iu_test)).check(matches(isDisplayed()))
//    }


    @Test
    fun recyclerViewTest(){

        for (i in 0..11){
            onView(withId(R.id.bt_home_fragment_test_recyclerView)).perform(click())
        }

//        onView(withId(R.id.rv_home_dream_list)).perform(RecyclerViewActions.scrollTo<DreamAdapter.DreamViewHolder>(
//            ))

        onView(withId(R.id.rv_home_dream_list)).perform(RecyclerViewActions.scrollToPosition<DreamAdapter.DreamViewHolder>(11))
        onView(withText("Iphone 10")).check(matches(isDisplayed()))




    }

}