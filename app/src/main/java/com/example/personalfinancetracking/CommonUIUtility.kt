package com.example.personalfinancetracking


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

class CommonUIUtility(var context: Context) {
    val dialog= Dialog(context)

    public fun showProgress() {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.custom_progress_dialog, null, false)
        dialog.setContentView(view.rootView)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = WindowManager.LayoutParams()
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = lp
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    public fun dismissProgress() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}