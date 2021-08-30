package com.handen.characters.ui

import androidx.lifecycle.ViewModel
import com.handen.characters.domain.entities.Character
import com.handen.characters.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    val characters: Flow<List<Character>> = getCharactersUseCase()
}