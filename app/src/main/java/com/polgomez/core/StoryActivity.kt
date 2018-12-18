package com.polgomez.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.polgomez.core.story.StoryScreenContainer
import com.polgomez.core.story.StoryState
import com.polgomez.core.story.UserStory
import com.polgomez.movies.R

abstract class StoryActivity : AppCompatActivity(), StoryScreenContainer {

    lateinit var userStory: UserStory<StoryState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_container)

        if (savedInstanceState == null) userStory.start()
        else userStory.restoreState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        userStory.storeState(outState)
        super.onSaveInstanceState(outState)
    }
}
