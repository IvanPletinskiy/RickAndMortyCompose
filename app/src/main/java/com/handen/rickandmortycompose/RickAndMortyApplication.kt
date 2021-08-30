package com.handen.rickandmortycompose

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RickAndMortyApplication:  Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
    }
}