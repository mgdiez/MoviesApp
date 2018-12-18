package com.polgomez.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.polgomez.core.story.StoryState
import com.polgomez.core.story.UserStory
import com.polgomez.movies.R
import javax.inject.Inject

abstract class StoryActivity : AppCompatActivity() {

    @Inject
    lateinit var userStory: UserStory<StoryState>

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeInjections()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_container)

        if (savedInstanceState == null) userStory.start()
        else userStory.restoreState(savedInstanceState)
    }

    abstract fun initializeInjections()

    override fun onSaveInstanceState(outState: Bundle?) {
        userStory.storeState(outState)
        super.onSaveInstanceState(outState)
    }
}
