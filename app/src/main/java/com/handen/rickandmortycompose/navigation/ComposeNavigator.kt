package com.handen.rickandmortycompose.navigation

import com.github.terrakok.cicerone.Navigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ComposeNavigator: Navigator {
    val currentScreen: StateFlow<ComposeScreen>
}