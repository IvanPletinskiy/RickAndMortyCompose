package com.handen.characters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handen.characters.domain.entities.Character
import com.handen.characters.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _characters = MutableSharedFlow<List<Character>>()
    val characters: Flow<List<Character>> = _characters

    init {
        viewModelScope.launch {
            getCharactersUseCase().collect { _characters.emit(it) }
        }
    }
}