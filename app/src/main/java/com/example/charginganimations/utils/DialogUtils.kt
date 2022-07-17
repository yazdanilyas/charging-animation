package com.example.charginganimations.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.example.charginganimations.R
import com.google.android.material.bottomsheet.BottomSheetDialog


object DialogUtils {
    fun overLayPermissionDialog(context: Context, dialogCallBack: DialogCallBack) {
        val dialog = Dialog(context)
        val view = dialog.layoutInflater.inflate(R.layout.dialog_overlay_permission, null, false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(view)
        val confirmBtn = view.findViewById<AppCompatButton>(R.id.confirmBtn)
        val cancelBtn = view.findViewById<AppCompatButton>(R.id.cancelBtn)

        confirmBtn.setOnClickListener {
            dialogCallBack.onPositiveCallBack(dialog)

        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    //
    interface DialogCallBack {
        fun onPositiveCallBack(dialog: Dialog)
        fun onNegativeCallBack()
    }

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

    fun bottomSheetDialog(context: AppCompatActivity, sheetListener: BottomSheetListener) {
        val bottomSheetDialog = BottomSheetDialog(context)
        val view = context.layoutInflater.inflate(R.layout.bottom_sheet_apply_animation, null)
        val lockScreenView = view.findViewById<CardView>(R.id.lockScreenView)
        val chargingScreenView = view.findViewById<CardView>(R.id.chargingScreenView)
        val bothScreenView = view.findViewById<CardView>(R.id.bothScreenView)
        val cancelView = view.findViewById<CardView>(R.id.cancelView)
        lockScreenView.setOnClickListener {
            sheetListener.onLockScreenClick(bottomSheetDialog)
        }
        chargingScreenView.setOnClickListener {
            sheetListener.onChargingScreenClick(bottomSheetDialog)
        }
        bothScreenView.setOnClickListener {
            sheetListener.onBothClick(bottomSheetDialog)
        }
        cancelView.setOnClickListener {
            sheetListener.onCancel(bottomSheetDialog)
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    interface BottomSheetListener {
        fun onLockScreenClick(bottomSheetDialog: BottomSheetDialog)
        fun onChargingScreenClick(bottomSheetDialog: BottomSheetDialog)
        fun onBothClick(bottomSheetDialog: BottomSheetDialog)
        fun onCancel(bottomSheetDialog: BottomSheetDialog)
    }
}