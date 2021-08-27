package com.handen.characters.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.handen.characters.domain.entities.Character

@Composable
fun CharactersScreen(viewModel: CharactersViewModel = viewModel()) {
    val characters by viewModel.characters.collectAsState(initial = emptyList())
    Column {
        characters.chunked(2).forEach {
            CharactersRow(it)
        }
    }
}

@Composable
fun CharactersRow(list: List<Character>) {
    Row {
        list.forEach {
            CharacterCard(
                Modifier.weight(1f),
                name = it.name,
                species = it.species,
                status = it.status,
                gender = it.gender,
                imageUrl = it.imageUrl
            )
        }
        if(list.size == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview("Characters preview")
@Composable
fun DefaultPreview() {
    CharactersScreen()
}

@Composable
fun CharacterCard(
    modifier: Modifier,
    name: String,
    species: String,
    status: String,
    gender: String,
    imageUrl: String
) {
    Card(modifier) {
        Column(Modifier.padding(16.dp)) {
            Text(name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(species)
            Spacer(modifier = Modifier.height(8.dp))
            Text(status)
            Spacer(modifier = Modifier.height(8.dp))
            Text(gender)
        }
    }
}