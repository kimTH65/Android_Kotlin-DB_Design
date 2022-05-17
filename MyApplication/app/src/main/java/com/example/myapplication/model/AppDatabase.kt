package com.example.myapplication.model
import android.content.Context
import androidx.room.*
import com.example.myapplication.model.Entity.ExpenseItem
import com.example.myapplication.model.Entity.IncomeItem
import com.example.myapplication.model.Entity.MainCategory
import com.example.myapplication.model.Entity.SubCategory


@Database(
    entities = arrayOf(ExpenseItem::class,
                        IncomeItem::class,
                        MainCategory::class,
                        SubCategory::class),
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): DAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"
                    ).build()
                }
            }
            return instance
        }
    }
}