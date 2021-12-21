package com.example.pokerhelper.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val players: List<String>,
    val deposit: Int = 0,
    val blind: Int
)