package com.example.pokerhelper.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokerhelper.MainActivity


@Database(entities = [Game::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokerDataBase : RoomDatabase() {
    abstract fun getPokerDao(): PokerDao

    companion object {
        var database: PokerDataBase? = null

        fun getDbDao(): PokerDao {
            return if (database == null) {
                Room.databaseBuilder(MainActivity.context, PokerDataBase::class.java, "db")
                    .allowMainThreadQueries().build().getPokerDao()

            } else {
                database!!.getPokerDao()
            }
        }
    }

}