package com.example.personalfinancetracking.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.personalfinancetracking.CommonUIUtility
import com.example.personalfinancetracking.R
import com.example.personalfinancetracking.database.DatabaseManager
import com.example.personalfinancetracking.database.Query
import com.example.personalfinancetracking.databinding.FragmentAnalyticBinding
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.PieModel
import java.text.SimpleDateFormat
import java.util.*


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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analytic, container, false)
        commonUIUtility = CommonUIUtility(requireContext())
        databaseManager = DatabaseManager(requireContext())
        databaseManager.initializeInstance(requireContext())
        Log.d(TAG, "onCreateView: Total Income :" + getTotalIncome())
        Log.d(TAG, "onCreateView: Total Expense :" + getTotalExpense())
        Log.d(TAG, "onCreateView: Total Savings :" + getTotalSaving())
        binding.tvExpenseAnalytics.text = getTotalExpense().toString()
        binding.tvIncomeAnalytics.text = getTotalIncome().toString()
        binding.tvSavingAnalytics.text = (getTotalIncome() - getTotalExpense()).toString()

        commonUIUtility.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            commonUIUtility.dismissProgress()
            showPieChart()
            bindMonthSpinner()
        },3000)

        return binding.root
    }

    fun getTotalIncome(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalIncome())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalIncome: " + e.message)
        }
        return count
    }

    fun getTotalExpense(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalExpense())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalIncome: " + e.message)
        }
        return count
    }

    fun getTotalSaving(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalSaving())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalIncome: " + e.message)
        }
        return count
    }

    fun getTotalSalary(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalSalary())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalSalary: " + e.message)
        }
        return count
    }

    fun getTotalInvestmentReturn(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalInvestmentReturn())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalInvestmentReturn: " + e.message)
        }
        return count
    }

    fun getTotalOtherIncome(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalOtherIncome())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalOtherIncome: " + e.message)
        }
        return count
    }

    fun getTotalGrocery(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalGrocery())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalGrocery: " + e.message)
        }
        return count
    }

    fun getTotalShopping(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalShopping())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalShopping: " + e.message)
        }
        return count
    }

    fun getTotalInvestment(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalInvestment())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalInvestment: " + e.message)
        }
        return count
    }

    fun getTotalBillPayments(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalBillPayments())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalBillPayments: " + e.message)
        }
        return count
    }

    fun getTotalFeePayments(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalFeePayments())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalFeePayments: " + e.message)
        }
        return count
    }

    fun getTotalInsurance(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalInsurance())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalInsurance: " + e.message)
        }
        return count
    }

    fun getTotalRent(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalRent())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalRent: " + e.message)
        }
        return count
    }

    fun getTotalFoodExpense(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalFoodExpense())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalFoodExpense: " + e.message)
        }
        return count
    }

    fun getTotalOtherExpense(): Int {
        var count = 0
        try {
            count = databaseManager.ExecuteScalar(Query().getTotalOther())!!.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getTotalOtherExpense: " + e.message)
        }
        return count
    }

    fun showPieChart() {
        binding.piechart.addPieSlice(
            PieModel(
                "Grocery",
                getTotalGrocery().toFloat(),
                Color.parseColor("#FE6DA8")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Shopping",
                getTotalShopping().toFloat(),
                Color.parseColor("#56B7F1")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Investment",
                getTotalInvestment().toFloat(),
                Color.parseColor("#5645FF")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Bill Payments",
                getTotalBillPayments().toFloat(),
                Color.parseColor("#545712")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Fee Payments",
                getTotalFeePayments().toFloat(),
                Color.parseColor("#555787")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Insurance",
                getTotalInsurance().toFloat(),
                Color.parseColor("#5FD765")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Rent",
                getTotalRent().toFloat(),
                Color.parseColor("#56E6BA")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Food Expense",
                getTotalFoodExpense().toFloat(),
                Color.parseColor("#56FC5F")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Salary",
                getTotalSalary().toFloat(),
                Color.parseColor("#564BD5")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Investment Return",
                getTotalInvestmentReturn().toFloat(),
                Color.parseColor("#56D9D5")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Other Income",
                getTotalOtherIncome().toFloat(),
                Color.parseColor("#564B12")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Other",
                getTotalOtherExpense().toFloat(),
                Color.parseColor("#564A96")
            )
        )
        binding.piechart.startAnimation()
    }

    fun bindMonthSpinner() {
        try {
            val dateFormater = SimpleDateFormat("dd-MM-yyyy", Locale.US)
            val calendar = Calendar.getInstance()
            val date: String = dateFormater.format(calendar.time)
            val month = date.substring(3, 5).toInt()
            val monthArr = resources.getStringArray(R.array.month)
            val adapter = ArrayAdapter<String>(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                monthArr
            )
            binding.spnMonth.adapter = adapter
            binding.spnMonth.setSelection(month - 1)
            binding.spnMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val digit = p2+1
                    var mon:String =digit.toString()
                    if (mon.length<2){
                        mon ="0$mon"
                    }
                    binding.piechartMonth.clearChart()
                    binding.piechartMonth.clearAnimation()
                    binding.piechartMonth.clearFocus()
                    showPieChartMonthWise(mon.toString())
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "bindMonthSpinner: " + e.message)
        }
    }

    fun showPieChartMonthWise(month: String) {
        try {
            var totalGrocery =
                databaseManager.ExecuteScalar(Query().getTotalGroceryMonthWise(month))
            if (totalGrocery.equals("invalid")) {
                totalGrocery = "0"
            }
            var totalShopping =
                databaseManager.ExecuteScalar(Query().getTotalShoppingMonthWise(month))
            if (totalShopping.equals("invalid")) {
                totalShopping = "0"
            }
            var totalSaving =
                databaseManager.ExecuteScalar(Query().getTotalSavingMonthWise(month))
            if (totalSaving.equals("invalid")) {
                totalSaving = "0"
            }
            var totalInvestment =
                databaseManager.ExecuteScalar(Query().getTotalInvestmentMonthWise(month))
            if (totalInvestment.equals("invalid")) {
                totalInvestment = "0"
            }
            var totalBillPayments =
                databaseManager.ExecuteScalar(Query().getTotalBillPaymentsMonthWise(month))
            if (totalBillPayments.equals("invalid")) {
                totalBillPayments = "0"
            }
            var totalFeePayments =
                databaseManager.ExecuteScalar(Query().getTotalFeePaymentsMonthWise(month))
            if (totalFeePayments.equals("invalid")) {
                totalFeePayments = "0"
            }
            var totalInsurance =
                databaseManager.ExecuteScalar(Query().getTotalInsuranceMonthWise(month))
            if (totalInsurance.equals("invalid")) {
                totalInsurance = "0"
            }
            var totalRent = databaseManager.ExecuteScalar(Query().getTotalRentMonthWise(month))
            if (totalRent.equals("invalid")) {
                totalRent = "0"
            }
            var totalFoodExpense =
                databaseManager.ExecuteScalar(Query().getTotalFoodExpenseMonthWise(month))
            if (totalFoodExpense.equals("invalid")) {
                totalFoodExpense = "0"
            }
            var totalSalary = databaseManager.ExecuteScalar(Query().getTotalSalaryMonthWise(month))
            if (totalSalary.equals("invalid")) {
                totalSalary = "0"
            }
            var totalInvestmentReturn =
                databaseManager.ExecuteScalar(Query().getTotalInvestmentReturnMonthWise(month))
            if (totalInvestmentReturn.equals("invalid")) {
                totalInvestmentReturn = "0"
            }
            var totalOtherIncome =
                databaseManager.ExecuteScalar(Query().getTotalOtherIncomeMonthWise(month))
            if (totalOtherIncome.equals("invalid")) {
                totalOtherIncome = "0"
            }
            var totalOtherExpense =
                databaseManager.ExecuteScalar(Query().getTotalOtherMonthWise(month))
            if (totalOtherExpense.equals("invalid")) {
                totalOtherExpense = "0"
            }

            Log.d(TAG, "showPieChartMonthWise: totalGrocery : $totalGrocery")
            Log.d(TAG, "showPieChartMonthWise: totalShopping : $totalShopping")
            Log.d(TAG, "showPieChartMonthWise: totalSaving : $totalSaving")
            Log.d(TAG, "showPieChartMonthWise: totalInvestment : $totalInvestment")
            Log.d(TAG, "showPieChartMonthWise: totalBillPayments : $totalBillPayments")
            Log.d(TAG, "showPieChartMonthWise: totalFeePayments : $totalFeePayments")
            Log.d(TAG, "showPieChartMonthWise: totalInsurance : $totalInsurance")
            Log.d(TAG, "showPieChartMonthWise: totalRent : $totalRent")
            Log.d(TAG, "showPieChartMonthWise: totalFoodExpense : $totalFoodExpense")
            Log.d(TAG, "showPieChartMonthWise: totalSalary : $totalSalary")
            Log.d(TAG, "showPieChartMonthWise: totalInvestmentReturn : $totalInvestmentReturn")
            Log.d(TAG, "showPieChartMonthWise: totalOtherIncome : $totalOtherIncome")
            Log.d(TAG, "showPieChartMonthWise: totalOtherExpense : $totalOtherExpense")

            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Grocery",
                    totalGrocery!!.toFloat(),
                    Color.parseColor("#FE6DA8")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Shopping",
                    totalShopping!!.toFloat(),
                    Color.parseColor("#56B7F1")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Investment",
                    totalInvestment!!.toFloat(),
                    Color.parseColor("#5645FF")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Bill Payments",
                    totalBillPayments!!.toFloat(),
                    Color.parseColor("#545712")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Fee Payments",
                    totalFeePayments!!.toFloat(),
                    Color.parseColor("#555787")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Insurance",
                    totalInsurance!!.toFloat(),
                    Color.parseColor("#5FD765")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Rent",
                    totalRent!!.toFloat(),
                    Color.parseColor("#56E6BA")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Food Expense",
                    totalFoodExpense!!.toFloat(),
                    Color.parseColor("#56FC5F")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Salary",
                    totalSalary!!.toFloat(),
                    Color.parseColor("#564BD5")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Saving",
                    totalSaving!!.toFloat(),
                    Color.parseColor("#562BD3")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Investment Return",
                    totalInvestmentReturn!!.toFloat(),
                    Color.parseColor("#56D9D5")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Other Income",
                    totalOtherIncome!!.toFloat(),
                    Color.parseColor("#564B12")
                )
            )
            binding.piechartMonth.addPieSlice(
                PieModel(
                    "Other",
                    totalOtherExpense!!.toFloat(),
                    Color.parseColor("#564A96")
                )
            )
            binding.piechartMonth.startAnimation()
        } catch (e: Exception) {
            Log.e(TAG, "showPieChartMonthWise: " + e.message)
            e.printStackTrace()
        }
    }
}