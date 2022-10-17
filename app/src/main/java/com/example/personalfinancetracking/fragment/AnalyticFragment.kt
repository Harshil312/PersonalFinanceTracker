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
                TODO("Not yet implemented")
            }
        })
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            Log.d(TAG, "onCreateView: FINAL TOT INC $totalIncome")
            Log.d(TAG, "onCreateView: FINAL TOT EXP $totalExpense")
            totalSaving = totalIncome - totalExpense
            Log.d(TAG, "onCreateView: FINAL TOT SAV $totalSaving")

            binding.tvIncomeAnalytics.setText(totalIncome.toString())
            binding.tvExpenseAnalytics.setText(totalExpense.toString())
            binding.tvSavingAnalytics.setText(totalSaving.toString())
        },2000)

        return binding.root
    }

}