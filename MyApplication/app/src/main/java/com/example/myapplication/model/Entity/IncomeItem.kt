package com.example.myapplication.model.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "IncomeItem")
data class IncomeItem(
    @PrimaryKey(autoGenerate = true) val income_item_id: Int,
    @ColumnInfo(name = "income_type") val income_type: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "datetime") val datetime: String
)
