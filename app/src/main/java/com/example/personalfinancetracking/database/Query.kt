package com.example.personalfinancetracking.database

import android.util.Log

class Query {
    lateinit var qry:String
    val TAG = "Query"

//    fun getLabs():String
//    {
//        qry = "SELECT LabName FROM LabMaster"
//        Log.d(TAG, "getLabs : $qry")
//        return qry
//    }
//
    fun getIncomeData():String
    {
        qry = "SELECT IncomeId,Amount,IncomeType,Date,Month,Note FROM Income"
        Log.d(TAG, "getIncomeData : $qry")
        return qry
    }

    fun getExpenseData():String
    {
        qry = "SELECT ExpenseId,Amount,ExpenseType,Date,Month,Note FROM Expense"
        Log.d(TAG, "getExpenseData : $qry")
        return qry
    }


}