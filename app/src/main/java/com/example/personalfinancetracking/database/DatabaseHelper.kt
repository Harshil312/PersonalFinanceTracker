package com.example.personalfinancetracking.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "PERSONAL_FINANCE.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //Masters
//        db?.execSQL("CREATE TABLE LabMaster (LabId TEXT,LabName TEXT,CompanyCode TEXT,IsActive TEXT,CreateDate TEXT,CreateUser TEXT,IsLab TEXT,cdate TEXT,udate TEXT,UpdateUser TEXT)")
        db?.execSQL("CREATE TABLE Income (IncomeId Integer Primary Key AutoIncrement,Amount Integer,IncomeType TEXT,Date TEXT,Month TEXT,Note TEXT)")
        db?.execSQL("CREATE TABLE Expense (ExpenseId Integer Primary Key AutoIncrement,Amount Integer,ExpenseType TEXT,Date TEXT,Month TEXT,Note TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        this.onCreate(db)
    }
}