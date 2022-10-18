package com.example.personalfinancetracking.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinancetracking.R
import com.example.personalfinancetracking.adapter.MyAdapter
import com.example.personalfinancetracking.databinding.FragmentExpenseBinding
import com.example.personalfinancetracking.databinding.FragmentIncomeBinding
import com.example.personalfinancetracking.model.Details
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class ExpenseFragment : Fragment() {

    lateinit var binding: FragmentExpenseBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var DBRefer: DatabaseReference
    lateinit var adapter: MyAdapter
    var pushKey:String = ""
    val TAG = "ExpenseFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.rcViewExpenseFragment.layoutManager = LinearLayoutManager(requireContext())
        DBRefer = FirebaseDatabase.getInstance().getReference().child("Expense")
            .child(firebaseAuth.currentUser!!.uid);
        binding.fabExpenseFragment.setOnClickListener {
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
        val ExpenseArray: Array<String> = arrayOf(
            "Grocery",
            "Shopping",
            "Investment",
            "Bill Payments",
            "Fee Payments",
            "Insurance",
            "Rent",
            "Food Expense",
            "Other"
        )
        val arrayAdapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            ExpenseArray
        )
        actItem.setAdapter(arrayAdapter)
        val edtExpense = view.findViewById<EditText>(R.id.edt_Amount)
        val edtNote = view.findViewById<EditText>(R.id.edt_Note)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
        val id = DBRefer.push().key
        btnSave.setOnClickListener {
            var Expense: String = edtExpense.text.toString().trim()
            var item: String = actItem.text.toString().trim()
            var note: String = edtNote.text.toString().trim()

            if (TextUtils.isEmpty(Expense)) {
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

                    val details = Details(item, "", Expense, note, date, month)
                    DBRefer.child(id.toString()).setValue(details).addOnCompleteListener {
                        if (!it.isSuccessful) {

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

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mView: View? = null


        init {
            mView = itemView
        }

        fun setDetailType(itemName: String) {
            val item = mView!!.findViewById<TextView>(R.id.tv_Item_row)
            item.text = itemName

        }

        fun setDetailAmt(itemName: String) {
            val item = mView!!.findViewById<TextView>(R.id.tv_Amt_row)
            item.text = itemName

        }

        fun setDetailDate(itemName: String) {
            val item = mView!!.findViewById<TextView>(R.id.tv_Date_row)
            item.text = itemName
        }

        fun setDetailNote(itemName: String) {
            val item = mView!!.findViewById<TextView>(R.id.tv_Note_row)
            item.text = itemName
        }
    }

    override fun onStart() {
        super.onStart()
        try {
            val recyclerOptions = FirebaseRecyclerOptions.Builder<Details>()
                .setQuery(DBRefer, Details::class.java)
                .build()
            val adapter : FirebaseRecyclerAdapter<Details, MyViewHolder> = object : FirebaseRecyclerAdapter<Details, MyViewHolder>(recyclerOptions) {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row_adapter,parent,false)
                    return MyViewHolder(view)
                }

                override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Details) {
                    holder.setDetailAmt("Amount : "+model.Expense)
                    holder.setDetailDate("Date : "+model.Date)
                    holder.setDetailType("Name : "+model.ItemName)
                    holder.setDetailNote("Note : "+model.Note)
                    holder.mView!!.setOnClickListener {
                        pushKey = getRef(position).key.toString()
                        callUpdateDialog(model.Expense,model.Note,model.ItemName,model.Date)
                    }
                }
            }
            binding.rcViewExpenseFragment.adapter = adapter
            adapter.startListening()

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "onStart: " + e.message)
        }
    }

    private fun callUpdateDialog(amount:String,note:String,itemType:String,date:String) {
        val dialog = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.update_custom_dialog, null)
        dialog.setView(view)

        val alertDialog = dialog.create()
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(true)

        val actItem = view.findViewById<AutoCompleteTextView>(R.id.atc_item_update)
        val ExpenseArray: Array<String> = arrayOf(
            "Grocery",
            "Shopping",
            "Investment",
            "Bill Payments",
            "Fee Payments",
            "Insurance",
            "Rent",
            "Food Expense",
            "Other"
        )
        val arrayAdapter = ArrayAdapter<String>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ExpenseArray)
        actItem.setAdapter(arrayAdapter)
        val edtAmount = view.findViewById<EditText>(R.id.edt_Amount_update)
        val edtNote = view.findViewById<EditText>(R.id.edt_Note_update)
        val btnSave = view.findViewById<Button>(R.id.btnUpdate)
        val btnCancel = view.findViewById<Button>(R.id.btnDelete)

        edtNote.setText(note)
        edtAmount.setText(amount)
        actItem.setText(itemType)
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
//                    val dateFormater = SimpleDateFormat("dd-MM-yyyy", Locale.US)
//                    val calendar = Calendar.getInstance()
//                    val date: String = dateFormater.format(calendar.time)
                    val month = date.substring(3, 5)
                    Log.d(TAG, "callCustomDialog: MONTH : " + month)
                    Log.d(TAG, "callCustomDialog: DATE : " + date)

                    val details = Details(item, "", amount, note, date, month)
                    DBRefer.child(pushKey).setValue(details).addOnCompleteListener {
                        if (!it.isSuccessful) {
//                            Toast.makeText(requireContext(), "Data Added!", Toast.LENGTH_SHORT)
//                                .show()
                            Toast.makeText(requireContext(), "Data NOT Updated!", Toast.LENGTH_SHORT)
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
            DBRefer.child(pushKey).removeValue().addOnCompleteListener {
                if (!it.isSuccessful)
                {
                    Toast.makeText(requireContext(), "Data NOT Deleted!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

}

