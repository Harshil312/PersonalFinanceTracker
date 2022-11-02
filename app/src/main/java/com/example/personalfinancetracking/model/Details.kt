package com.example.personalfinancetracking.model

data class Details(
    var Id:String = "",
    var ItemName: String = "",
    var Income: Int = 0,
    var Expense: Int = 0,
    var Note: String = "",
    var Date: String = "",
    var Month: String = ""
)
