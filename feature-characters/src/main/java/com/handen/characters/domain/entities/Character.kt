package com.handen.characters.domain.entities

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val imageUrl: String
)
