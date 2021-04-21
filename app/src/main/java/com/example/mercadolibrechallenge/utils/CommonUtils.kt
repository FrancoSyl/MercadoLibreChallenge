package com.example.mercadolibrechallenge.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes

object CommonUtils {

    fun getDialog(context: Context, @LayoutRes layoutRes: Int, configBlock: Dialog.() -> Unit): Dialog {
        return Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(layoutRes)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            configBlock()
            show()
        }
    }

}