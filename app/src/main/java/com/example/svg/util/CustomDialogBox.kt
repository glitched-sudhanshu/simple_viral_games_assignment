package com.example.svg.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import com.example.svg.R

fun showCustomDialogBox(context: Context, @StringRes resId : Int, onClick:() -> Unit) {
  val dialog = Dialog(context)
  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
  dialog.setCancelable(false)
  dialog.setContentView(R.layout.custom_dialog_box)

  dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

  val btnYes = dialog.findViewById<Button>(R.id.btnYes)
  val btnNo = dialog.findViewById<Button>(R.id.btnNo)
  val tvMessage = dialog.findViewById<TextView>(R.id.tvMessage)
  tvMessage.text = context.getString(resId)

  btnYes.setOnClickListener {
    onClick()
    dialog.dismiss()
  }

  btnNo.setOnClickListener { dialog.dismiss() }
  dialog.show()
}