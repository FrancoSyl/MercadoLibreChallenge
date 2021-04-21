package com.example.mercadolibrechallenge.ui.activities

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.controllers.ObjectsController
import com.example.mercadolibrechallenge.di.base.BaseActivity
import com.example.mercadolibrechallenge.network.ApiService
import com.example.mercadolibrechallenge.network.ProductsService
import com.example.mercadolibrechallenge.network.responses.Product
import com.example.mercadolibrechallenge.network.responses.ResponseSearch
import com.example.mercadolibrechallenge.utils.AppConstants
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {

    private val productsList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)

        val service = ApiService.getRetrofitCommon(AppConstants.BASE_URL_TO_GET_PRODUCTS).create(ProductsService::class.java)
        service.getProducts().enqueue(object : Callback<ResponseSearch> {
            override fun onResponse(
                call: Call<ResponseSearch>,
                response: Response<ResponseSearch>
            ) {
                response.body()?.let {
                    if (it.results != null){
                        it.results.forEach { product ->
                            productsList.add(product)
                        }
                    }
                    ObjectsController.productsList = productsList
                    finish()
                    MainActivity.start(this@SplashActivity)
                }
            }

            override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                finish()
                toast("Error: ${t.message}")
            }
        })

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        Handler(Looper.getMainLooper()).postDelayed({

        }, 1000)
    }

}