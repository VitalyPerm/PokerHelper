package com.example.pokerhelper.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    private  val STRING_LIST_SEPARATOR = "|"

    @TypeConverter
    fun fromStringsList(list: List<String>) =
        list.joinToString(STRING_LIST_SEPARATOR)

    @TypeConverter
    fun toStringsList(string: String) =
        string.split(STRING_LIST_SEPARATOR)

    @TypeConverter
    fun saveIntList(list: List<Int>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun getIntList(list: String): List<Int> {
        return Gson().fromJson(
            list,
            object : TypeToken<List<Int>>() {}.type
        )
    }
}