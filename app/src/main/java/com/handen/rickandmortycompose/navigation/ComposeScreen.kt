package com.handen.rickandmortycompose.navigation

import androidx.compose.runtime.Composable
import com.github.terrakok.cicerone.Screen

interface ComposeScreen: Screen {
    @Composable
    fun compose()
}