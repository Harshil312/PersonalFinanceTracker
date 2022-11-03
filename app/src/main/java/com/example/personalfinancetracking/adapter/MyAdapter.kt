package com.example.personalfinancetracking.adapter

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinancetracking.R
import com.example.personalfinancetracking.database.DatabaseManager
import com.example.personalfinancetracking.fragment.ExpenseFragment
import com.example.personalfinancetracking.fragment.IncomeFragment
import com.example.personalfinancetracking.model.Details
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class MyAdapter(mContext: Context, mFragment: Fragment, detailsList: ArrayList<Details>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var fragment: Fragment
    var context: Context
    var dataList: ArrayList<Details>
    lateinit var model: Details
    val TAG = "MyAdapter"
    var databaseManager: DatabaseManager

    init {
        fragment = mFragment
        context = mContext
        dataList = detailsList
        databaseManager = DatabaseManager(context)
        databaseManager.initializeInstance(context)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mView: View? = null
        lateinit var cardView: CardView

        init {
            mView = itemView
            cardView = mView!!.findViewById(R.id.cv_row)
        }

        fun setDetailType(itemName: String) {
            val item = mView!!.findViewById<TextView>(R.id.tv_Item_row)
            item.text = itemName

        }

        fun setDetailId(Id: String) {
            val item = mView!!.findViewById<TextView>(R.id.tv_Id_row)
            item.text = Id

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.single_row_adapter, null, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        model = dataList[position]
        if (fragment is IncomeFragment) {
            holder.setDetailAmt("Amount : " + dataList[position].Income)
        } else {
            holder.setDetailAmt("Amount : " + dataList[position].Expense)
        }
        holder.setDetailDate("Date : " + dataList[position].Date)
        holder.setDetailNote("Note : " + dataList[position].Note)
        holder.setDetailType("Income Type : " + dataList[position].ItemName)
        holder.setDetailId(dataList[position].Id)

        holder.cardView.setOnClickListener {
            if (fragment is IncomeFragment) {
                callUpdateDialog(
                    dataList[position].Id,
                    dataList[position].Income.toString(),
                    dataList[position].Note,
                    dataList[position].ItemName,
                    dataList[position].Date
                )
            } else  {
                callUpdateDialog(
                    dataList[position].Id,
                    dataList[position].Expense.toString(),
                    dataList[position].Note,
                    dataList[position].ItemName,
                    dataList[position].Date
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun callUpdateDialog(id: String, amount: String, note: String, itemType: String, date: String) {
        val dialog = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.update_custom_dialog, null)
        dialog.setView(view)

        val alertDialog = dialog.create()
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(true)

        val actItem = view.findViewById<AutoCompleteTextView>(R.id.atc_item_update)
        if (fragment is IncomeFragment) {
            val arr = context.resources.getStringArray(R.array.incomeItem)
            val arrayAdapter = ArrayAdapter<String>(
                context,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arr
            )
            actItem.setAdapter(arrayAdapter)
        } else {
            val arr = context.resources.getStringArray(R.array.expenseItem)
            val arrayAdapter = ArrayAdapter<String>(
                context,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arr
            )
            actItem.setAdapter(arrayAdapter)
        }

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
                Toast.makeText(context, "Please Enter Amount!", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(item)) {
                Toast.makeText(context, "Please Select Item!", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val month = date.substring(3, 5)
                    Log.d(TAG, "callCustomDialog: MONTH : " + month)
                    Log.d(TAG, "callCustomDialog: DATE : " + date)
                    if (fragment is IncomeFragment) {
                        val details = Details(id, item, amount.toInt(), 0, note, date, month)

                        var count = databaseManager.updateInsert(details)
                        if (count > 0) {
                            Toast.makeText(context, "Data Updated!", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "Data NOT Updated!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        val details = Details(id, item, 0, amount.toInt(), note, date, month)

                        var count = databaseManager.updateExpense(details)
                        if (count > 0) {
                            Toast.makeText(context, "Data Updated!", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "Data NOT Updated!", Toast.LENGTH_SHORT)
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
}
