package com.handen.characters.ui

import androidx.lifecycle.ViewModel
import com.handen.characters.domain.entities.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CharactersViewModel : ViewModel() {
    val characters: Flow<List<Character>> = flowOf(
        listOf(
            Character("Name", "Species", "Status", "Gender", ""),
            Character("Name", "Species", "Status", "Gender", ""),
            Character("Name", "Species", "Status", "Gender", "")
        )
    )
}