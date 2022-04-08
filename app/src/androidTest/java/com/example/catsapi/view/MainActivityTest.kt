package com.example.catsapi.view

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.catsapi.R
import com.example.catsapi.model.CatListApiItem
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
   // @get:Rule var activityTestScenerioRule = activityScenarioRule<MainActivity>()

    @get: Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java);

    val LIST_ITEM_IN_TEST = 4
    //val CATLIST_IN_TEST = [LIST_ITEM_IN_TEST]

    @Before
    fun setup(){

    }

    @After
    fun tearDown(){

    }
    @Test
    fun `clickSobreItemRecyclerViewQueAbreLaDetalActivity`(){
        onView(withId(R.id.rvlist)).perform(pressImeActionButton())
    }
}