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

                //var data = DB.dao().loadMonthSumSubCategoryEI("2022-05")
                var data = DB.dao().loadMonthSumMainCategoryEI("2022-05")
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
                var data = DB.dao().loadSubCategory()
                binding.text1.setText(data.toString())
            }.await()
        }
    }

    private fun testSet(){
        CoroutineScope(Dispatchers.Main).launch {
            val start = CoroutineScope(Dispatchers.IO).async {
                DB.dao().insertEI(ExpenseItem(0,1,"test1",10000, "2022-04-05"))
                DB.dao().insertEI(ExpenseItem(0,2,"test2",10000, "2022-05-17"))
                DB.dao().insertEI(ExpenseItem(0,3,"test3",10000, "2022-05-17"))
                DB.dao().insertEI(ExpenseItem(0,1,"test4",10000, "2022-05-18"))
                DB.dao().insertEI(ExpenseItem(0,2,"test5",10000, "2022-05-19"))
                DB.dao().insertEI(ExpenseItem(0,3,"test6",10000, "2022-05-20"))
                DB.dao().insertEI(ExpenseItem(0,1,"test7",10000, "2022-06-10"))
                DB.dao().insertEI(ExpenseItem(0,2,"test8",10000, "2022-07-03"))
                DB.dao().insertEI(ExpenseItem(0,3,"test9",10000, "2022-08-20"))
                DB.dao().insertEI(ExpenseItem(0,1,"test10",10000, "2022-09-17"))
                DB.dao().insertEI(ExpenseItem(0,2,"test11",10000, "2022-09-17"))
                DB.dao().insertEI(ExpenseItem(0,3,"test12",10000, "2022-09-10"))
                DB.dao().insertEI(ExpenseItem(0,4,"test13",10000, "2022-05-10"))

                DB.dao().insertSubCategory(SubCategory(0,1,"sub1","n"))
                DB.dao().insertSubCategory(SubCategory(0,2,"sub2","n"))
                DB.dao().insertSubCategory(SubCategory(0,3,"sub3","n"))
                DB.dao().insertSubCategory(SubCategory(0,5,"sub4","n"))

                DB.dao().insertMainCategory()

                var data = DB.dao().loadSubCategory()
                binding.text1.setText(data.toString())
            }.await()
        }
    }
}