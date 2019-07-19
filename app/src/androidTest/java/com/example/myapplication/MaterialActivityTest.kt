package com.example.myapplication

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MaterialActivityTest{
    @Rule
    @JvmField

    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)
    @Test
    fun guessWrong() {
        val secret = activityTestRule.activity.se.secret

        val resources = activityTestRule.activity.resources
        for (n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.input_num)).perform(clearText())
                onView(withId(R.id.input_num))
                    .perform(typeText(n.toString()))

                onView(withId(R.id.ok)).perform(click())
                val message = if (n < secret) resources.getString(R.string.big)
                else resources.getString((R.string.sma))

                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.ok))).perform(click())

            }
        }
    }



}