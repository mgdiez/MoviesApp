package com.polgomez.core.story

import android.os.Bundle

abstract class UserStory<S : StoryState>(val storyContainer: StoryContainer, var state: S) {

    fun storeState(outState: Bundle?) {
        outState?.putParcelable(STORY_STATE_KEY, state)
    }

    fun restoreState(savedState: Bundle?) {
        if (savedState != null && savedState.containsKey(STORY_STATE_KEY)) {
            state = savedState.getParcelable(STORY_STATE_KEY)
        }
    }

    companion object {

        private val STORY_STATE_KEY = "storyStateKey"
    }
}