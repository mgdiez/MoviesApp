package com.polgomez.core.story

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.util.Log
import com.polgomez.movies.R

class StoryScreen(
    private val fragmentClass: Class<Fragment>,
    @param:IdRes @get:IdRes val containerId: Int = R.id.container
) {

    fun createScreenFragment(): Fragment? {
        var f: Fragment? = null
        try {
            f = fragmentClass.newInstance() as Fragment
        } catch (ignored: Exception) {
            Log.e(TAG, ignored.message)
        }

        return f
    }

    companion object {
        private const val TAG = "StoryScreen"
    }
}
