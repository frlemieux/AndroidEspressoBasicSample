/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.espresso.BasicSample

import androidx.test.ext.junit.rules.activityScenarioRule
import android.app.Activity
import androidx.compose.runtime.withRunningRecomposer
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.lemieux.tasks.ui.theme.AppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * The kotlin equivalent to the ChangeTextBehaviorTest, that
 * showcases simple view matchers and actions like [ViewMatchers.withId],
 * [ViewActions.click] and [ViewActions.typeText], and ActivityScenarioRule
 *
 *
 * Note that there is no need to tell Espresso that a view is in a different [Activity].
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorKtTest {


    @get:Rule val composeTestRuleMainActivity = createAndroidComposeRule<MainActivity>()

    @Test
    fun changeText_sameActivity() {
        composeTestRuleMainActivity.onNodeWithTag("tag_text_field")
            .performTextInput(STRING_TO_BE_TYPED)
        composeTestRuleMainActivity.onNodeWithTag("tag_change_text")
            .performClick()
        composeTestRuleMainActivity.onNodeWithTag("tag_hello_world")
            .assertTextEquals(STRING_TO_BE_TYPED)
    }

    @Test
    fun changeText_newActivity() {
        // First, input text in the MainActivity
        composeTestRuleMainActivity.onNodeWithTag("tag_text_field")
            .performTextInput(STRING_TO_BE_TYPED)

        // Click to open the new activity
        composeTestRuleMainActivity.onNodeWithTag("tag_open_activity_and_change_text")
            .performClick()

        composeTestRuleMainActivity.onNodeWithTag("tag_text_screenB")
            .assertTextEquals(STRING_TO_BE_TYPED)
    }

    companion object {

        val STRING_TO_BE_TYPED = "Espresso"
    }
}