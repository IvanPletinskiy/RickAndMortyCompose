package com.handen.rickandmortycompose

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class RickAndMortyApplication:  Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
    }
}