package com.example.charginganimations.utils

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.example.charginganimations.R


object DialogUtils {
    //    fun exitDialog(context: Context, dialogCallBack: DialogCallBack) {
//        val dialog = Dialog(context)
//        val view = dialog.layoutInflater.inflate(R.layout.custom_exit_dialog, null, false)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.setContentView(view)
//        val yesImgBtn = view.findViewById<ImageView>(R.id.yesImgBtn)
//        val noImgBtn = view.findViewById<ImageView>(R.id.noImgBtn)
//        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
//        ratingBar.onRatingBarChangeListener =
//            RatingBar.OnRatingBarChangeListener { p0, p1, p2 ->
//                CommonUtils.rateApp(context)
//                dialog.dismiss()
//            }
//        yesImgBtn.setOnClickListener {
//            dialogCallBack.onPositiveCallBack()
//        }
//        noImgBtn.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.show()
//
//    }
//
//    interface DialogCallBack {
//        fun onPositiveCallBack()
//        fun onNegativeCallBack()
//    }
    open fun withItems(context: Context, view: View?) {
        val items = arrayOf("10 Seconds", "20 Seconds", "30 Seconds", "1 Minute", "Always")
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Select Animation Duration")
        builder.setSingleChoiceItems(
            items, 0
        ) { dialog, choice ->

        }

        builder.setPositiveButton("OK", null)
        builder.setNegativeButton("CANCEL", null)
        builder
        builder.setIcon(R.drawable.ic_share)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
//        val button: Button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
//        button.setBackgroundColor(Color.BLACK)
//        button.setPadding(0, 0, 20, 0)
//        button.setTextColor(Color.WHITE)
    }
}