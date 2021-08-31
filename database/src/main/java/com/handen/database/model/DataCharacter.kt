package com.handen.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class DataCharacter(
    @PrimaryKey
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
    val imageUrl: String
)