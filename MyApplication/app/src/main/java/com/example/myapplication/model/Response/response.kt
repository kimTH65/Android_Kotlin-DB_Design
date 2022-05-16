package com.example.myapplication.model.Response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "IncomeItem")
data class loadSumII(
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "price") val price: Int?
)

@Entity(tableName = "ExpenseItem")
data class loadSumEI(
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "price") val price: Int?
)

@Entity(tableName = "ExpenseItem")
data class loadSumCategoryEI(
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "price") val price: Int?,
    @ColumnInfo(name = "sub_name") val sub_name: String?,
    @ColumnInfo(name = "main_id") val main_id: Int?
)