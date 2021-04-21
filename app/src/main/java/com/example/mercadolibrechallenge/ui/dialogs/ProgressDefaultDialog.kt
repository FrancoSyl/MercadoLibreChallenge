package com.example.mercadolibrechallenge.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.utils.AppConstants

class ProgressDefaultDialog(context: Context) : Dialog(context) {

    init {
        setTitle(AppConstants.EMPTY_STRING)
        setContentView(R.layout.dialog_progress_default)
        setCancelable(false)

        window?.apply {
            attributes?.apply {
                dimAmount = 0.2f
                gravity = Gravity.CENTER
            }

            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}