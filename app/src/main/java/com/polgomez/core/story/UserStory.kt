package com.polgomez.core.story

import android.os.Bundle

abstract class UserStory<S : StoryState>(val storyScreenContainer: StoryScreenContainer, var state: S) {

    abstract fun start()

    fun storeState(outState: Bundle?) {
        outState?.putParcelable(STORY_STATE_KEY, state)
    }

    fun restoreState(savedState: Bundle?) {
        savedState?.let {
            if (it.containsKey(STORY_STATE_KEY)) state = savedState.getParcelable(STORY_STATE_KEY)
        }
    }

    companion object {
        private const val STORY_STATE_KEY = "storyStateKey"
    }
}