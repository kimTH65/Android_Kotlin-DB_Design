package com.example.myapplication

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.AppDatabase
import com.example.myapplication.model.Entity.ExpenseItem
import com.example.myapplication.model.Entity.SubCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private lateinit var DB : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DB = AppDatabase.getInstance(this)!!
        test()


    }
    private fun test(){
        CoroutineScope(Dispatchers.Main).launch {

            val start = CoroutineScope(Dispatchers.IO).async {
                DB.dao().insertEI(ExpenseItem(0,2,"test3",140000, "2022-12-14"))
                //var data = DB.dao().loadWeekSumEI("2022")
                var data = DB.dao().loadMonthSumSubCategoryEI("2022-12")
                binding.text1.setText(data.toString())
            }.await()

        }
    }

    private fun testMain(){
        CoroutineScope(Dispatchers.Main).launch {

            val start = CoroutineScope(Dispatchers.IO).async {
                DB.dao().insertMainCategory()
                var data = DB.dao().loadMainCategory("f")
                binding.text1.setText(data.toString())
            }.await()

        }
    }

    private fun testSub(){
        CoroutineScope(Dispatchers.Main).launch {

            val start = CoroutineScope(Dispatchers.IO).async {
                DB.dao().insertSubCategory(SubCategory(0,2,"sub2",'n'))
                var data = DB.dao().loadSubCategory()
                binding.text1.setText(data.toString())
            }.await()

        }
    }
}