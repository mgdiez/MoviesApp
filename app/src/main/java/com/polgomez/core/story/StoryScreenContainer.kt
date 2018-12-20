package com.polgomez.core.story

interface StoryScreenContainer {

    fun addStoryScreen(storyScreen: StoryScreen)

    fun replaceStoryScreen(storyScreen: StoryScreen)

    fun previousScreen()
}