package com.example.personalfinancetracking.database

import android.util.Log

class Query {
    lateinit var qry: String
    val TAG = "Query"

    //    fun getLabs():String
//    {
//        qry = "SELECT LabName FROM LabMaster"
//        Log.d(TAG, "getLabs : $qry")
//        return qry
//    }
//
    fun getIncomeData(): String {
        qry = "SELECT IncomeId,Amount,IncomeType,Date,Month,Note FROM Income"
        Log.d(TAG, "getIncomeData : $qry")
        return qry
    }

    fun getExpenseData(): String {
        qry = "SELECT ExpenseId,Amount,ExpenseType,Date,Month,Note FROM Expense"
        Log.d(TAG, "getExpenseData : $qry")
        return qry
    }

    fun getTotalIncome(): String {
        qry = "SELECT SUM(Amount) FROM Income"
        Log.d(TAG, "getTotalIncomeSaving : $qry")
        return qry
    }

    fun getTotalExpense(): String {
        qry = "SELECT SUM(Amount) FROM Expense"
        Log.d(TAG, "getTotalExpense : $qry")
        return qry
    }

    fun getTotalSaving(): String {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Saving'"
        Log.d(TAG, "getTotalSaving : $qry")
        return qry
    }

    fun getTotalSalary(): String {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Salary'"
        Log.d(TAG, "getTotalSalary : $qry")
        return qry
    }

    fun getTotalInvestmentReturn(): String {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Investment Return'"
        Log.d(TAG, "getTotalInvestmentReturn : $qry")
        return qry
    }

    fun getTotalOtherIncome(): String {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Other'"
        Log.d(TAG, "getTotalOtherIncome : $qry")
        return qry
    }

    fun getTotalGrocery(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Grocery'"
        Log.d(TAG, "getTotalGrocery : $qry")
        return qry
    }

    fun getTotalShopping(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Shopping'"
        Log.d(TAG, "getTotalShopping : $qry")
        return qry
    }

    fun getTotalInvestment(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Investment'"
        Log.d(TAG, "getTotalInvestment : $qry")
        return qry
    }

    fun getTotalBillPayments(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Bill Payments'"
        Log.d(TAG, "getTotalBillPayments : $qry")
        return qry
    }

    fun getTotalFeePayments(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Fee Payments'"
        Log.d(TAG, "getTotalFeePayments : $qry")
        return qry
    }

    fun getTotalInsurance(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Insurance'"
        Log.d(TAG, "getTotalInsurance : $qry")
        return qry
    }

    fun getTotalRent(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Rent'"
        Log.d(TAG, "getTotalRent : $qry")
        return qry
    }

    fun getTotalFoodExpense(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Food Expense'"
        Log.d(TAG, "getTotalFoodExpense : $qry")
        return qry
    }

    fun getTotalOther(): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Other'"
        Log.d(TAG, "getTotalOtherExpense : $qry")
        return qry
    }
}