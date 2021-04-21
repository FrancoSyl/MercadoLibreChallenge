package com.example.mercadolibrechallenge.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.mercadolibrechallenge.utils.AppConstants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory<SharedPreferences> {
        androidContext().getSharedPreferences(
            AppConstants.PREF_NAME, Context.MODE_PRIVATE
        )
    }
}