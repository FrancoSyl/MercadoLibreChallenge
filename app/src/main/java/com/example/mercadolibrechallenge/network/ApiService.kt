package com.example.mercadolibrechallenge.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    private val apiClientCommon = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    fun getRetrofitCommon(url: String): Retrofit {
        return Retrofit.Builder().client(apiClientCommon)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url).build()
    }

}