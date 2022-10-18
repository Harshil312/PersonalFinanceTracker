package com.example.personalfinancetracking.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinancetracking.R
import com.example.personalfinancetracking.adapter.MyAdapter
import com.example.personalfinancetracking.databinding.FragmentIncomeBinding
import com.example.personalfinancetracking.model.Details
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


class IncomeFragment : Fragment() {
    lateinit var binding: FragmentIncomeBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var DBRefer: DatabaseReference
    lateinit var adapter: MyAdapter
    val TAG = "IncomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_income, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

            binding.rcViewIncomeFragment.layoutManager = LinearLayoutManager(requireContext())
        DBRefer = FirebaseDatabase.getInstance().getReference().child("Income")
            .child(firebaseAuth.currentUser!!.uid)
        binding.fabIncomeFragment.setOnClickListener {
            callCustomDialog()
        }
        return binding.root
    }

    private fun callCustomDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.custom_dialog, null)
        dialog.setView(view)

        val alertDialog = dialog.create()
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(false)

        val actItem = view.findViewById<AutoCompleteTextView>(R.id.atc_item)
        val incomeArray: Array<String> = arrayOf("Salary","Investment Return", "Other")
        val arrayAdapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            incomeArray
        )
        actItem.setAdapter(arrayAdapter)
        val edtAmount = view.findViewById<EditText>(R.id.edt_Amount)
        val edtNote = view.findViewById<EditText>(R.id.edt_Note)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
        val id = DBRefer.push().key
        btnSave.setOnClickListener {
            var amount: String = edtAmount.text.toString().trim()
            var item: String = actItem.text.toString().trim()
            var note: String = edtNote.text.toString().trim()

            if (TextUtils.isEmpty(amount)) {
                Toast.makeText(requireContext(), "Please Enter Amount!", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(item)) {
                Toast.makeText(requireContext(), "Please Select Item!", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val dateFormater = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                    val calendar = Calendar.getInstance()
                    val date: String = dateFormater.format(calendar.time)
                    val month = date.substring(3, 5)
                    Log.d(TAG, "callCustomDialog: MONTH : " + month)
                    Log.d(TAG, "callCustomDialog: DATE : " + date)

                    val details = Details(item, amount, "", note, date, month)
                    DBRefer.child(id.toString()).setValue(details).addOnCompleteListener {
                        if (!it.isSuccessful) {
//                            Toast.makeText(requireContext(), "Data Added!", Toast.LENGTH_SHORT)
//                                .show()
                            Toast.makeText(requireContext(), "Data NOT Added!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    alertDialog.dismiss()
                } catch (e: Exception) {
                    Log.e(TAG, "callCustomDialog: " + e.message)
                }
            }
        }

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()


    }

    override fun onStart() {
        super.onStart()
        try {
            val recyclerOptions = FirebaseRecyclerOptions.Builder<Details>()
                .setQuery(DBRefer, Details::class.java)
                .build()
            adapter = MyAdapter(recyclerOptions)
            binding.rcViewIncomeFragment.adapter = adapter
            adapter.startListening()

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "onStart: " + e.message)
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            adapter.stopListening()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}