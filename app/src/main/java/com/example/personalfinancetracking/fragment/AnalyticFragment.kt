package com.example.personalfinancetracking.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.personalfinancetracking.CommonUIUtility
import com.example.personalfinancetracking.R
import com.example.personalfinancetracking.database.DatabaseManager
import com.example.personalfinancetracking.database.Query
import com.example.personalfinancetracking.databinding.FragmentAnalyticBinding
import org.eazegraph.lib.models.PieModel


class AnalyticFragment : Fragment() {
    lateinit var binding: FragmentAnalyticBinding
    lateinit var commonUIUtility: CommonUIUtility
    lateinit var databaseManager: DatabaseManager
    val TAG = "AnalyticFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_analytic, container, false)
        commonUIUtility = CommonUIUtility(requireContext())
        databaseManager = DatabaseManager(requireContext())
        databaseManager.initializeInstance(requireContext())
        Log.d(TAG, "onCreateView: Total Income :" +getTotalIncome())
        Log.d(TAG, "onCreateView: Total Expense :" +getTotalExpense())
        Log.d(TAG, "onCreateView: Total Savings :" +getTotalSaving())
        binding.tvExpenseAnalytics.text = getTotalExpense().toString()
        binding.tvIncomeAnalytics.text = getTotalIncome().toString()
        binding.tvSavingAnalytics.text = (getTotalIncome() - getTotalExpense()).toString()
        showPieChart()
        return binding.root
    }

    fun getTotalIncome():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalIncome())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalIncome: "+e.message)
        }
        return count
    }

    fun getTotalExpense():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalExpense())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalIncome: "+e.message)
        }
        return count
    }

    fun getTotalSaving():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalSaving())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalIncome: "+e.message)
        }
        return count
    }

    fun getTotalSalary():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalSalary())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalSalary: "+e.message)
        }
        return count
    }

    fun getTotalInvestmentReturn():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalInvestmentReturn())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalInvestmentReturn: "+e.message)
        }
        return count
    }
    fun getTotalOtherIncome():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalOtherIncome())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalOtherIncome: "+e.message)
        }
        return count
    }
    fun getTotalGrocery():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalGrocery())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalGrocery: "+e.message)
        }
        return count
    }
    fun getTotalShopping():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalShopping())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalShopping: "+e.message)
        }
        return count
    }
    fun getTotalInvestment():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalInvestment())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalInvestment: "+e.message)
        }
        return count
    }
    fun getTotalBillPayments():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalBillPayments())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalBillPayments: "+e.message)
        }
        return count
    }
    fun getTotalFeePayments():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalFeePayments())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalFeePayments: "+e.message)
        }
        return count
    }
    fun getTotalInsurance():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalInsurance())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalInsurance: "+e.message)
        }
        return count
    }
    fun getTotalRent():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalRent())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalRent: "+e.message)
        }
        return count
    }
    fun getTotalFoodExpense():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalFoodExpense())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalFoodExpense: "+e.message)
        }
        return count
    }
    fun getTotalOtherExpense():Int{
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalOther())!!.toInt()
        }catch (e:Exception)
        {
            e.printStackTrace()
            Log.e(TAG, "getTotalOtherExpense: "+e.message)
        }
        return count
    }
    fun showPieChart()
    {
        binding.piechart.addPieSlice(PieModel("Grocery", getTotalGrocery().toFloat(), Color.parseColor("#FE6DA8")))
        binding.piechart.addPieSlice(PieModel("Shopping", getTotalShopping().toFloat(), Color.parseColor("#56B7F1")))
        binding.piechart.addPieSlice(PieModel("Investment", getTotalInvestment().toFloat(), Color.parseColor("#5645FF")))
        binding.piechart.addPieSlice(PieModel("Bill Payments", getTotalBillPayments().toFloat(), Color.parseColor("#545712")))
        binding.piechart.addPieSlice(PieModel("Fee Payments", getTotalFeePayments().toFloat(), Color.parseColor("#555787")))
        binding.piechart.addPieSlice(PieModel("Insurance", getTotalInsurance().toFloat(), Color.parseColor("#5FD765")))
        binding.piechart.addPieSlice(PieModel("Rent", getTotalRent().toFloat(), Color.parseColor("#56E6BA")))
        binding.piechart.addPieSlice(PieModel("Food Expense", getTotalFoodExpense().toFloat(), Color.parseColor("#56FC5F")))
        binding.piechart.addPieSlice(PieModel("Salary", getTotalSalary().toFloat(), Color.parseColor("#564BD5")))
        binding.piechart.addPieSlice(PieModel("Investment Return", getTotalInvestmentReturn().toFloat(), Color.parseColor("#56D9D5")))
        binding.piechart.addPieSlice(PieModel("Other Income", getTotalOtherIncome().toFloat(), Color.parseColor("#564B12")))
        binding.piechart.addPieSlice(PieModel("Other", getTotalOtherExpense().toFloat(), Color.parseColor("#564A96")))
        binding.piechart.startAnimation()
   }
}