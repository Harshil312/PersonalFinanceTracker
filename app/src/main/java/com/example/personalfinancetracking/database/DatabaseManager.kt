package com.example.personalfinancetracking.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.personalfinancetracking.constants.Constants
import com.example.personalfinancetracking.model.Details


class DatabaseManager(context: Context) {
    var mOpenCounter = 0
    var instance: DatabaseManager? = null
    var mDatabaseHelper = DatabaseHelper(context)
    var mDatabase: SQLiteDatabase? = null
    val TAG = "DatabaseManager"
    @Synchronized
    fun initializeInstance(context: Context?) {
        if (instance == null) {
            instance = DatabaseManager(context!!)
        }
    }

    @JvmName("getInstance1")
    fun getInstance(): DatabaseManager {
        if (instance != null) {
            throw IllegalStateException(DatabaseManager::class.java.simpleName + "is not initialized, Call initializeInstance(..) method first")
        }
        return instance!!
    }

    fun openDatabase(): SQLiteDatabase {
        mOpenCounter++
        if (mOpenCounter == 1) {
            mDatabase = mDatabaseHelper.writableDatabase
        }

        return mDatabaseHelper.writableDatabase
    }

    fun closeDatabase() {
        mOpenCounter--
        if (mOpenCounter == 0) {
            mDatabase?.close()
        }
    }

    fun ExecuteScalar(qry: String?): String? {
        Log.d("ExecuteScalar", qry!!)
        mDatabase = openDatabase()
        var str = "invalid"
        val cursor: Cursor = mDatabase!!.rawQuery(qry, null)
        try {
             str= if (cursor.count > 0) {
                cursor.moveToFirst()
                cursor.getString(Constants.index)
            } else {
                "invalid"
            }
        } catch (ex: java.lang.Exception) {
             str= "invalid"
            ex.printStackTrace()
        }
        cursor.close()
        return str
    }

    fun CommonExecute(qry: String) {
        Log.d("CommonExecute", qry)
        mDatabase = openDatabase()
        try {
            mDatabase?.execSQL(qry)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("CommonExecute", "" + e.message)
        }
    }

    fun ExecuteQuery(qry: String):Int{
        Log.d("ExecuteQuery",qry)
        mDatabase = openDatabase()
        var data = 0
        try {
            val cursor = mDatabase?.rawQuery(qry,null)
            if (cursor!=null && cursor.count>0)
            {
                data = 1
            }else
            {
                data = 0
            }
        }catch (e:Exception)
        {
            data=0
            e.printStackTrace()
            Log.e("ExecuteQuery",""+e.message)
        }

        return data
    }

    fun ExecuteRawSql(qry: String):Cursor?
    {
        Log.d("ExecuteRawSql",qry)
        mDatabase = openDatabase()
        return try {
            val cursor: Cursor? = mDatabase?.rawQuery(qry, null)
            if (cursor != null && cursor.count > 0) {
                cursor
            } else {
                null
            }
        } catch (ex: java.lang.Exception) {
            null
        }
    }

    fun commonInsert(list:ContentValues,tableName:String){
        mDatabase = openDatabase()
        try {
            mDatabase?.insert(tableName,null,list)
            closeDatabase()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e("commonInsert",""+e.message)
        }
    }

    fun insertIncome(details: Details):Long{
        mDatabase = openDatabase()
        var count:Long = 0L
        try {
            val list = ContentValues()
            list.put("Amount",details.Income)
            list.put("IncomeType",details.ItemName)
            list.put("Date",details.Date)
            list.put("Month",details.Month)
            list.put("Note",details.Note)

            count = mDatabase!!.insert(Constants.TBL_INCOME,null,list)
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "insertIncome: "+e.message)
        }
        return count
    }

    fun insertExpense(details: Details):Long{
        mDatabase = openDatabase()
        var count:Long = 0L
        try {
            val list = ContentValues()
            list.put("Amount",details.Expense)
            list.put("ExpenseType",details.ItemName)
            list.put("Date",details.Date)
            list.put("Month",details.Month)
            list.put("Note",details.Note)

            count = mDatabase!!.insert(Constants.TBL_EXPENSE,null,list)
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "insertIncome: "+e.message)
        }
        return count
    }

    fun updateInsert(details: Details) : Long{
        mDatabase = openDatabase()
        val count = 0L
        try {
            val list = ContentValues()
            list.put("Amount",details.Income)
            list.put("IncomeType",details.ItemName)
            list.put("Date",details.Date)
            list.put("Month",details.Month)
            list.put("Note",details.Note)
            mDatabase!!.update(Constants.TBL_INCOME,list,"IncomeId = ?", arrayOf(details.Id))
        }catch (e:Exception)
        {
            Log.e(TAG, "updateInsert: "+e.message)
            e.printStackTrace()
        }
        return count
    }

    fun updateExpense(details: Details) : Long{
        mDatabase = openDatabase()
        val count = 0L
        try {
            val list = ContentValues()
            list.put("Amount",details.Expense)
            list.put("ExpenseType",details.ItemName)
            list.put("Date",details.Date)
            list.put("Month",details.Month)
            list.put("Note",details.Note)
            mDatabase!!.update(Constants.TBL_INCOME,list,"ExpenseId = ?", arrayOf(details.Id))
        }catch (e:Exception)
        {
            Log.e(TAG, "updateExpense: "+e.message)
            e.printStackTrace()
        }
        return count
    }

    fun deleteData(tableName: String){
        Log.d("deleteData: ",tableName)
        mDatabase = openDatabase()
        try {
            mDatabase?.execSQL("DELETE FROM $tableName")
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e("deleteData",""+e.message)
        }
    }
}