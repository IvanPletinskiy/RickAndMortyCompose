package com.handen.characters.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.handen.characters.R
import com.handen.characters.domain.entities.Character

@Composable
fun CharactersScreen(viewModel: CharactersViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val characters by viewModel.characters.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
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
        if (list.size == 1) {
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
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(stringResource(R.string.name), color = Color.Gray)
            Text(name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(stringResource(R.string.species), color = Color.Gray)
            Text(species)
            Spacer(modifier = Modifier.height(8.dp))
            Text(stringResource(R.string.status), color = Color.Gray)
            Text(status)
            Spacer(modifier = Modifier.height(8.dp))
            Text(stringResource(R.string.gender), color = Color.Gray)
            Text(gender)
        }
    }
}