package com.example.myapplication.model.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*


@Entity(tableName = "ExpenseItem")
data class ExpenseItem(
    @PrimaryKey(autoGenerate = true) val expense_item_id: Int,
    @ForeignKey(
        entity = SubCategory::class,
        parentColumns = ["sub_category_id"],
        childColumns = ["sub_category_id"],
        //onDelete = ForeignKey.CASCADE
    ) val  sub_category_id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "datetime") val datetime: String
)