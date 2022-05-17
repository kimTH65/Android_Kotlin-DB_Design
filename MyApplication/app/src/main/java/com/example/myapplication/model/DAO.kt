package com.example.myapplication.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.Entity.ExpenseItem
import com.example.myapplication.model.Entity.IncomeItem
import com.example.myapplication.model.Entity.MainCategory
import com.example.myapplication.model.Entity.SubCategory
import com.example.myapplication.model.Response.*

@Dao
interface DAO {
    //Main Category - expense_type에 따라서 Main Category Name 불러오기
    @Query("SELECT * from `MainCategory` " +
            "WHERE expense_type LIKE :expense_type||'%'"  +
            "ORDER BY main_category_id ASC;")
    fun loadMainCategory(expense_type : String) : List<MainCategory>

    //Main Category - 데이터 추가
    @Query("INSERT OR IGNORE INTO `MainCategory`(main_category_id,main_category_name,expense_type) VALUES" +
            "(1,'공과금','fix')," +
            "(2,'생활','fix')," +
            "(3,'기타','fix')," +
            "(4,'식비','flex')," +
            "(5,'생활','flex')," +
            "(6,'여가','flex')," +
            "(7,'문화','flex')," +
            "(8,'자기계발','flex')," +
            "(9,'기타','flex');"
    )fun insertMainCategory()

    //데이터 전체 삭제
    @Query("DELETE FROM `MainCategory`")
    fun deleteAllMainCategory()

    //--------------------------------------------

    //Sub Category - 데이터 불러오기
    @Query("SELECT * FROM SubCategory " +
            "WHERE deleted_yn = 'n'" +
            "ORDER BY sub_category_id ASC;")
    fun loadSubCategory() : List<SubCategory>

    //Sub Category - 데이터 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubCategory(entity: SubCategory)

    //Sub Category- 데이터 업데이트
    @Update
    fun updateSubCategory(entity: SubCategory)

    //Sub Category - 데이터 삭제 (Soft Delete)
    @Query("UPDATE SubCategory " +
            "SET deleted_yn ='y' " +
            "WHERE sub_category_id = :select")
    fun deleteSubCategory(select: Int);

    //데이터 전체 삭제
    @Query("DELETE FROM `SubCategory`")
    fun deleteAllSubCategory()

    //--------------------------------------------

    //IncomeItem - 전체 데이터 불러오기
    @Query("SELECT * FROM IncomeItem " +
            "ORDER BY income_item_id ASC;")
    fun loadIIt() : List<IncomeItem>

    //IncomeItem - 특정 데이터 불러오기
    @Query("SELECT * FROM IncomeItem " +
            "WHERE datetime Like :select||'%' " +
            "ORDER BY income_item_id ASC;")
    fun loadII(select:String) : List<IncomeItem>

    //IncomeItem - Month 기준 합계 데이터 불러오기
    @Query("SELECT substr(datetime,1,7) AS date, SUM(price) AS price " +
            "FROM IncomeItem " +
            "WHERE substr(datetime,1,7) Like :select||'%' " +
            "GROUP BY substr(datetime,1,7) " +
            "ORDER BY income_item_id ASC;")
    fun loadMonthSumII(select:String ) : List<loadSumII>

    //IncomeItem - Day 기준 합계 데이터 불러오기
    @Query("SELECT datetime AS date, SUM(price) AS price " +
            "FROM IncomeItem " +
            "WHERE datetime Like :select||'%' " +
            "GROUP BY datetime " +
            "ORDER BY income_item_id ASC;")
    fun loadDaySumII(select:String ) : List<loadSumII>

    //IncomeItem - Week 기준 합계 데이터 불러오기
    @Query("SELECT strftime('%W',datetime) AS date, SUM(price) AS price " +
            "FROM IncomeItem WHERE datetime Like :select||'%' " +
            "GROUP BY strftime('%W',datetime)" +
            "ORDER BY strftime('%W',datetime) ASC;")
    fun loadWeekSumII(select:String ) : List<loadSumII>

    //IncomeItem - 데이터 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertII(entity: IncomeItem)

    //IncomeItem- 데이터 업데이트
    @Update
    fun updateII(entity: IncomeItem)

    //IncomeItem - 데이터 삭제
    @Delete
    fun deleteII(entity: IncomeItem);

    //--------------------------------------------

    //Expense Item - 데이터 불러오기
    @Query("SELECT * from ExpenseItem ORDER BY expense_item_id ASC;")
    fun loadEI() : List<ExpenseItem>

    //Expense Item - 특정 데이터 불러오기
    @Query("SELECT * " +
            "FROM ExpenseItem AS E " +
            "INNER JOIN SubCategory AS S " +
            "ON E.sub_category_id = S.sub_category_id " +
                "INNER JOIN MainCategory AS M " +
                "ON S.main_category_id = M.main_category_id " +
            "WHERE datetime Like :select||'%' " +
            "ORDER BY expense_item_id ASC;")
    fun loadEI(select:String) : List<ExpenseItemDetail>

    //Expense Item - Month 기준 합계 데이터 불러오기
    @Query("SELECT substr(datetime,1,7) AS date, SUM(price) AS price " +
            "FROM ExpenseItem " +
            "WHERE substr(datetime,1,7) Like :select||'%' " +
            "GROUP BY substr(datetime,1,7) " +
            "ORDER BY expense_item_id ASC;")
    fun loadMonthSumEI(select:String ) : List<loadSumEI>

    //Expense Item - Day 기준 합계 데이터 불러오기
    @Query("SELECT datetime AS date, SUM(price) AS price " +
            "FROM ExpenseItem WHERE datetime Like :select||'%' " +
            "GROUP BY datetime " +
            "ORDER BY expense_item_id ASC;")
    fun loadDaySumEI(select:String ) : List<loadSumEI>

    //Expense Item - Week 기준 합계 데이터 불러오기
    @Query("SELECT strftime('%W',datetime) AS date, SUM(price) AS price " +
            "FROM ExpenseItem WHERE datetime Like :select||'%' " +
            "GROUP BY strftime('%W',datetime)" +
            "ORDER BY strftime('%W',datetime) ASC;")
    fun loadWeekSumEI(select:String ) : List<loadSumEI>

    //Expense Item - Week Day 기준 합계 데이터 불러오기
    @Query("SELECT datetime AS date, SUM(price) AS price " +
            "FROM ExpenseItem WHERE strftime('%W',datetime) Like :select||'%' " +
            "GROUP BY datetime ORDER BY datetime ASC;")
    fun loadWeekDaySumEI(select:String ) : List<loadSumEI>

    //Expense Item - main category 기준 합계 데이터 불러오기
    @Query("SELECT substr(E.datetime,1,7) AS 'date' ," +
            "SUM(E.price) AS 'price', " +
            "M.main_category_name AS 'main_name'," +
            "M.main_category_id AS 'main_id'," +
            "M.expense_type AS 'type'" +
            "FROM ExpenseItem AS E " +
            "INNER JOIN SubCategory AS S " +
            "ON E.sub_category_id = S.sub_category_id " +
                "INNER JOIN MainCategory AS M " +
                "ON S.main_category_id = M.main_category_id " +
            "WHERE E.datetime like :select||'%' " +
            "GROUP BY M.main_category_id " +
            "ORDER BY M.main_category_id ASC; "
    )fun loadMonthSumMainCategoryEI(select:String ) : List<loadSumMainCategoryEI>

    //Expense Item - sub category 기준 합계 데이터 불러오기
    @Query("SELECT substr(E.datetime,1,7) AS date, " +
                "SUM(E.price) AS price, " +
                "S.sub_category_name AS sub_name, " +
                "S.main_category_id AS main_id " +
            "FROM ExpenseItem AS E " +
            "INNER JOIN SubCategory AS S " +
            "ON E.sub_category_id = S.sub_category_id " +
            "WHERE E.datetime Like :select||'%' " +
            "GROUP BY E.sub_category_id " +
            "ORDER BY E.sub_category_id ASC;")
    fun loadMonthSumSubCategoryEI(select:String ) : List<loadSumSubCategoryEI>

    //Expense Item - 데이터 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEI(entity: ExpenseItem)

    //IncomeItem- 데이터 업데이트
    @Update
    fun updateEI(entity: ExpenseItem)

    //Expense Item - 데이터 삭제
    @Delete
    fun deleteEI(entity: ExpenseItem);

    //데이터 전체 삭제
    @Query("DELETE FROM `ExpenseItem`")
    fun deleteAllEI()

}