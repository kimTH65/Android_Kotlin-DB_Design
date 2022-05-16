package com.example.myapplication.model.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "SubCategory")
data class SubCategory(
    @PrimaryKey(autoGenerate = true) val sub_category_id: Int,
    @ForeignKey(
        entity = MainCategory::class,
        parentColumns = ["main_category_id"],
        childColumns = ["main_category_id"],
        //onDelete = ForeignKey.CASCADE
    ) val  main_category_id: Int,
    @ColumnInfo(name = "sub_category_name") val sub_category_name: String,
    @ColumnInfo(name = "deleted_yn", defaultValue = "n") val memo: Char
)
