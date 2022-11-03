package com.example.personalfinancetracking.fragment

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

    fun getTotal():Int{
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
}