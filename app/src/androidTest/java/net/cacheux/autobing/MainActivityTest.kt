package net.cacheux.autobing

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.cacheux.autobing.MainActivity.Companion.MAX_SEARCHES
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testAllowedValues() {
        onView(withId(R.id.countText)).perform(replaceText("10"))
        onView(withId(R.id.launchButton)).check(matches(isEnabled()))

        onView(withId(R.id.countText)).perform(replaceText("0"))
        onView(withId(R.id.launchButton)).check(matches(isNotEnabled()))

        onView(withId(R.id.countText)).perform(replaceText((MAX_SEARCHES + 1).toString()))
        onView(withId(R.id.launchButton)).check(matches(isNotEnabled()))
    }

    private fun isNotEnabled(): Matcher<View> = IsNotEnabledMatcher()

    internal class IsNotEnabledMatcher : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("is not enabled")
        }

        public override fun matchesSafely(view: View): Boolean {
            return !view.isEnabled
        }
    }
}
