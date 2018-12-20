package com.polgomez.movies.story

import android.support.v4.app.FragmentManager
import com.polgomez.core.story.StoryScreen
import com.polgomez.core.story.StoryScreenContainer

class MoviesScreenContainer(private val fragmentManager: FragmentManager) : StoryScreenContainer {

    override fun addStoryScreen(storyScreen: StoryScreen) {
        with(fragmentManager.beginTransaction()) {
            storyScreen.createScreenFragment()?.let {
                add(storyScreen.containerId, it).commit()
            }
        }
    }

    override fun replaceStoryScreen(storyScreen: StoryScreen) {
        with(fragmentManager.beginTransaction()) {
            storyScreen.createScreenFragment()?.let {
                replace(storyScreen.containerId, it)
                    .addToBackStack(it.javaClass.simpleName)
                    .commit()
            }
        }
    }

    override fun previousScreen() {
        fragmentManager.popBackStackImmediate()
    }
}
