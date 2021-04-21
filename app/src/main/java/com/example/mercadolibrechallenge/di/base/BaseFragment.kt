package com.example.mercadolibrechallenge.di.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {
    protected val sharedPreferences: SharedPreferences by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    fun hideProgress() {
        activity?.let {
            if (it is BaseActivity) {
                it.hideProgress()
            }
        }
    }

    fun showProgress() {
        activity?.let {
            if (it is BaseActivity) {
                it.showProgress()
            }
        }
    }

    abstract fun setUp()

}