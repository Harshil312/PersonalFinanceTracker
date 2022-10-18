package com.example.personalfinancetracking.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.personalfinancetracking.R
import com.example.personalfinancetracking.databinding.FragmentAnalyticBinding
import com.example.personalfinancetracking.model.Details
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AnalyticFragment : Fragment() {
    lateinit var binding: FragmentAnalyticBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var DBRefer:DatabaseReference
     var totalExpense:Int = 0
    var totalIncome:Int = 0
    var totalSaving:Int = 0
    var totalGrocery:Int = 0
    var totalShopping:Int = 0
    var totalInvestment:Int = 0
    var totalBillPayment:Int = 0
    var totalFeePayment:Int = 0
    var totalInsurance:Int = 0
    var totalRent:Int = 0
    var totalFoodExpense:Int = 0
    var totalOther:Int = 0
    var totalInvestmentReturn:Int = 0
    var totalOtherIncome:Int = 0
    var totalSalary:Int = 0
    val TAG = "AnalyticFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_analytic, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        //Total Expense
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        DBRefer.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalExpense += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT EXP : $totalExpense")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        //Total Income
        DBRefer = FirebaseDatabase.getInstance().getReference("Income").child(firebaseAuth.currentUser!!.uid)
        DBRefer.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalIncome += Integer.parseInt(details!!.Income)
                    Log.d(TAG, "onDataChange: TOT EXP : $totalIncome")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        //total grocery
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        var query:Query = DBRefer.orderByChild("item").equalTo("Grocery")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalGrocery += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT GROCERY : $totalGrocery")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })
        //total Salary
        DBRefer = FirebaseDatabase.getInstance().getReference("Income").child(firebaseAuth.currentUser!!.uid)
        query = DBRefer.orderByChild("item").equalTo("Salary")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalGrocery += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT SALARY : $totalGrocery")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Shopping
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        query= DBRefer.orderByChild("item").equalTo("Shopping")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalShopping += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT SHOPPING : $totalShopping")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Investment
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        query= DBRefer.orderByChild("item").equalTo("Investment")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalInvestment += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT INVESTMENT : $totalInvestment")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Bill Payments
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        query= DBRefer.orderByChild("item").equalTo("Bill Payments")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalBillPayment += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT BILLPAYMENT : $totalBillPayment")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Fee Payments
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        query= DBRefer.orderByChild("item").equalTo("Fee Payments")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalFeePayment += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT FEEPAYMENT : $totalFeePayment")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Insurance
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        query= DBRefer.orderByChild("item").equalTo("Insurance")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalInsurance += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT INSURANCE : $totalInsurance")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Rent
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        query= DBRefer.orderByChild("item").equalTo("Rent")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalRent += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT RENT : $totalRent")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Food Expense
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        query= DBRefer.orderByChild("item").equalTo("Food Expense")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalFoodExpense += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT FOODEXP : $totalFoodExpense")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Other Expense
        DBRefer = FirebaseDatabase.getInstance().getReference("Expense").child(firebaseAuth.currentUser!!.uid)
        query= DBRefer.orderByChild("item").equalTo("Other")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalOther += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT OTHEREXP : $totalOther")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Investment Return
        DBRefer = FirebaseDatabase.getInstance().getReference("Income").child(firebaseAuth.currentUser!!.uid)
        query = DBRefer.orderByChild("item").equalTo("Investment Return")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalInvestmentReturn += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT INVESTMENTRETURN : $totalInvestmentReturn")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })

        //total Other Income
        DBRefer = FirebaseDatabase.getInstance().getReference("Income").child(firebaseAuth.currentUser!!.uid)
        query = DBRefer.orderByChild("item").equalTo("Other")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot:DataSnapshot in snapshot.children)
                {
                    val details = dataSnapshot.getValue(Details::class.java)
                    totalOtherIncome += Integer.parseInt(details!!.Expense)
                    Log.d(TAG, "onDataChange: TOT OTHERINC : $totalOtherIncome")
                }
            }

            override fun onCancelled(error: DatabaseError)
            {}
        })


        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            Log.d(TAG, "onCreateView: FINAL TOT INC $totalIncome")
            Log.d(TAG, "onCreateView: FINAL TOT EXP $totalExpense")
            Log.d(TAG, "onCreateView: FINAL TOT GROS $totalGrocery")
            Log.d(TAG, "onCreateView: FINAL TOT SALARY $totalSalary")
            Log.d(TAG, "onCreateView: FINAL TOT SHOPPING $totalShopping")
            Log.d(TAG, "onCreateView: FINAL TOT INVESTMENT $totalInvestment")
            Log.d(TAG, "onCreateView: FINAL TOT BILLPAYMENT $totalBillPayment")
            Log.d(TAG, "onCreateView: FINAL TOT FEEPAYMENT $totalFeePayment")
            Log.d(TAG, "onCreateView: FINAL TOT INSURANCE $totalInsurance")
            Log.d(TAG, "onCreateView: FINAL TOT RENT $totalRent")
            Log.d(TAG, "onCreateView: FINAL TOT FOODEXP $totalFoodExpense")
            Log.d(TAG, "onCreateView: FINAL TOT OTHEREXP $totalOther")
            Log.d(TAG, "onCreateView: FINAL TOT INVESTMENTRETURN $totalInvestmentReturn")
            Log.d(TAG, "onCreateView: FINAL TOT OTHERINC $totalOtherIncome")
            totalSaving = totalIncome - totalExpense
            Log.d(TAG, "onCreateView: FINAL TOT SAV $totalSaving")

            binding.tvIncomeAnalytics.setText(totalIncome.toString())
            binding.tvExpenseAnalytics.setText(totalExpense.toString())
            binding.tvSavingAnalytics.setText(totalSaving.toString())
        },5000)

        return binding.root
    }

}