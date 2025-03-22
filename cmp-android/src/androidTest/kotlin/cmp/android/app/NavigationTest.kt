/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package cmp.android.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun isFirstScreen_Home() {
        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
    }

    @Test
    fun verifyNotificationScreenFlow() {
        composeTestRule.apply {
            onNodeWithContentDescription("view notification").performClick()
            onNodeWithText("Notification Screen").assertIsDisplayed()
            onNodeWithContentDescription("Back").performClick()
            onNodeWithText("Home Screen").assertIsDisplayed()
        }
    }

    @Test
    fun verifySettingScreenFlow() {
        composeTestRule.apply {
            onNodeWithContentDescription("view settings").performClick()
            onNodeWithText("Settings").assertIsDisplayed()
            onNodeWithContentDescription("Back").performClick()
            onNodeWithText("Home Screen").assertIsDisplayed()
        }
    }

    @Test
    fun verifyProfileScreenFlow() {
        composeTestRule.apply {
            onNodeWithText("Profile").performClick()
            onNodeWithText("Profile Screen").assertIsDisplayed()
            Espresso.pressBack() // Should navigates to Home Screen
            onNodeWithText("Home Screen").assertIsDisplayed()
        }
    }

    @Test(expected = NoActivityResumedException::class)
    fun homeDestination_back_quitsApp() {
        composeTestRule.apply {
            onNodeWithText("Profile").performClick()
            onNodeWithText("Profile Screen").assertIsDisplayed()
            onNodeWithText("Home").performClick()
            Espresso.pressBack()
            // THEN the app quits
        }
    }
}
