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

    fun getTotalIncome():String
    {
        qry = "SELECT SUM(Amount) FROM Income"
        Log.d(TAG, "getTotalIncomeSaving : $qry")
        return qry
    }
    fun getTotalExpense():String
    {
        qry = "SELECT SUM(Amount) FROM Expense"
        Log.d(TAG, "getTotalExpense : $qry")
        return qry
    }

    fun getTotalSaving():String
    {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Saving'"
        Log.d(TAG, "getTotalSaving : $qry")
        return qry
    }

    fun getTotalSalary():String
    {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Salary'"
        Log.d(TAG, "getTotalSalary : $qry")
        return qry
    }

    fun getTotalInvestmentReturn():String
    {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Investment Return'"
        Log.d(TAG, "getTotalInvestmentReturn : $qry")
        return qry
    }

    fun getTotalOtherIncome():String
    {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Other'"
        Log.d(TAG, "getTotalOtherIncome : $qry")
        return qry
    }
}