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

    fun getTotalSavingMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Saving' AND Month='$month'"
        Log.d(TAG, "getTotalSavingMonthWise : $qry")
        return qry
    }

    fun getTotalSalaryMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Salary' AND Month='$month'"
        Log.d(TAG, "getTotalSalaryMonthWise : $qry")
        return qry
    }

    fun getTotalInvestmentReturnMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Investment Return' AND Month='$month'"
        Log.d(TAG, "getTotalInvestmentReturnMonthWise : $qry")
        return qry
    }

    fun getTotalOtherIncomeMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Income WHERE IncomeType = 'Other' AND Month='$month'"
        Log.d(TAG, "getTotalOtherIncomeMonthWise : $qry")
        return qry
    }

    fun getTotalGroceryMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Grocery' AND Month='$month'"
        Log.d(TAG, "getTotalGroceryMonthWise : $qry")
        return qry
    }

    fun getTotalShoppingMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Shopping' AND Month='$month'"
        Log.d(TAG, "getTotalShoppingMonthWise : $qry")
        return qry
    }

    fun getTotalInvestmentMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Investment' AND Month='$month'"
        Log.d(TAG, "getTotalInvestmentMonthWise : $qry")
        return qry
    }

    fun getTotalBillPaymentsMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Bill Payments' AND Month='$month'"
        Log.d(TAG, "getTotalBillPaymentsMonthWise : $qry")
        return qry
    }

    fun getTotalFeePaymentsMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Fee Payments' AND Month='$month'"
        Log.d(TAG, "getTotalFeePaymentsMonthWise : $qry")
        return qry
    }

    fun getTotalInsuranceMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Insurance' AND Month='$month'"
        Log.d(TAG, "getTotalInsuranceMonthWise : $qry")
        return qry
    }

    fun getTotalRentMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Rent' AND Month='$month'"
        Log.d(TAG, "getTotalRentMonthWise : $qry")
        return qry
    }

    fun getTotalFoodExpenseMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Food Expense' AND Month='$month'"
        Log.d(TAG, "getTotalFoodExpenseMonthWise : $qry")
        return qry
    }

    fun getTotalOtherMonthWise(month:String): String {
        qry = "SELECT SUM(Amount) FROM Expense WHERE ExpenseType = 'Other' AND Month='$month'"
        Log.d(TAG, "getTotalOtherMonthWise : $qry")
        return qry
    }
}