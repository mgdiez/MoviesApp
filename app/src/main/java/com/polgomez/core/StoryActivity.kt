package com.polgomez.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.polgomez.core.story.UserStory
import com.polgomez.movies.R
import javax.inject.Inject

abstract class StoryActivity : AppCompatActivity() {

    @Inject
    lateinit var userStory: UserStory<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeInjections()

        super.onCreate(savedInstanceState)

        setContentView(getLayoutResource())

        if (savedInstanceState == null) userStory.start()
        else userStory.restoreState(savedInstanceState)
    }

    abstract fun initializeInjections()

    open fun getLayoutResource(): Int = R.layout.activity_container

    override fun onSaveInstanceState(outState: Bundle?) {
        userStory.storeState(outState)
        super.onSaveInstanceState(outState)
    }
}
