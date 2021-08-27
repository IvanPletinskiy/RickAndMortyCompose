package com.handen.rickandmortycompose.navigation

import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.ActivityScreen
import kotlinx.coroutines.flow.MutableStateFlow

class ComposeNavigatorImpl constructor(
    private val defaultScreen: ComposeScreen,
    val finish: () -> Unit = {},
    val startNewActivity: (ActivityScreen) -> Unit = {}
) : ComposeNavigator {

    private val localStackCopy = mutableListOf<ComposeScreen>()

    override val currentScreen = MutableStateFlow(defaultScreen)

    override fun applyCommands(commands: Array<out Command>) {
        //copy stack before apply commands
        copyStackToLocal()

        for (command in commands) {
            try {
                applyCommand(command)
            } catch (e: RuntimeException) {
                errorOnApplyCommand(command, e)
            }
        }
    }

    private fun copyStackToLocal() {
        localStackCopy.clear()
        updateCurrentScreen()
    }

    private fun updateCurrentScreen() {
        currentScreen.value = localStackCopy.lastOrNull() ?: defaultScreen
    }

    /**
     * Perform transition described by the navigation command
     *
     * @param command the navigation command to apply
     */
    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> forward(command)
            is Replace -> replace(command)
            is BackTo -> backTo(command)
            is Back -> back()
        }
        updateCurrentScreen()
    }

    private fun forward(command: Forward) {
        when (val screen = command.screen) {
            is ActivityScreen -> {
                checkAndStartActivity(screen)
            }
            is ComposeScreen -> {
                localStackCopy.add(screen)
            }
        }
    }

    private fun replace(command: Replace) {
        when (val screen = command.screen) {
            is ActivityScreen -> {
                checkAndStartActivity(screen)
                finish()
            }
            is ComposeScreen -> {
                if (localStackCopy.isNotEmpty()) {
                    localStackCopy.removeAt(localStackCopy.lastIndex)
                } 
                localStackCopy.add(screen)
            }
        }
    }

    private fun back() {
        if (localStackCopy.isNotEmpty()) {
            localStackCopy.removeAt(localStackCopy.lastIndex)
        } else {
            activityBack()
        }
    }

    private fun activityBack() {
        finish()
    }

    /**
     * Performs [BackTo] command transition
     */
    private fun backTo(command: BackTo) {
        if (command.screen == null) {
            backToRoot()
        } else {
            val screen = command.screen ?: return
            val index = localStackCopy.indexOfFirst { it == screen }
            if (index != -1) {
                val forRemove = localStackCopy.subList(index, localStackCopy.size)
                forRemove.clear()
            } else {
                backToUnexisting(screen)
            }
        }
    }

    private fun backToRoot() {
        localStackCopy.clear()
    }

    private fun checkAndStartActivity(screen: ActivityScreen) {
        startNewActivity(screen)
    }

    /**
     * Called when we tried to fragmentBack to some specific screen (via [BackTo] command),
     * but didn't found it.
     *
     * @param screen screen
     */
    private fun backToUnexisting(screen: Screen) {
        backToRoot()
    }

    /**
     * Override this method if you want to handle apply command error.
     *
     * @param command command
     * @param error   error
     */
    private fun errorOnApplyCommand(
        command: Command,
        error: RuntimeException
    ) {
        throw error
    }
}