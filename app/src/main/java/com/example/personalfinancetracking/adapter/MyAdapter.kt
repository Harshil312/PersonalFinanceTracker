package com.example.personalfinancetracking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinancetracking.R
import com.example.personalfinancetracking.model.Details
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class MyAdapter(options: FirebaseRecyclerOptions<Details>) :
    FirebaseRecyclerAdapter<Details, MyAdapter.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Details) {

        if (model.Income.isEmpty())
        {
            holder.setDetailAmt("Amount : ₹"+model.Expense)
        }else
        {
            holder.setDetailAmt("Amount : ₹"+model.Income)
        }
        holder.setDetailDate("Date : "+model.Date)
        holder.setDetailType("Item : "+model.ItemName)
        holder.setDetailNote("Note : "+model.Note)
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

}
