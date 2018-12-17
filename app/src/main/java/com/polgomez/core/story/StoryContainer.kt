package com.polgomez.core.story

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager

interface StoryContainer {

    @IdRes
    fun getContainerId(): Int

    fun getStoryFragmentManager(): FragmentManager
}
