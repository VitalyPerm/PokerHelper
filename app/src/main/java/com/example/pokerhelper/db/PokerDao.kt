package com.example.pokerhelper.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokerDao {
    @Query("SELECT * FROM Game")
    fun getAll(): List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: Game)
}