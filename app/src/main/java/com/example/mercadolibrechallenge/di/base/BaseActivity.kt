package com.example.mercadolibrechallenge.di.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.ui.dialogs.ProgressDefaultDialog
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    private var progressHUD: ProgressDefaultDialog? = null
    private val progressTimeoutTime = 20000L // 20 secs
    private val progressHandler = Handler(Looper.getMainLooper())
    protected val sharedPreferences: SharedPreferences by inject()

    private val progressTask = Runnable {
        runOnUiThread {
            hideProgress()
            onTimeout()
        }
    }

    protected open fun onTimeout() = toast(R.string.loading_timeout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressHUD = ProgressDefaultDialog(this)
    }

    fun hideProgress() {
        if (isFinishing.not() || isDestroyed.not()) {
            progressHUD?.dismiss()
            progressHandler.removeCallbacks(progressTask)
        }
    }

    fun showProgress() {
        if (isFinishing.not()) {
            progressHUD?.show()
            progressHandler.postDelayed(progressTask, progressTimeoutTime)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}