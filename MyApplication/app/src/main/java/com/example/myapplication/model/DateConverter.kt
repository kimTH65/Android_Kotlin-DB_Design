package com.example.myapplication.model

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    //Long을 Date로
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    //Date을 Long로
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}